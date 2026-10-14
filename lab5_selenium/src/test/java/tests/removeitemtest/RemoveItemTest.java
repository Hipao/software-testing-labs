package tests.removeitemtest;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static constants.Constant.Urls.SAUCEDEMO_LOGIN_PAGE;

// Самостоятельно, сценарий 3: удалённый из корзины товар исчезает из списка
public class RemoveItemTest extends BaseTest {

    @Test(description = "Удалённый товар исчезает из корзины, остальные остаются")
    public void removeItemTest() {
        basePage.open(SAUCEDEMO_LOGIN_PAGE);
        saucedemoLoginPage.loggingIn();
        saucedemoShowcasePage.takeAndGoToCart();   // рюкзак + футболка Bolt
        Assert.assertTrue(cartPage.containsItem("Sauce Labs Backpack"), "Рюкзака нет в корзине до удаления.");

        cartPage.removeItem("sauce-labs-backpack");

        Assert.assertFalse(cartPage.containsItem("Sauce Labs Backpack"), "Рюкзак остался в корзине после удаления.");
        Assert.assertTrue(cartPage.containsItem("Sauce Labs Bolt T-Shirt"), "Удалился не тот товар.");
        Assert.assertEquals(cartPage.getItemNames().size(), 1, "В корзине должен остаться один товар.");
    }
}
