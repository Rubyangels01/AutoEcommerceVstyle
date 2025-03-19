package pages;

import models.CartProduct;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.Browser;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    public static CartProduct pro =  new CartProduct();


    By listProduct = By.xpath("//div[@class = 'cart_item_label']");
    By nameProduct = By.xpath(".//div[@class = 'inventory_item_name']");
    By descriProduct = By.xpath(".//div[@class = 'inventory_item_desc']");
    By priceProduct = By.xpath(".//div[@class = 'inventory_item_price']");
    By btnCheckout = By.xpath(".//button[@id = 'checkout']");

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

    public List<WebElement> getProductList1() {
        List<WebElement> products = Browser.getList(listProduct);
        for(WebElement p : products)
        {
            System.out.println(p.findElement(priceProduct).getText());
        }
        return  products;
    }
    public CartProduct getProductJustAdd() {
        CartProduct a = new CartProduct();
        a = getProductList().getLast();
        return a;
    }

    public void clickCheckout()
    {
        Browser.click(btnCheckout);
    }

}
