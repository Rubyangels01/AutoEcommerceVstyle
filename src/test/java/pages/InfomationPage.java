package pages;

import org.openqa.selenium.By;
import support.Browser;

public class InfomationPage {
    By firstNameElement = By.xpath("//input[@id = 'first-name']");
    By lastNameElement = By.xpath("//input[@id = 'last-name']");
    By postalcodeElement = By.xpath("//input[@id = 'postal-code']");
    By btnContinue = By.xpath("//input[@id = 'continue']");

    public void fillFirstname()
    {
        Browser.fill(firstNameElement,"Nhung");


    }
    public void fillLastName()
    {
        Browser.fill(lastNameElement,"Nguyen");
    }

    public void fillpostalCode()
    {
        Browser.fill(postalcodeElement,"1234");
    }

    public void clickContinue()
    {
        Browser.click(btnContinue);
    }
}
