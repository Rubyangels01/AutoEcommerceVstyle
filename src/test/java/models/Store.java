package models;

import org.openqa.selenium.WebElement;

public class Store {
    WebElement nameStore;
    WebElement addressStore;

    WebElement phoneStore;
    WebElement timeOpenStore;
    public Store() {

    }

    public Store(WebElement nameStore, WebElement addressStore, WebElement phoneStore, WebElement timeOpenStore) {
        this.nameStore = nameStore;
        this.addressStore = addressStore;
        this.phoneStore = phoneStore;
        this.timeOpenStore = timeOpenStore;
    }

    public WebElement getNameStore() {
        return nameStore;
    }

    public WebElement getAddressStore() {
        return addressStore;
    }

    public WebElement getPhoneStore() {
        return phoneStore;
    }

    public WebElement getTimeOpenStore() {
        return timeOpenStore;
    }
}
