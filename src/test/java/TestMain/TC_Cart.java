package TestMain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomepageVstyle;
import support.Browser;

import java.util.List;
import java.util.stream.Collectors;

public class TC_Cart {
    HomepageVstyle home;
    @BeforeClass
    public void set_up()
    {

        home = new HomepageVstyle();
    }
    @BeforeMethod
    public void before_method()
    {
        Browser.launchBrowser("chrome");
        home.open_home_page();
        home.click_icon_close(); //click tắt icon close
    }
    @AfterMethod
    public void affter_method()
    {
        Browser.quit();
    }

    @Test
    public void TC_AddProductToCartInLisProduct()
    {
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();
        Browser.waitForElementVisible(By.xpath("//span[@class = 'bd-cart']"),5000);
        List<WebElement> listcart = Browser.getList(By.xpath("//span[@class = 'bd-cart']")).stream().collect(Collectors.toList());
        String countNumber = Browser.getText(By.xpath("(//span[@class= 'box-icon']/span)[1]"));
        listcart.get(0).click();

        String informSuccess = Browser.getText(By.xpath("//h2"));
        String informAddSuccess = Browser.getElement(By.xpath("//div[@class = 'jq-toast-wrap top-right']/div")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(informSuccess,"Success","Tiêu đề thông báo không đúng");
        softAssert.assertEquals(informAddSuccess,"Sản phẩm đã được thêm vào giỏ hàng.","Thông báo hiển thị ko đúng");
        softAssert.assertEquals(countNumber,countNumber + 1,"Cập nhật số lượng không đúng");
        // Hiển thị sản phẩm đã thêm vào popup

        // Hiển thị sản phẩm ở trong giỏ hàng

        softAssert.assertAll();
    }
    

}
