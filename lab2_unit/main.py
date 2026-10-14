"""Лабораторная работа 2. Unit-тестирование программ на Python (вариант 3)."""


def factorial(n):
    """Пример из методических указаний: факториал неотрицательного целого числа."""
    if n < 0:
        raise ValueError("Факториал определён только для неотрицательных чисел")
    result = 1
    for i in range(2, n + 1):
        result *= i
    return result


def read_lines(path, encoding="utf-8"):
    """Вариант 3: читает текстовый файл и возвращает его строки по порядку (без символов перевода строки).
    Пустые строки сохраняются — они тоже часть файла. Отсутствующий файл → FileNotFoundError."""
    with open(path, encoding=encoding) as f:
        return [line.rstrip("\r\n") for line in f]


def print_lines(path, encoding="utf-8"):
    """Выводит строки файла по порядку с номерами и возвращает их количество."""
    lines = read_lines(path, encoding)
    for number, line in enumerate(lines, start=1):
        print(f"{number}: {line}")
    return len(lines)


if __name__ == "__main__":
    import sys
    if len(sys.argv) != 2:
        print("Использование: python main.py <файл>")
    else:
        print_lines(sys.argv[1])
