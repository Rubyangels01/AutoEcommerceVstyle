package TestMain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
                                        String weightProduct,String expectedtype,String expectedcode) throws InterruptedException {
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();


        category.waiElementTitle();
        Thread.sleep(3000);

        WebElement firstImg = Browser.getDriver().findElement(By.xpath("(//div[contains(@class,'img-container')])[1]/picture/img"));
        firstImg.click();

        detailpage.waiElementTitle();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(detailpage.getNameProduct(), expectedname);
        softAssert.assertEquals(detailpage.getVentorProduct(),expectedventor);
        softAssert.assertEquals(detailpage.getPriceProduct(),expectedPrice);
        softAssert.assertEquals(detailpage.getOriginalPriceProduct(),expectedOriginalPrice);
        softAssert.assertEquals(detailpage.getExpicyProduct(),expectedexpricy);
        softAssert.assertEquals(detailpage.getWeightProduct(),weightProduct);
        softAssert.assertEquals(detailpage.gettypeProduct(),expectedtype);
        softAssert.assertEquals(detailpage.getCodeProduct(),expectedcode);
        softAssert.assertEquals(detailpage.getQuantity(),"1");
        softAssert.assertAll();
    }

    @Test(dataProvider = "firstRowData", dataProviderClass = DataProviderUtils.class)
    public void TC_DetailProductByName(String expectedimg,
                                       String expectedname,
                                       String expectedventor,
                                       String expectedexpricy,
                                       String expectedPrice,
                                       String expectedOriginalPrice,
                                       String weightProduct,String expectedtype,String expectedcode)
    {
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();
        category.clickNameProduct();
        detailpage.waiElementTitle();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(detailpage.getNameProduct(), expectedname);
        softAssert.assertEquals(detailpage.getVentorProduct(),expectedventor);
        softAssert.assertEquals(detailpage.getPriceProduct(),expectedPrice);
        softAssert.assertEquals(detailpage.getOriginalPriceProduct(),expectedOriginalPrice);
        softAssert.assertEquals(detailpage.getExpicyProduct(),expectedexpricy);
        softAssert.assertEquals(detailpage.getWeightProduct(),weightProduct);
        softAssert.assertEquals(detailpage.gettypeProduct(),expectedtype);
        softAssert.assertEquals(detailpage.getCodeProduct(),expectedcode);
        softAssert.assertEquals(detailpage.getQuantity(),"1");
        softAssert.assertAll();

    }
    @Test
    public void TC_VerifyTransVentorPage()
    {
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();
        category.clickNameProduct();
        detailpage.waiElementTitle();
        detailpage.clickVentor();
        Browser.waitForElementVisible(By.xpath("//div[@class = 'collection-title']/h1"),3000);
        Assert.assertEquals(Browser.getElement(By.xpath("//div[@class = 'collection-title']/h1")),"Calvin Klein");
        Assert.assertEquals(Browser.getCurrentURL(),"https://vstyle.vn/collections/vendors?q=calvin-klein");
        Assert.assertEquals(Browser.getText(By.xpath("//span[@itemprop= 'item']/span")),"Calvin Klein - Vstyle.vn");
    }
    @Test
    public void TC_VerifyTransTypeProductPage()
    {
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();
        category.clickNameProduct();
        detailpage.waiElementTitle();
        detailpage.clickTypeProduct();
        Browser.waitForElementVisible(By.xpath("//div[@class = 'collection-title']/h1"),3000);
        Assert.assertEquals(Browser.getElement(By.xpath("//div[@class = 'collection-title']/h1")),"Calvin Klein");
        Assert.assertEquals(Browser.getCurrentURL(),"https://vstyle.vn/collections/types?q=nuoc-hoa");
        Assert.assertEquals(Browser.getText(By.xpath("//span[@itemprop= 'item']/span")),"Calvin Klein - Vstyle.vn");
    }
    // XÁC MINH SỐ LƯỢNG SẢN PHẨM TĂNG KHI CLICK VÀO DẤU (+)
    @Test
    public void TC_VerifyIncreaseQuantityWhenClickPlus()
    {
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();
        category.clickNameProduct();
        detailpage.waiElementTitle();
        Assert.assertEquals(detailpage.getQuantity(),"1");
        String quantitycurrent = detailpage.getQuantity();
        detailpage.clickPlusIcon();
        String expectedQuantity = String.valueOf(Integer.parseInt(quantitycurrent) + 1);
        Assert.assertEquals(detailpage.getQuantity(),expectedQuantity);

    }
    // XÁC MINH SỐ LƯỢNG GIẢM KHI CLICK VÀO DẤU (-)
    @Test
    public void TC_VerifyIncreaseQuantityWhenClickMinis()
    {
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();
        category.clickNameProduct();
        detailpage.waiElementTitle();


        detailpage.clickMinIcon();

        Assert.assertEquals(detailpage.getQuantity(),"1");
        detailpage.ClearQuantity();
        detailpage.senkeyQuantity("5");
        Assert.assertEquals(detailpage.getQuantity(),"5");
        String quantitycurrent = detailpage.getQuantity();
        detailpage.clickMinIcon();
        String expectedQuantity = String.valueOf(Integer.parseInt(quantitycurrent) - 1);
        Assert.assertEquals(detailpage.getQuantity(),expectedQuantity);

    }
    // THAO TÁC BẰNG TAY VỚI SỐ LƯỢNG SẢN PHẨM


    // XÁC MINH SỐ LƯỢNG SẢN PHẨM HIỂN THỊ MẶC ĐỊNH LÀ 1 SAU KHI NHẤN DẤU (+), QUAY VỀ TRANG DANH SÁCH VÀ TRUY CẬP LẠI TRANG CHI TIẾT





}
