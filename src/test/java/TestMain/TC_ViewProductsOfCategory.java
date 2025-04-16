package TestMain;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomepageVstyle;
import pages.ProductOfCategory;
import support.Browser;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TC_ViewProductsOfCategory {
    private HomepageVstyle home;
    private ProductOfCategory category;

    @BeforeClass
    public void setUp() {
        home = new HomepageVstyle();
        category = new ProductOfCategory();
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

    @Test
    public void TC_DisplayAllProductOfParentCategory() throws InterruptedException {
        // 1. Chờ đợi explicit thay vì sleep cứng

       // Browser.waitForElementsVisible(home.getCategoryLocator(), 10);
        Thread.sleep(3000);

        // 2. Lấy danh sách category
        List<String> actualCategories = home.listOfCategory()
                .stream()
                .map(WebElement::getText)
                .map(String::trim) // Loại bỏ khoảng trắng thừa
                .collect(Collectors.toList());

        // 3. Danh sách expected
        List<String> expectedCategories = Arrays.asList(
                "⚡Flash Sale",
                "Nước Hoa",
                "Trang Điểm",
                "Chăm Sóc Da Mặt",
                "Chăm Sóc Cơ Thể",
                "Thời Trang",
                "Phụ Kiện",
                "Nhà Cửa & Đời Sống",
                "Thực Phẩm",
                "Tất cả"
        );
        SoftAssert softAssert = new SoftAssert();
        // 4. Assert với message rõ ràng
        softAssert.assertEquals(
                actualCategories,
                expectedCategories,
                "Danh sách category không khớp với mong đợi"
        );
      //home.hoverCategory(home.listOfCategory().get(1));
      // so sánh màu sắc ban đầu của danh mục trước khi hover là màu đen và sau khi hover là màu xanh

        String actualcolorbeforehover = home.getCategoryElement().getCssValue("color");
        String expectedColorDefault = "rgba(0, 0, 0, 1)";
        softAssert.assertEquals(actualcolorbeforehover,expectedColorDefault,"Color both is not match");
        home.hoverCategoryNuocHoa();
        Thread.sleep(3000);
        String expectedColor = "rgb(0, 100, 50)";
        softAssert.assertEquals(home.getColorOfNuocHoa(),expectedColor,"Color both is not match");
        // click vào danh mục
        home.clickCategoryNuocHoa();
        category.waiElementTitle();
        // Xác thực tiêu đề của trang
        String expectedTitle = "Nước Hoa";
        softAssert.assertEquals(category.getTitle(),expectedTitle,"Title ís not match");
        softAssert.assertEquals(Browser.getCurrentURL(),"https://vstyle.vn/collections/nuoc-hoa","URL is not match");
        // Xác thực danh sách sản phẩm

        softAssert.assertAll();

    }
    // KIỂM TRA HIỂN THỊ DANH SÁCH SẢN PHẨM THEO TỪNG DANH MỤC CON
    @Test
    public void TC_displayListOfSubcategory() throws InterruptedException {
        Thread.sleep(3000);
        SoftAssert softAssert = new SoftAssert();
        home.hoverCategoryNuocHoa();
        // Kiểm tra hiển thị đầy đủ danh mục con
        List<String> actual_subcategory = home.listofSubCategory().stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> expected_SubCategory = Arrays.asList("Nước Hoa Nữ","Nước Hoa Nam", "Nước Hoa Unisex","Nước Hoa Mini");
        softAssert.assertEquals(actual_subcategory,expected_SubCategory,"List is both not match");
        home.hoverCategoryNuocHoaNu();
        Thread.sleep(3000);
        String expectedColor = "rgb(0, 100, 50)";
        softAssert.assertEquals(home.getColorOfNuocHoaNu(),expectedColor,"Color both is not match");
        home.clickCategoryNuocHoaNu();
        category.waiElementTitle();
        String expectedTitle = "Nước hoa nữ";
        softAssert.assertEquals(category.getTitle(),expectedTitle,"Title ís not match");
        softAssert.assertEquals(Browser.getCurrentURL(),"https://vstyle.vn/collections/nuoc-hoa-nu","URL is not match");
        softAssert.assertAll();


    }
    // KIỂM TRA HIỂN THỊ CHÍNH XÁC THÔNG TIN CỦA 1 SẢN PHẨM CỤ THỂ
    // MAI KIỂM TRA LẠI
    @Test
    public void TC_displayInformationOfProduct() throws InterruptedException {
        Thread.sleep(3000);
        SoftAssert softAssert = new SoftAssert();
        home.hoverCategoryNuocHoa();
        Thread.sleep(3000);
        home.hoverCategoryNuocHoaNam();
        System.out.println(Browser.getCurrentURL());
        home.clickCategoryNuocHoaNam();
        Thread.sleep(3000);
        softAssert.assertEquals(Browser.getCurrentURL(),"https://vstyle.vn/collections/nuoc-hoa-nam","Current URL is not correctly");

        category.waiElementTitle();
        softAssert.assertEquals(category.getNamePage(),"Nước hoa nam","Page Name is not correctly");
        softAssert.assertEquals(category.getImg(),"https://file.hstatic.net/200000397757/file/lazyload_e95df2e69ca44092831654bec491fb77_large.jpg","Image is not correctly");
        softAssert.assertEquals(category.getVentor(),"CALVIN KLEIN","Ventor is not correctly");
        softAssert.assertEquals(category.getExpire(),"HSD trên 16 tháng","Expire is not correctly");
        softAssert.assertEquals(category.getName(),"Sáp Khử Mùi Calvin Klein Be 75G","Name is not correctly");
        softAssert.assertEquals(category.getPrice(),"350,000₫","Price is not correctly");
        softAssert.assertEquals(category.getOriginalPrice(),"700,000₫","Orginal Price is not correctly");
        softAssert.assertAll();

    }



}