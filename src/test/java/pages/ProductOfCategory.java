package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.Browser;

public class ProductOfCategory {
    private By titlePage = By.xpath("//div[contains(@class, 'collection')]/h1");
    //private
    private By pageName = By.xpath("//span[@itemprop = 'item']/span");
    private By imgProduct = By.xpath("(//div[contains(@class,'img-container')])[1]/picture/img");
    private By ventorProduct = By.xpath("(//div[@class ='pro-vendors'])[1]");
    private By expireProduct = By.xpath("(//i[@class ='product-hsd'])[1]");
    private By nameProduct = By.xpath("(//h3[@class ='pro-name'])[1]/a");
    private By priceProduct = By.xpath("(//p[@class ='pro-price'])[1]/span");
    private By orginalProduct = By.xpath("(//p[@class ='pro-price'])[1]/del");


    public void waiElementTitle()
    {
        Browser.waitForElementVisible(titlePage,3000);
    }
    public boolean displayTitle()
    {
       return Browser.isDisplayed(titlePage);
    }
    public String getTitle()
    {
        return Browser.getText(titlePage);
    }
    public String getNamePage()
    {
        return Browser.getText(pageName);
    }
    public String getImg()
    {
        return Browser.getElement(imgProduct).getAttribute("src");
    }
    public String getVentor()
    {
        return Browser.getText(ventorProduct);
    }
    public String getExpire()
    {
        return Browser.getText(expireProduct);
    }
    public String getName()
    {
        return Browser.getText(nameProduct);
    }
    public String getPrice()
    {
        return Browser.getText(priceProduct);
    }
    public String getOriginalPrice()
    {
        return Browser.getText(orginalProduct);
    }

    public void clickImageProduct()
    {
        Browser.click(imgProduct);
    }
    public void clickNameProduct()
    {
        Browser.click(nameProduct);
    }



}
