package tests.base;

import basic.BasicActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.base.BasePage;
import pages.cart.CartPage;
import pages.saucedemo.SaucedemoLoginPage;
import pages.showcase.SaucedemoShowcasePage;

import static basic.Config.CLEAR_COOKIES_AND_STORAGE;
import static basic.Config.HOLD_BROWSER_OPEN;

// Базовый класс тестов — от него наследуются все тестовые классы.
// Отличие от методички: браузер создаётся перед классом (@BeforeClass) и закрывается после него (@AfterClass).
// В методичке драйвер создавался в поле класса и закрывался в @AfterSuite, поэтому при нескольких
// тестовых классах окна браузера оставались открытыми.
public class BaseTest {

    protected WebDriver driver;
    protected BasePage basePage;
    protected SaucedemoLoginPage saucedemoLoginPage;
    protected SaucedemoShowcasePage saucedemoShowcasePage;
    protected CartPage cartPage;

    @BeforeClass
    public void setUp() {
        driver = BasicActions.createDriver();
        basePage = new BasePage(driver);
        saucedemoLoginPage = new SaucedemoLoginPage(driver);
        saucedemoShowcasePage = new SaucedemoShowcasePage(driver);
        cartPage = new CartPage(driver);
    }

    // После каждого теста — очистка cookies, localStorage и sessionStorage
    @AfterMethod
    public void clearCookiesAndLocalStorage() {
        if (CLEAR_COOKIES_AND_STORAGE) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            js.executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
        }
    }

    // После всех тестов класса — закрытие браузера
    @AfterClass
    public void clear() {
        if (!HOLD_BROWSER_OPEN && driver != null) {
            driver.quit();
        }
    }
}
