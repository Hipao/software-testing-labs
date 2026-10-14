package pages.showcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

// Страница с товарами. Здесь мы добавляем товары в корзину, переходим в неё и выходим из учётной записи
public class SaucedemoShowcasePage extends BasePage {

    public SaucedemoShowcasePage(WebDriver driver) {
        super(driver);
    }

    private final By backpackButton = By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']");
    private final By blackTshirtButton = By.xpath("//button[@name='add-to-cart-sauce-labs-bolt-t-shirt']");
    private final By cartLinkButton = By.xpath("//a[@class='shopping_cart_link']");
    private final By cartBadge = By.xpath("//span[@class='shopping_cart_badge']");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    // Сценарий из методички: рюкзак + футболка Bolt, затем переход в корзину
    public SaucedemoShowcasePage takeAndGoToCart() {
        driver.findElement(backpackButton).click();
        driver.findElement(blackTshirtButton).click();
        driver.findElement(cartLinkButton).click();
        return this;
    }

    // Добавить товар по его техническому имени (sauce-labs-backpack, sauce-labs-onesie, ...)
    public SaucedemoShowcasePage addToCart(String itemId) {
        driver.findElement(By.xpath("//button[@name='add-to-cart-" + itemId + "']")).click();
        return this;
    }

    // Число на значке корзины
    public int getCartBadgeCount() {
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public SaucedemoShowcasePage goToCart() {
        driver.findElement(cartLinkButton).click();
        return this;
    }

    // Выход: открыть боковое меню и нажать Logout
    public SaucedemoShowcasePage logout() {
        driver.findElement(menuButton).click();
        waitElementIsVisible(logoutLink).click();
        return this;
    }
}
