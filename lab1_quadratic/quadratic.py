"""Лабораторная работа 1, вариант 3.
Решение уравнения a*x^2 + b*x + c = 0, где a, b, c — любые вещественные числа."""
import math

EPS = 1e-12   # погрешность сравнения вещественных чисел с нулём


def parse_number(text):
    """Преобразует введённую строку в вещественное число. Допускается запятая вместо точки.
    Бесконечность и NaN не являются допустимыми коэффициентами."""
    try:
        value = float(str(text).strip().replace(",", "."))
    except ValueError:
        raise ValueError(f"«{text}» не является числом") from None
    if math.isinf(value) or math.isnan(value):
        raise ValueError("коэффициент должен быть конечным числом")
    return value


def solve(a, b, c):
    """Возвращает (вид решения, список действительных корней)."""
    if abs(a) < EPS:                                   # ветвь 1: уравнение не квадратное
        if abs(b) < EPS:                               # ветвь 2: b = 0
            if abs(c) < EPS:                           # ветвь 3: 0 = 0
                return "бесконечно много решений", []
            return "нет решений", []                   # ветвь 4: c = 0 при c ≠ 0
        return "линейное уравнение", [-c / b + 0.0]    # ветвь 5: bx + c = 0 (+0.0 убирает «-0»)
    d = b * b - 4 * a * c                              # дискриминант
    if d > EPS:                                        # ветвь 6: два корня
        s = math.sqrt(d)
        return "два корня", sorted([(-b - s) / (2 * a), (-b + s) / (2 * a)])
    if abs(d) <= EPS:                                  # ветвь 7: один (кратный) корень
        return "один корень", [-b / (2 * a) + 0.0]
    return "нет действительных корней", []             # ветвь 8: d < 0


def main():
    print("Решение уравнения a*x^2 + b*x + c = 0")
    try:
        a = parse_number(input("a = "))
        b = parse_number(input("b = "))
        c = parse_number(input("c = "))
    except ValueError as e:
        print("Ошибка ввода:", e)
        return
    kind, roots = solve(a, b, c)
    print(kind + (": " + "; ".join(f"x{i + 1} = {r:g}" for i, r in enumerate(roots)) if roots else ""))


if __name__ == "__main__":
    main()
