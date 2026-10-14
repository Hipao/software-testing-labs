package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static constants.Constant.TimeoutVariable.IMPLICIT_WAIT;

public class BasicActions {
    public static WebDriver createDriver() {
        // Путь к chromedriver.exe не указываем: Selenium Manager (Selenium 4.6+) сам скачивает
        // драйвер под установленную версию Chrome. Ручной путь из методички (C:\tools\chromedriver.exe)
        // ломается при каждом обновлении браузера.

        // Настраиваем запуск Chrome в режиме инкогнито
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        // Без окна: mvn test -Dheadless=true (удобно для запуска из консоли)
        if (Boolean.getBoolean("headless")) {
            chromeOptions.addArguments("--headless=new", "--window-size=1920,1080");
        }

        // Создаём драйвер с этими настройками
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Разворачиваем окно и устанавливаем неявное ожидание
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));

        return driver;
    }
}
