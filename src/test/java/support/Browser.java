package support;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Browser {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<Actions> actions = new ThreadLocal<>();

    public static WebDriver launchBrowser(String name) {
        WebDriver newDriver;
        switch (name.toLowerCase()) {
            case "chrome":
                newDriver = new ChromeDriver();
                break;
            case "firefox":
                newDriver = new FirefoxDriver();
                break;
            case "safari":
                newDriver = new SafariDriver();
                break;
            default:
                throw new IllegalStateException("Unexpected browser: " + name);
        }

        newDriver.manage().window().maximize();
        newDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.set(newDriver);
        actions.set(new Actions(newDriver));
        return newDriver;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void visit(String url) {
        getDriver().get(url);
    }

    public static void click(By locator) {
        getDriver().findElement(locator).click();
    }

    public static void fill(By locator, String message) {
        getDriver().findElement(locator).sendKeys(message);
    }

    public static String getText(By locator) {
        return getDriver().findElement(locator).getText();
    }

    public static List<WebElement> getList(By locator) {
        return getDriver().findElements(locator);
    }

    public static void moveAndClickElement(By locator) {
        actions.get().moveToElement(getDriver().findElement(locator)).click().perform();
    }

    public static WebElement getElement(By locator) {
        return getDriver().findElement(locator);
    }

    public static void clickElement(WebElement element) {
        element.click();
    }

    public static boolean isDisplayed(By locator) {
        return getDriver().findElement(locator).isDisplayed();
    }

    public static String getTextElement(WebElement e) {
        return e.getText();
    }

    public static String getFirstOfSelect(By locator) {
        return new Select(getDriver().findElement(locator)).getFirstSelectedOption().getText();
    }

    public static List<WebElement> getListOfSelect(By locator) {
        return new Select(getDriver().findElement(locator)).getOptions();
    }

    public static void selectItemByValue(By locator, String value) {
        new Select(getDriver().findElement(locator)).selectByValue(value);
    }

    public static boolean isSelected(By locator) {
        return getDriver().findElement(locator).isSelected();
    }

    public static String getBackgroundColorByJS(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (String) js.executeScript(
                "return window.getComputedStyle(arguments[0]).backgroundColor",
                getDriver().findElement(locator)
        );
    }

    public static boolean hasScroll(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement element = getDriver().findElement(locator);
        long scrollHeight = (long) js.executeScript("return arguments[0].scrollHeight", element);
        long clientHeight = (long) js.executeScript("return arguments[0].clientHeight", element);
        return scrollHeight > clientHeight;
    }
    public static List<WebElement> waitForElementsVisible(By locator, long timeoutInSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public static WebElement waitForElementVisible(By locator, long timeoutInSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void hoverElement(WebElement e)
    {
        actions.get().moveToElement(e).perform();
    }
    public static void hoverElement(By e)
    {
        actions.get().moveToElement(getDriver().findElement(e)).perform();
    }
    public static void hoverElementAndClick(By e)
    {

        actions.get().moveToElement(getDriver().findElement(e)).click().perform();
    }
    public static String getColorOfElement(By locator)
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String color = (String) js.executeScript(
                "return window.getComputedStyle(arguments[0]).color;", getDriver().findElement(locator));
        return color;
    }
    public static String getCurrentURL()
    {
        return getDriver().getCurrentUrl();
    }


    public static void quit() {
        if (driver.get() != null) {
            getDriver().quit();
            driver.remove();
            actions.remove();
        }
    }
}