package TestMain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.DetailPage;
import pages.HomepageVstyle;
import pages.ProductOfCategory;
import support.Browser;
import support.DataProviderUtils;

import java.util.Date;
import java.util.List;

public class TC_ViewDetailProduct {
    private HomepageVstyle home;
    private ProductOfCategory category;
    private DetailPage detailpage;

    @BeforeClass
    public void setUp() {
        home = new HomepageVstyle();
        category = new ProductOfCategory();
        detailpage = new DetailPage();
    }

    @BeforeMethod
    @Parameters("browser")
    public void beforeMethod(String browser) { // Thêm tham số browser
        Browser.launchBrowser(browser); // Sử dụng browser từ parameters
        home.open_home_page();
        home.click_icon_close();

    }



    @AfterMethod
    public void afterMethod() {
        Browser.quit();
    }

    @Test(dataProvider = "firstRowData", dataProviderClass = DataProviderUtils.class)
    public void TC_DetailProductByImage(String expectedimg,
                                        String expectedname,
                                        String expectedventor,
                                        String expectedexpricy,
                                        String expectedPrice,
                                        String expectedOriginalPrice,
                                        String weightProduct) throws InterruptedException {
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();


        category.waiElementTitle();
        Thread.sleep(3000);

        // Không cần giữ list nữa, lấy element trực tiếp luôn khi click
        WebElement firstImg = Browser.getDriver().findElement(By.xpath("(//div[contains(@class,'img-container')])[1]/picture/img"));
        firstImg.click();

        detailpage.waiElementTitle();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(detailpage.getNameProduct(), expectedname);
        softAssert.assertEquals(detailpage.getVentorProduct(),expectedventor);
        softAssert.assertEquals(detailpage.getPriceProduct(),expectedPrice);
        softAssert.assertEquals(detailpage.getOriginalPriceProduct(),expectedOriginalPrice);
        softAssert.assertEquals(detailpage.getExpicyProduct(),expectedexpricy);


        softAssert.assertAll();
    }

    @Test
    public void TC_DetailProductByName()
    {
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();
        category.clickNameProduct();
    }
}
