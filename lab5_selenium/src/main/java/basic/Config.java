package basic;

public class Config {
    // Указываем операционную систему и браузер, которые будут использоваться в тестах
    // Формат: "os-browser" (например, "win-chrome", "mac-firefox", "linux-chrome")
    public static final String OS_AND_BROWSER = "win-chrome";

    // Если true — после каждого теста будут очищаться cookies и хранилище браузера
    public static final Boolean CLEAR_COOKIES_AND_STORAGE = true;

    // Если false — браузер будет закрываться после завершения тестов класса
    // Если true — браузер останется открытым (например, для отладки)
    public static final Boolean HOLD_BROWSER_OPEN = false;
}
