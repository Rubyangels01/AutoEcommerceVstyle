package pages;

import models.CartProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import support.Browser;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage {
    By listProduct = By.xpath("//div[@class = 'cart_item_label']");
    By nameProduct = By.xpath(".//div[@class = 'inventory_item_name']");
    By descriProduct = By.xpath(".//div[@class = 'inventory_item_desc']");
    By priceProduct = By.xpath(".//div[@class = 'inventory_item_price']");
    By btnFinish = By.xpath("//button[@id= 'finish']");


    public List<CartProduct> getProductList() {
        List<WebElement> products = Browser.getList(listProduct);
        List<CartProduct> listproduct =  new ArrayList<>();
        for(WebElement p : products)
        {
            WebElement name = p.findElement(nameProduct);
            WebElement descri = p.findElement(descriProduct);
            WebElement price = p.findElement(priceProduct);
            CartProduct product = new CartProduct(name,descri,price);
            listproduct.add(product);
        }
        return  listproduct;
    }

    public void clickFinish()
    {
        Browser.moveandclickElement(btnFinish);
    }
}
