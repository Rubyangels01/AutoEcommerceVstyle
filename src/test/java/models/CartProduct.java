package models;

import org.openqa.selenium.WebElement;

public class CartProduct {
    WebElement nameProduct;
    WebElement descriProduct;

    WebElement priceProduct;

    public CartProduct() {

    }

    public CartProduct(WebElement nameProduct, WebElement descriProduct, WebElement priceProduct) {
        this.nameProduct = nameProduct;
        this.descriProduct = descriProduct;
        this.priceProduct = priceProduct;

    }



    public WebElement getNameProduct() {
        return nameProduct;
    }

    public WebElement getDescriProduct() {
        return descriProduct;
    }

    public WebElement getPriceProduct() {
        return priceProduct;
    }


}
