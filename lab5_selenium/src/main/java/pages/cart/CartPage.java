package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

import java.time.Duration;
import java.util.List;

// Страница корзины: цены, список товаров, удаление товара
public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Локатор цены рюкзака
    private final By backpackInCart = By.xpath(
        "//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']//..//..//div[@class='inventory_item_price']"
    );

    // Локатор цены футболки
    private final By blackTshirtInCart = By.xpath(
        "//div[@class='inventory_item_name' and text()='Sauce Labs Bolt T-Shirt']//..//..//div[@class='inventory_item_price']"
    );

    private final By itemNames = By.xpath("//div[@class='cart_list']//div[@class='inventory_item_name']");

    private String totalPrice;

    // Метод рассчитывает сумму товаров и сохраняет в переменную
    public CartPage calculateTotalPrice() {
        // Получаем элементы с ценами
        WebElement backpackPriceElement = driver.findElement(backpackInCart);
        WebElement blackTshirtPriceElement = driver.findElement(blackTshirtInCart);

        // Преобразуем текст из "$29.99" в число
        double backpackPrice = Double.parseDouble(backpackPriceElement.getText().replace("$", ""));
        double blackTshirtPrice = Double.parseDouble(blackTshirtPriceElement.getText().replace("$", ""));

        // Складываем и форматируем в строку с долларом и двумя знаками после точки
        double total = backpackPrice + blackTshirtPrice;
        this.totalPrice = String.format(java.util.Locale.US, "$%.2f", total);

        return this;
    }

    // Метод возвращает ранее рассчитанную сумму
    public String getTotalPrice() {
        return totalPrice;
    }

    // Названия всех товаров в корзине
    public List<String> getItemNames() {
        return driver.findElements(itemNames).stream().map(WebElement::getText).toList();
    }

    // Удалить товар из корзины по техническому имени (sauce-labs-backpack, ...)
    public CartPage removeItem(String itemId) {
        driver.findElement(By.xpath("//button[@name='remove-" + itemId + "']")).click();
        return this;
    }

    // Есть ли товар в корзине (без неявного ожидания, чтобы отсутствие проверялось сразу)
    public boolean containsItem(String name) {
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            return !driver.findElements(By.xpath(
                "//div[@class='cart_list']//div[@class='inventory_item_name' and text()='" + name + "']")).isEmpty();
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(constants.Constant.TimeoutVariable.IMPLICIT_WAIT));
        }
    }
}
