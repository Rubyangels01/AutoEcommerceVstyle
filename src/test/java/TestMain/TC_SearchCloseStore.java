package TestMain;

import models.Store;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomepageVstyle;
import support.Browser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TC_SearchCloseStore {
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
    public void TC_SearchAllStoreDefault() throws InterruptedException {


        home.click_icon_store_system();// click icon hệ thống cửa hàng
        Thread.sleep(3000);
        String expectedTitle = "TÌM CỬA HÀNG GẦN BẠN";
        String expectedDefaultCity = "Chọn tỉnh/thành phố";
        String expectedDefaultDistrict = "Chọn Quận/huyện";
        Assert.assertTrue(home.display_popup()); // hiển thị popup
        Assert.assertEquals(home.get_title_popup().toUpperCase(),expectedTitle); // kiểm tra hiển thị title của popup
        Assert.assertEquals(home.get_city_name(),expectedDefaultCity); // kiểm tra hiển thị thành phố mặc định
        Assert.assertEquals(home.get_district_name(),expectedDefaultDistrict); // kiểm tra hiển thị quận huyện mặc định
        // kiểm tra tổng số lượng cửa hàng hiển thị

    }
    // Kiểm tra hiển thị tất cả cửa hàng thuộc một thành phố
    @Test
    public void TC_DisplayAllStoreOfCity() throws InterruptedException {
        home.click_icon_store_system(); // click vào icon
        Thread.sleep(3000);
        home.click_select_city();
        Assert.assertTrue(home.is_selected_city()); // kiểm tra mặc định c đang được chọn

        home.select_hcm_city("ho-chi-minh"); // Chọn tirnh hồ chí minh
        Thread.sleep(5000);
        Assert.assertEquals(home.get_city_name(),"Hồ Chí Minh"); // Kiểm tra hồ chí minh có đang được chọn
        int expected_sizelist = 8;
        Assert.assertEquals(home.getStoreList().size(),expected_sizelist); // Kiểm tra số lươnng cửa hàng có tại thành phố hồ chí minh
        List<String> sortedItems = home.listOfDistrict().stream().map(WebElement::getText).toList();
        List<String> expected = Arrays.asList("Chọn Quận/huyện","Quận Thủ Đức", "Quận 1","Quận 2","Quận 7", "Quận Tân Bình","Quận 6", "Gò Vấp");
        Assert.assertEquals(sortedItems,expected,"List is not semilar");// Kiểm tra danh sách quận hiển thị đúng mong đợi
    }

    // KIỂM TRA HIỂN THỊ TẤT CẢ CÁC CỬA HÀNG CỦA 1 QUẬN
    @Test
    public void TC_DisplayStoreOfDistrict() throws InterruptedException {
        home.click_icon_store_system();
        Thread.sleep(3000);
        home.click_select_city();
        home.select_hcm_city("ho-chi-minh");
        //Kiểm tra quận đang mặc định đang laf chọn Quận/huyện
        Assert.assertEquals(home.get_district_name(),"Chọn Quận/huyện");
        home.select_district("quan-1");
        //Kiểm tra quận 1 đã được chọn
        Assert.assertEquals(home.get_district_name(),"Quận 1");
        // Kiểm tra số lượng danh sách cửa hàng đúng với mong đợi

        Thread.sleep(5000);
        int expected_numberstoreofdistrict1 = 2;
        Assert.assertEquals(home.getStoreList().size(),2);
        // Lấy danh sách địa chỉ cửa hàng thuộc quận 1 ko
        List<String> actual_listaddressstore = home.getStoreList().stream().map(store -> store.getAddressStore().getText()).toList();
        //Kiểm tra tất cả cửa hàng có phải đúng là quận 1 ko
        boolean allContainDistrict1 = actual_listaddressstore.stream().allMatch(s -> s.contains("Quận 1"));
        Assert.assertTrue(allContainDistrict1);

        home.select_district("quan-thu-duc"); // Chonj quanj thu duc
        Thread.sleep(5000);
        Assert.assertEquals(home.get_district_name(),"Quận Thủ Đức");// kiểm tra xem quận đang được chọn có là quận thủ đức hay ko
        Assert.assertTrue(CheckContainAddress("Quận Thủ Đức"),"List is empty");

        home.select_district("quan-2"); // Chonj quanj thu duc
        Thread.sleep(5000);
        Assert.assertEquals(home.get_district_name(),"Quận 2");// kiểm tra xem quận đang được chọn có là quận thủ đức hay ko
        Assert.assertTrue(CheckContainAddress("Quận 2"),"List is empty");

        home.select_district("quan-7"); // Chonj quanj thu duc
        Thread.sleep(5000);
        Assert.assertEquals(home.get_district_name(),"Quận 7");// kiểm tra xem quận đang được chọn có là quận thủ đức hay ko
        Assert.assertTrue(CheckContainAddress("Quận 7"),"List is empty");

        home.select_district("quan-tan-binh"); // Chonj quanj thu duc
        Thread.sleep(5000);
        Assert.assertEquals(home.get_district_name(),"Quận Tân Bình");// kiểm tra xem quận đang được chọn có là quận thủ đức hay ko
        Assert.assertTrue(CheckContainAddress("Quận Tân Bình"),"List is empty");

        home.select_district("quan-6"); // Chonj quanj thu duc
        Thread.sleep(5000);
        Assert.assertEquals(home.get_district_name(),"Quận 6");// kiểm tra xem quận đang được chọn có là quận thủ đức hay ko
        Assert.assertTrue(CheckContainAddress("Quận 6"),"List is empty");

        home.select_district("go-vap"); // Chonj quanj thu duc
        Thread.sleep(5000);
        Assert.assertEquals(home.get_district_name(),"Gò Vấp");// kiểm tra xem quận đang được chọn có là quận thủ đức hay ko
        Assert.assertTrue(CheckContainAddress("Gò Vấp"),"List is empty");




    }
    // KIỂM TRA HIỂN THỊ TOÀN BỘ CỦA HÀNG THUỘC THÀNH PHỐ HỒ CHÍ MINH KHI CHỌN LẠI ITEM MẠC ĐINH "CHỌN QUẬN/HUYỆN
    @Test
    public void TC_DisplayAllStoreOfCitychooseDefaultDistrictItem() throws InterruptedException {
        home.click_icon_store_system();
        home.select_hcm_city("ho-chi-minh");
        home.select_district("quan-1");
        home.select_district("all");
        Thread.sleep(3000);
        Assert.assertEquals(home.get_district_name(),"Chọn Quận/huyện"); // kiểm tra item mặc định có được chọn chưa
//        List<String>  actualStore = Optional.ofNullable(home.getStoreList()).orElseGet(List::of).stream().map(s->s.getAddressStore().getText()).collect(Collectors.toList());
//        List<String> expectedStore = Arrays.asList("Hồ Chí Minh - Vstyle Gigamall", "Hồ Chí Minh - Vstyle Vincom Center Đồng Khởi");
//        Assert.assertEquals(actualStore, expectedStore,"Actual Store is not match expected Store list");
    }


    // KIỂM TRA HIỂN THỊ DANH SÁCH CỬA HÀNG KHI CHỌN MỘT THÀNH PHỐ KHAC THÀNH PHỐ HIỆN TẠI
    @Test
    public void TC_DisplayAllStoreWhenChooseItemDefault()
    {
        home.click_icon_store_system();
        home.select_hcm_city("ho-chi-minh");
        home.select_district("quan-1");
        home.select_hcm_city("ha-noi");

        // Kiểm tra quận hà nội đã được chọn
        Assert.assertEquals(home.get_city_name(),"Hà Nội");
        // Hiển thị toàn bộ quận của hà nội
        List<String> actual_district = home.listOfDistrict().stream().map(WebElement::getText).toList();
        List<String> expected = Arrays.asList("Chọn Quận/huyện", "Thanh Xuân","Gia Lâm");
        Assert.assertEquals(actual_district,expected);
        // Hiển thị item mặc định của quận
        Assert.assertEquals(home.get_district_name(),"Chọn Quận/huyện");
        // Hiển thị toàn bôj cửa hàng tại quận
        Assert.assertTrue(CheckContainAddress("Hà Nội"));

    }
    // KIỂM TRA HIỂN THỊ ĐÚNG THÔNG TIN CỦA 1 CỬA HÀNG CỤ THE
    @Test
    public void TC_DisplayInforOfStore() throws InterruptedException {
        home.click_icon_store_system();
        home.select_hcm_city("hung-yen");
        home.select_district("van-giang");
        Thread.sleep(5000);
        String expected_namestore = "Hưng Yên - Vstyle Little Hong Kong";
        String expected_address = "Biệt thự SH8.SP9-27-31, Little Hong Kong, KĐT Vinhomes Ocean Park 2, Huyện Văn Giang, Tỉnh Hưng Yên";
        String expected_phone = "19009048";
        String expected_timeopen = "Thời gian hoạt động: 9:30 - 22:00";
        Store actual_store = home.getStoreList() == null ? null : home.getStoreList().stream().findFirst().orElse(null);
        Assert.assertEquals(actual_store.getNameStore().getText(),expected_namestore,"Name is not match");
        System.out.println(actual_store.getPhoneStore().getText());
        Assert.assertEquals(actual_store.getAddressStore().getText(),expected_address,"Address is not match");
        Assert.assertEquals(actual_store.getPhoneStore().getText(),expected_phone,"Phone is not match");
        Assert.assertEquals(actual_store.getTimeOpenStore().getText(),expected_timeopen,"Open time is not match");
    }

    // KIỂM TRA HIỂN THỊ VÀ ẨN THANH DỌC KÉO LÊN KHI DANH SÁCH NHIỀU HƠN 2 CỬA HÀNG
    @Test
    public void TC_DisplayPullbar() throws InterruptedException {
        home.click_icon_store_system();
        home.select_hcm_city("ho-chi-minh");
        Thread.sleep(5000);
        // Xác thực hiển thị thanh dọc kéo
        Assert.assertTrue(home.check_scroll());
        // Xác thực ấn thanh dọc kéo
        home.select_hcm_city("ha-noi");
        Assert.assertFalse(home.check_scroll());
    }

    public boolean CheckContainAddress(String s1)
    {
        List<String> actual_listaddressstore1 = home.getStoreList().stream().map(store -> store.getAddressStore().getText()).toList();
        return actual_listaddressstore1.stream().allMatch(s -> s.contains(s));
    }












}
