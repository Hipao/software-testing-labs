package tests.wrongpasswordtest;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static constants.Constant.Urls.SAUCEDEMO_LOGIN_PAGE;
import static constants.Constant.Users.STANDARD_USER;

// Самостоятельно, сценарий 4: при неверном пароле появляется сообщение об ошибке
public class WrongPasswordTest extends BaseTest {

    @Test(description = "Неверный пароль: вход не выполнен, показано сообщение об ошибке")
    public void wrongPasswordTest() {
        basePage.open(SAUCEDEMO_LOGIN_PAGE);
        saucedemoLoginPage.loggingIn(STANDARD_USER, "wrong_password");

        String error = saucedemoLoginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"),
                "Нет ожидаемого сообщения об ошибке, получено: " + error);
        Assert.assertFalse(driver.getCurrentUrl().contains("inventory"), "Вход выполнен с неверным паролем.");
    }
}
