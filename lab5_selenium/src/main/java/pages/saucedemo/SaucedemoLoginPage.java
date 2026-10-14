package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

import static constants.Constant.Users.PASSWORD;
import static constants.Constant.Users.STANDARD_USER;

// Страница логина. Здесь мы описываем действия: ввести логин, пароль и нажать кнопку Login
public class SaucedemoLoginPage extends BasePage {

    // Конструктор передаёт драйвер в базовый класс
    public SaucedemoLoginPage(WebDriver driver) {
        super(driver);
    }

    // Локаторы — «указатели», позволяющие найти нужные элементы на странице
    private final By loginInput = By.xpath("//input[@placeholder='Username']");
    private final By passwordInput = By.xpath("//input[@placeholder='Password']");
    private final By loginButton = By.xpath("//input[@type='submit']");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    // Метод для авторизации стандартным пользователем (как в методичке)
    public SaucedemoLoginPage loggingIn() {
        return loggingIn(STANDARD_USER, PASSWORD);
    }

    // Авторизация с произвольными логином и паролем (нужна для негативного сценария)
    public SaucedemoLoginPage loggingIn(String user, String password) {
        driver.findElement(loginInput).sendKeys(user);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
        return this;
    }

    // Доступна ли кнопка Login (видна и активна)
    public boolean isLoginButtonAvailable() {
        return waitElementIsVisible(loginButton).isEnabled();
    }

    // Текст сообщения об ошибке под формой входа
    public String getErrorMessage() {
        return waitElementIsVisible(errorMessage).getText();
    }
}
