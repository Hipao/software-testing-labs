package constants;

import java.time.Duration;

public class Constant {
    // Переменные, связанные с таймингами ожидания
    public static class TimeoutVariable {
        // Время неявного ожидания (в секундах) — Selenium будет ждать элементы на странице
        public static final int IMPLICIT_WAIT = 5;

        // Время явного ожидания — используется в WebDriverWait
        public static final Duration EXPLICIT_WAIT = Duration.ofSeconds(10);
    }

    // URL-адреса страниц, с которыми работают тесты
    public static class Urls {
        // Страница логина сайта, который мы тестируем
        public static final String SAUCEDEMO_LOGIN_PAGE = "https://www.saucedemo.com/";
    }

    // Учётные данные демо-сайта (опубликованы на самой странице логина saucedemo.com)
    public static class Users {
        public static final String STANDARD_USER = "standard_user";
        public static final String PASSWORD = "secret_sauce";
    }
}
