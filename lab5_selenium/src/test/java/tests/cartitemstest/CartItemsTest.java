package tests.cartitemstest;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.List;

import static constants.Constant.Urls.SAUCEDEMO_LOGIN_PAGE;

// Самостоятельно, сценарий 2: 4 разных товара добавляются в корзину и отображаются в ней
public class CartItemsTest extends BaseTest {

    @Test(description = "Четыре разных товара отображаются в корзине")
    public void fourItemsInCartTest() {
        basePage.open(SAUCEDEMO_LOGIN_PAGE);
        saucedemoLoginPage.loggingIn();

        saucedemoShowcasePage
                .addToCart("sauce-labs-backpack")
                .addToCart("sauce-labs-bike-light")
                .addToCart("sauce-labs-onesie")
                .addToCart("sauce-labs-fleece-jacket");
        Assert.assertEquals(saucedemoShowcasePage.getCartBadgeCount(), 4, "Счётчик корзины не равен 4.");

        saucedemoShowcasePage.goToCart();
        List<String> expected = List.of("Sauce Labs Backpack", "Sauce Labs Bike Light",
                "Sauce Labs Onesie", "Sauce Labs Fleece Jacket");
        Assert.assertEqualsNoOrder(cartPage.getItemNames().toArray(), expected.toArray(),
                "Состав корзины не совпадает с добавленными товарами.");
    }
}
