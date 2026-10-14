import pytest
from main import factorial, read_lines, print_lines


# ── Пример из методических указаний: factorial ─────────────────────────
@pytest.mark.parametrize("n,expected", [(0, 1), (1, 1), (5, 120)])
def test_factorial(n, expected):
    assert factorial(n) == expected


def test_negative_input():
    with pytest.raises(ValueError):
        factorial(-1)


# ── Вариант 3: чтение файла и вывод строк по порядку ──────────────────
@pytest.fixture
def make_file(tmp_path):
    """Создаёт временный файл с заданным содержимым и возвращает путь к нему."""
    def make(content, encoding="utf-8", newline=None):
        p = tmp_path / "data.txt"
        with open(p, "w", encoding=encoding, newline=newline) as f:
            f.write(content)
        return p
    return make


def test_tc1_order(make_file):
    """TC1: строки возвращаются в том же порядке, что в файле."""
    assert read_lines(make_file("первая\nвторая\nтретья\n")) == ["первая", "вторая", "третья"]


def test_tc2_no_trailing_newline(make_file):
    """TC2: последняя строка без перевода строки не теряется."""
    assert read_lines(make_file("a\nb")) == ["a", "b"]


def test_tc3_empty_file(make_file):
    """TC3: пустой файл — пустой список."""
    assert read_lines(make_file("")) == []


def test_tc4_blank_lines_kept(make_file):
    """TC4: пустые строки в середине файла сохраняются."""
    assert read_lines(make_file("a\n\nb\n")) == ["a", "", "b"]


def test_tc5_windows_line_endings(make_file):
    """TC5: переводы строк Windows (\\r\\n) не попадают в результат."""
    assert read_lines(make_file("x\r\ny\r\n", newline="")) == ["x", "y"]


def test_tc6_cyrillic_encoding(make_file):
    """TC6: файл в другой кодировке читается при указании encoding."""
    assert read_lines(make_file("Привет\n", encoding="cp1251"), encoding="cp1251") == ["Привет"]


def test_tc7_missing_file(tmp_path):
    """TC7: несуществующий файл — FileNotFoundError."""
    with pytest.raises(FileNotFoundError):
        read_lines(tmp_path / "нет_такого.txt")


def test_tc8_print_lines(make_file, capsys):
    """TC8: print_lines выводит строки с номерами по порядку и возвращает их количество."""
    assert print_lines(make_file("один\nдва\n")) == 2
    assert capsys.readouterr().out == "1: один\n2: два\n"
