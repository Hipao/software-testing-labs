# Software testing labs

Coursework for a software testing course. One project for all labs:

| Folder | Lab | Stack |
|---|---|---|
| `lab1_quadratic` | Testing strategies: structural review, white-box and black-box tests for a quadratic equation solver | Python, pytest |
| `lab2_unit` | Unit testing: `factorial` example and `read_lines` (read a file and return its lines in order) | Python, pytest |
| `lab3_1c` | Use Case (black-box) testing of a 1C:Enterprise configuration | test plan, test cases, bug reports |
| `lab4_git` | Git & GitHub: Git Bash session log | Git |
| `lab5_selenium` | UI tests for saucedemo.com with the Page Object pattern | Java 21, Selenium 4, TestNG, Allure, Maven |

## Run

```bash
cd lab1_quadratic && python -m pytest -v
cd lab2_unit && python -m pytest -v
cd lab5_selenium && ./mvnw test -Dheadless=true   # Allure: ./mvnw allure:report
```
