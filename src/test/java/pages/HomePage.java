package pages;

import models.CartProduct;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.Browser;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    String name = "";
    String price = "";
    String descrip = "";

    private final By listProduct = By.xpath("//div[@class = 'inventory_item_description']");
    private final By nameProduct = By.xpath(".//div[@class='inventory_item_name ']");
    private final By descriProduct = By.xpath(".//div[@class='inventory_item_desc']");
    private final By priceProduct = By.xpath(".//div[@class='inventory_item_price']");
    private final By btnAddToCart = By.xpath(".//button[contains(@id, 'add-to-cart')]");
    private final By cartIcon = By.xpath(".//a[@class = 'shopping_cart_link']/span");


    public List<Product> getProductList() {
        List<WebElement> products = Browser.getList(listProduct);
        List<Product> listproduct = new ArrayList<>();

        for (WebElement p : products) {
            listproduct.add(new Product(
                    p.findElement(nameProduct),
                    p.findElement(descriProduct),
                    p.findElement(priceProduct),
                    p.findElement(btnAddToCart)
            ));
        }

        return listproduct;
    }


    public void clickOneElement(int k) {
        List<Product> products = getProductList();

        if (k >= 0 && k < products.size()) {
             name = products.get(k).getNameProduct().getText();
             descrip = products.get(k).getDescriProduct().getText();
             price = products.get(k).getPriceProduct().getText();
            products.get(k).getBtnAddtoCart().click();
        } else {
            System.out.println("⚠️ Chỉ số sản phẩm không hợp lệ!");
        }
    }

    public String getTextCartIcon() {
        return Browser.getText(cartIcon);
    }
    public String getTextCartRemove(int k) {
        List<WebElement> products = Browser.getList(listProduct);

        if (k >= 0 && k < products.size()) {
            WebElement removeBtn = products.get(k).findElement(By.xpath(".//button[contains(text(), 'Remove')]"));
            return Browser.getTextElement(removeBtn);
        } else {
            return "⚠️ Không tìm thấy sản phẩm!";
        }
    }
    public void navigateToCart() {
        Browser.click(cartIcon);
    }

    public String nameProductAdd() {
        return name;
    }
    public String descripProductAdd() {
        return descrip;
    }
    public String priceProductAdd() {
        return price;
    }
}
