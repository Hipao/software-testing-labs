package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.Constant.TimeoutVariable.EXPLICIT_WAIT;

public class BasePage {
    protected WebDriver driver;

    // Сохраняем драйвер при создании страницы
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для открытия нужной страницы
    public void open(String url) {
        driver.get(url);
    }

    // Метод для ожидания появления элемента на экране
    public WebElement waitElementIsVisible(WebElement element) {
        new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    // То же по локатору — удобно, когда элемент появляется после действия (анимация меню и т. п.)
    public WebElement waitElementIsVisible(By locator) {
        return new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
