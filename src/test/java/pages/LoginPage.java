package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.Browser;


public class LoginPage {


    By usernameElement = By.xpath("//input[@id = 'user-name']");
    By passwordElement = By.xpath("//input[@id = 'password']");
    By btnLoginElement = By.xpath("//input[@id = 'login-button']");

    public void open()
    {
        Browser.visit("https://www.saucedemo.com/");
    }
    public void fillUsername()
    {
        Browser.fill(usernameElement,"standard_user");


    }
    public void fillPassword()
    {
        Browser.fill(passwordElement,"secret_sauce");


    }

    public void clickLogin()
    {
        Browser.click(btnLoginElement);
    }
}
