"""Тестирование методом «чёрного ящика» по формулировке задания (без знания кода):
эквивалентное разбиение, граничные значения, причинно-следственные связи, предположение об ошибке.
Тесты, выявившие дефекты, помечены xfail с описанием дефекта — набор проходит, а дефект виден в отчёте."""
import math
import pytest
from quadratic import parse_number, solve


# ── Эквивалентное разбиение ───────────────────────────────────────────
@pytest.mark.parametrize("a,b,c,kind", [
    (2, 3, -2, "два корня"),                    # класс «D > 0»
    (4, 4, 1, "один корень"),                   # класс «D = 0»
    (5, 1, 3, "нет действительных корней"),     # класс «D < 0»
    (0, 4, 8, "линейное уравнение"),            # класс «a = 0»
    (1.5, -2.25, 0.5, "два корня"),             # вещественные (дробные) коэффициенты
], ids=["B1-D>0", "B2-D=0", "B3-D<0", "B4-a=0", "B5-fractional"])
def test_equivalence(a, b, c, kind):
    got, roots = solve(a, b, c)
    assert got == kind
    for r in roots:                             # каждый найденный корень — решение уравнения
        assert a * r * r + b * r + c == pytest.approx(0, abs=1e-9)


# ── Граничные значения ────────────────────────────────────────────────
def test_boundary_discriminant_zero_fractional():
    """B6: D = 0 при дробных коэффициентах (0,25x² + x + 1 = 0)."""
    assert solve(0.25, 1, 1) == ("один корень", [-2.0])


def test_boundary_small_positive_discriminant():
    """B7: D чуть больше нуля — должно быть два близких корня."""
    kind, roots = solve(1, -2, 0.999)
    assert kind == "два корня" and roots[0] < 1 < roots[1]


@pytest.mark.xfail(strict=True, reason="ДЕФЕКТ 1: при |a| < 1e-12 уравнение ошибочно считается линейным")
def test_boundary_tiny_a():
    """B8: a = 1e-13 ≠ 0 — уравнение квадратное, у него два корня."""
    kind, _ = solve(1e-13, 1, -1)
    assert kind == "два корня"


def test_boundary_large_values():
    """B9: большие, но допустимые коэффициенты (1e100)."""
    kind, roots = solve(1e100, -3e100, 2e100)
    assert kind == "два корня" and roots == pytest.approx([1, 2])


# ── Причинно-следственные связи (знак a и c → наличие корней) ──────────
def test_cause_effect_ac_negative():
    """B10: если a и c разного знака, D = b² − 4ac > 0 всегда → два корня."""
    for b in (-5, 0, 5):
        assert solve(2, b, -3)[0] == "два корня"


# ── Предположение об ошибке ───────────────────────────────────────────
@pytest.mark.parametrize("text,value", [("2,5", 2.5), (" -3 ", -3.0), ("1e3", 1000.0)],
                         ids=["B11-comma", "B12-spaces", "B13-exponent"])
def test_input_valid(text, value):
    assert parse_number(text) == value


@pytest.mark.parametrize("text", ["abc", "", "inf", "nan"], ids=["B14-letters", "B15-empty", "B16-inf", "B17-nan"])
def test_input_invalid(text):
    with pytest.raises(ValueError):
        parse_number(text)


@pytest.mark.xfail(strict=True, reason="ДЕФЕКТ 2: переполнение b² при |b| > 1e154 даёт корни inf/nan")
def test_overflow():
    """B18: очень большие коэффициенты — корни должны быть конечными числами."""
    _, roots = solve(1e200, 3e200, 2e200)
    assert all(math.isfinite(r) for r in roots) and roots == pytest.approx([-2, -1])
