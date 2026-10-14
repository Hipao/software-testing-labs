package tests.logouttest;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static constants.Constant.Urls.SAUCEDEMO_LOGIN_PAGE;

// Самостоятельно, сценарий 1: после выхода из учётной записи кнопка Login снова доступна
public class LogoutTest extends BaseTest {

    @Test(description = "После выхода (Logout) снова доступна кнопка Login")
    public void logoutTest() {
        basePage.open(SAUCEDEMO_LOGIN_PAGE);
        saucedemoLoginPage.loggingIn();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Вход не выполнен.");

        saucedemoShowcasePage.logout();

        Assert.assertTrue(saucedemoLoginPage.isLoginButtonAvailable(), "Кнопка Login недоступна после выхода.");
        Assert.assertFalse(driver.getCurrentUrl().contains("inventory"), "После выхода осталась страница каталога.");
    }
}
