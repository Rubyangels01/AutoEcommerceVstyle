package models;

import org.openqa.selenium.WebElement;

public class Product {
    WebElement nameProduct;
    WebElement descriProduct;

    WebElement priceProduct;
    WebElement btnAddtoCart;
    public Product() {

    }

    public Product(WebElement nameProduct, WebElement descriProduct, WebElement priceProduct, WebElement btnAddtoCart) {
        this.nameProduct = nameProduct;
        this.descriProduct = descriProduct;
        this.priceProduct = priceProduct;
        this.btnAddtoCart = btnAddtoCart;
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

    public WebElement getBtnAddtoCart() {
        return btnAddtoCart;
    }
}
