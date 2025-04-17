package pages;

import org.openqa.selenium.By;
import support.Browser;

public class DetailPage {
    private By nameProduct = By.xpath("//div[contains(@class,'product-title')]/h1");
    private By ventorProduct = By.xpath("//div[contains(@class,'pro-brand')]/a");
    private By typeProduct = By.xpath("//div[contains(@class,'pro-type')]/a");
    private By codeProduct = By.xpath("//div[contains(@class,'pro-sku')]/span[2]");
    private By expicyProduct = By.xpath("//span[@class= 'title']");
    private By priceProduct = By.xpath("//span[@class= 'price-now']");
    private By orginalProduct = By.xpath("//span[@class= 'price-compare']/del");
    private By weightProduct = By.xpath("//div[@class='header']/span");
    private By btnAddProduct = By.xpath("//button [@name ='add'][1]");
    private By btnBuyProduct = By.xpath("//button [@name ='add'][2]");
    private By quantityminis = By.xpath("//input[contains(@class,'btn qtyminus')]");
    private By quantityplus = By.xpath("//input[contains(@class,'btn qtyplus')]");
    private By quantity = By.id("quantity");

    public void waiElementTitle()
    {
        Browser.waitForElementVisible(nameProduct,3000);
    }
    public String getNameProduct()
    {
        return Browser.getText(nameProduct);
    }
    public String getVentorProduct()
    {
        return Browser.getText(ventorProduct);
    }
    public String gettypeProduct()
    {
        return Browser.getText(typeProduct);
    }
    public String getCodeProduct()
    {
        return Browser.getText(codeProduct);
    }
    public String getExpicyProduct()
    {
        return Browser.getText(expicyProduct);
    }
    public String getPriceProduct()
    {
        return Browser.getText(priceProduct);
    }
    public String getOriginalPriceProduct()
    {
        return Browser.getText(orginalProduct);
    }
    public String getWeightProduct()
    {
        return Browser.getText(weightProduct);
    }
    public String getQuantity()
    {
        return Browser.getElement(quantity).getAttribute("value");

    }
    public void clickVentor()
    {
         Browser.click(ventorProduct);
    }
    public void clickTypeProduct()
    {
        Browser.click(typeProduct);
    }
    public void clickPlusIcon()
    {
        Browser.click(quantityplus);
    }
    public void clickMinIcon()
    {
        Browser.click(quantityminis);
    }
    public void senkeyQuantity(String s)
    {
        Browser.fill(quantity,s);
    }
    public void ClearQuantity()
    {
        Browser.getDriver().findElement(By.xpath("//input[@id='quantity']")).clear();

    }


}
