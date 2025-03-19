package support;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

public class Browser {
    public static Actions actions;
    private static WebDriver driver;
    public static WebDriver launchBrowser(String name)
    {
        switch (name){
            case "chrome":{
                driver = new ChromeDriver();
                actions = new Actions(driver);
                break;
            }
            case "firefox":{
                driver = new FirefoxDriver();
                break;
            }
            case "safari":{
                driver = new SafariDriver();
                break;
            }
            default:
                throw new IllegalStateException("Unpected Value: " + name);
        }
        return driver;
    }

    public static void visit(String url)
    {
        driver.navigate().to(url);
    }
    public static void click(By locator)
    {
        driver.findElement(locator).click();
    }
    public static void fill(By locator, String message)
    {
        driver.findElement(locator).sendKeys(message);
    }
    public static String getText(By locator)
    {
       return driver.findElement(locator).getText();
    }
    public static List<WebElement> getList(By locator) {
        return driver.findElements(locator);
    }
   public static void moveandclickElement(By locator)
   {
       actions.moveToElement(driver.findElement(locator)).click().perform();
   }


    public static WebElement getElement(By locator)
    {
        return driver.findElement(locator);
    }
    public static void ClickElement (WebElement element)
    {
        element.click();
    }
    public static boolean isDiplay(By locator)
    {
       return driver.findElement(locator).isDisplayed();
    }
    public static String getTextElement(WebElement e)
    {
        return  e.getText();
    }


    public static void quit()
    {
        if(driver != null)
        {
            driver.quit();
        }
    }

}
