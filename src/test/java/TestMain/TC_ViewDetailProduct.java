package TestMain;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.DetailPage;
import pages.HomepageVstyle;
import pages.ProductOfCategory;
import support.Browser;
import support.DataProviderUtils;

import java.time.Duration;
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
        softAssert.assertEquals(detailpage.getNameProduct(), expectedname,"Tên sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getVentorProduct(),expectedventor,"Thương hiệu sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getPriceProduct(),expectedPrice,"Giá sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getOriginalPriceProduct(),expectedOriginalPrice,"Giá gốc sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getExpicyProduct(),expectedexpricy,"Hạn sử dụng sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getWeightProduct(),weightProduct,"Khối lượng sản phẩm không đúng");
        softAssert.assertEquals(detailpage.gettypeProduct(),expectedtype,"Loại sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getCodeProduct(),expectedcode,"Mã sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getQuantity(),"1","Số lượng sản phẩm mặc định phải là 1");
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
        softAssert.assertEquals(detailpage.getNameProduct(), expectedname,"Tên sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getVentorProduct(),expectedventor,"Thương hiệu sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getPriceProduct(),expectedPrice,"Giá sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getOriginalPriceProduct(),expectedOriginalPrice,"Giá gốc sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getExpicyProduct(),expectedexpricy,"Hạn sử dụng sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getWeightProduct(),weightProduct,"Khối lượng sản phẩm không đúng");
        softAssert.assertEquals(detailpage.gettypeProduct(),expectedtype,"Loại sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getCodeProduct(),expectedcode,"Mã sản phẩm không đúng");
        softAssert.assertEquals(detailpage.getQuantity(),"1","Số lượng sản phẩm mặc định phải là 1");
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
        Assert.assertEquals(Browser.getElement(By.xpath("//div[@class = 'collection-title']/h1")),"Calvin Klein","Tên thương hiệu không đúng");
        Assert.assertEquals(Browser.getCurrentURL(),"https://vstyle.vn/collections/vendors?q=calvin-klein","URL không đúng");
        Assert.assertEquals(Browser.getText(By.xpath("//span[@itemprop= 'item']/span")),"Calvin Klein - Vstyle.vn","Tên page không đúng");
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
        Assert.assertEquals(detailpage.getQuantity(),"1","Số lượng ban đầu phải bằng 1");
        String quantitycurrent = detailpage.getQuantity();
        detailpage.clickPlusIcon();
        String expectedQuantity = String.valueOf(Integer.parseInt(quantitycurrent) + 1);
        Assert.assertEquals(detailpage.getQuantity(),expectedQuantity,"Số lượng sản phẩm sau tăng phải bằng 2");

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

    @Test(dataProvider = "quantityData", dataProviderClass = DataProviderUtils.class)
    public void TC_ValidQuatity(String a)
    {
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();
        category.clickNameProduct();
        detailpage.waiElementTitle();

        Assert.assertEquals(detailpage.getQuantity(),"1","Số lượng ban đầu phải là 1");
        detailpage.ClearQuantity();
        detailpage.senkeyQuantity(a);

        Assert.assertEquals(detailpage.getQuantity(),a,"Số lượng được nhập ko đúng");

    }
    @Test(dataProvider = "quantityDataInvalid", dataProviderClass = DataProviderUtils.class)
    public void TC_InValidQuantity(String invalidQuantity) {
        // 1. Arrange - Chuẩn bị dữ liệu và thực hiện các bước chung
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();
        category.clickNameProduct();
        detailpage.waiElementTitle();

        // Verify initial quantity is 1
        Assert.assertEquals(detailpage.getQuantity(), "1", "Số lượng ban đầu phải là 1");

        // 2. Act - Thực hiện thao tác nhập số lượng không hợp lệ
        detailpage.ClearQuantity();
        detailpage.senkeyQuantity(invalidQuantity);

        // 3. Assert - Kiểm tra kết quả
        if (invalidQuantity.equals("400") || invalidQuantity.equals("999")) {
            handleQuantityAlert();
        }

        // Verify quantity is reset to 1 after invalid input
        Assert.assertEquals(detailpage.getQuantity(), "1", "Số lượng phải được reset về 1 sau khi nhập giá trị không hợp lệ");
    }

    private void handleQuantityAlert() {
        try {
            // Wait for alert to be present
            WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            // Verify alert message
            String expectedMessage = "Cart Error(422): You can only add 202 Nước Hoa Chiết Calvin Klein Be EDT 10ml to the cart";
            Assert.assertEquals(alert.getText(), expectedMessage, "Nội dung alert không chính xác");

            // Accept alert
            alert.accept();

            // Verify alert is closed
            wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        } catch (TimeoutException e) {
            Assert.fail("Alert không xuất hiện trong thời gian chờ");
        }
    }

    // XÁC MINH SỐ LƯỢNG SẢN PHẨM HIỂN THỊ MẶC ĐỊNH LÀ 1 SAU KHI NHẤN DẤU (+), QUAY VỀ TRANG DANH SÁCH VÀ TRUY CẬP LẠI TRANG CHI TIẾT
    @Test
    public void TC_VerifyDislayQuantityDefault()
    {
        home.hoverCategoryNuocHoa();
        home.hoverCategoryNuocHoaNu();
        home.clickCategoryNuocHoaNu();
        category.clickNameProduct();
        detailpage.waiElementTitle();


        Assert.assertEquals(detailpage.getQuantity(),"1");
        detailpage.clickPlusIcon();
        Browser.getDriver().navigate().back();
        Assert.assertEquals(Browser.getCurrentURL(),"https://vstyle.vn/collections/nuoc-hoa-nu");
        category.clickNameProduct();
        detailpage.waiElementTitle();
        Assert.assertEquals(detailpage.getQuantity(),"1");
    }






}
