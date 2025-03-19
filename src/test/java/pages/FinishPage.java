package pages;

import org.openqa.selenium.By;
import support.Browser;

public class FinishPage {
    By titleElement = By.xpath(".//div[@class = 'header_container']/div/span");


    public boolean isDisplayTitle()
    {
       return Browser.isDiplay(titleElement);
    }


}
