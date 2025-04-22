package pages;

import models.CartProduct;
import models.Store;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.Browser;

import java.util.ArrayList;
import java.util.List;

public class HomepageVstyle {
    private By iconshopsystem = By.xpath("(//a[@class='group-icon-item d-flex d-flex-center '])[2]");
    private By popup = By.xpath("(//div[@class = 'header-dropdown_content'])[3]");

    private By searchtitlename = By.xpath("(.//div[text() = 'Tìm cửa hàng gần bạn'])[2]");
    private By selectCity = By.xpath("(//select[@name='change-tinh'])[2]");
    private By selectDistrict = By.xpath("(//select[@name='select-quan'])[2]");
    private By closepopup = By.xpath("//span[@class = 'close']");
    private By listofstore = By.xpath("(//div[@class='address-count mg-top-15'])[2]/div/ul/li[@style = 'display: list-item;']");
    private By nameStore = By.xpath("(//div[@class='address-count mg-top-15'])[2]/div/ul/li[@style = 'display: list-item;']/a/b");
    private By addressStore = By.xpath("(//div[@class='address-count mg-top-15'])[2]/div/ul/li[@style = 'display: list-item;']/a[1]/span[1]");
    private By phoneStore = By.xpath("(//div[@class='address-count mg-top-15'])[2]/div/ul/li[@style = 'display: list-item;']/a[1]/span[2]");
    private By timeOpenStore = By.xpath("(//div[@class='address-count mg-top-15'])[2]/div/ul/li[@style = 'display: list-item;']/a[1]/span[3]");
    private By optionAll = By.xpath("(//option[@value = 'all'])[3]");
    private By optionAllDistrict = By.xpath("(//option[@value = 'all'])[4]");
    private By container = By.xpath("(//div[@class = 'address-detail'])[2]");
    private By category_nav = By.xpath("(//div[@class = 'container'])[2]/div/nav/ul/li");
    private By category_Nuochoa = By.xpath("//a[text() = ' Nước Hoa']");
    private By subcategoryNuocHoa = By.xpath("(//li[@class =' '])[2]/div/ul/li");
    private By subcategoryNuocHoaNu = By.xpath("((//li[@class =' '])[2]/div/ul/li/a)[1]");
    private By subcategoryNuocHoaNam = By.xpath("//ul[contains(@class, 'js-center ')]/li[2]/div/ul/li[2]");
    private By iconCart = By.xpath("(//div[contains(@class,'list-inline-item-text')])[5]/a");


    public By getIconshopsystem() {
        return iconshopsystem;
    }

    public By getSearchtitlename() {
        return searchtitlename;
    }

    public By getSelectCity() {
        return selectCity;
    }

    public By getSelectDistrict() {
        return selectDistrict;
    }

    public List<Store> getStoreList() {
//        List<WebElement> stores = Browser.getList(listofstore);
//        List<Store> listStores =  new ArrayList<>();
//        for(WebElement p : stores)
//        {
//            System.out.println(p);
//            WebElement name = p.findElement(nameStore);
//            WebElement address = p.findElement(addressStore);
//            WebElement phone = p.findElement(phoneStore);
//            WebElement timeopen = p.findElement(timeOpenStore);
//            Store s = new Store(name,address,phone,timeopen);
//            listStores.add(s);
//        }
        List<Store> listStores = Browser.getList(listofstore).stream()
                .map(p -> {
                    WebElement name = p.findElement(nameStore);
                    WebElement address = p.findElement(addressStore);
                    WebElement phone = p.findElement(phoneStore);
                    WebElement timeopen = p.findElement(timeOpenStore);
                    return new Store(name,address,phone,timeopen); // Giả sử có constructor Store(String, String, String)
                })
                .toList();

        return  listStores;
    }
    public List<WebElement> listOfCategory()
    {
        return Browser.getList(category_nav);
    }


    public void open_home_page()
    {Browser.visit("https://vstyle.vn/");}
    public void click_icon_store_system()
    {
        Browser.click(iconshopsystem);
    }
    public void click_icon_close()
    {
        Browser.click(closepopup);
    }
    public String get_title_popup()
    {
       return Browser.getText(searchtitlename);
    }
    public boolean display_popup()
    {
        return Browser.isDisplayed(popup);
    }
    public String get_city_name()
    {
        return Browser.getFirstOfSelect(selectCity);
    }
    public String get_district_name()
    {
        return Browser.getFirstOfSelect(selectDistrict);
    }
    public void click_select_city()
    {
        Browser.click(selectCity);
    }
    public boolean is_selected_city()
    {
        return Browser.isSelected(optionAll);
    }
    public boolean is_selected_default_district()
    {
        return Browser.isSelected(optionAllDistrict);
    }


    public String get_background_select()
    {

        return Browser.getBackgroundColorByJS(optionAll);
    }
    public void select_hcm_city(String s)
    {
        Browser.selectItemByValue(selectCity,s);
    }
    public List<WebElement> listofselectcity()
    {
        return Browser.getListOfSelect(selectCity);
    }
    public List<WebElement> listOfDistrict()
    {
        return Browser.getListOfSelect(selectDistrict);
    }
    public void select_district(String district)
    {
        Browser.selectItemByValue(selectDistrict,district);
    }
    public boolean check_scroll()
    {
        return Browser.hasScroll(container);
    }
    public void hoverCategory(WebElement e)
    {
        Browser.hoverElement(e);
    }
    public void hoverCategoryNuocHoa()
    {
        Browser.hoverElement(category_Nuochoa);
    }
    public WebElement getCategoryElement()
    {
       return Browser.getElement(category_Nuochoa);
    }
    public void clickCategoryNuocHoa()
    {
        Browser.click(category_Nuochoa);
    }
    public String getColorOfNuocHoa()
    {
       return Browser.getColorOfElement(category_Nuochoa);
    }
    public List<WebElement> listofSubCategory()
    {
        return Browser.getList(subcategoryNuocHoa);
    }
    public void clickCategoryNuocHoaNu()
    {
        Browser.hoverElementAndClick(subcategoryNuocHoaNu);
    }
    public void clickCategoryNuocHoaNam()
    {
        Browser.hoverElementAndClick(subcategoryNuocHoaNam);
    }
    public void hoverCategoryNuocHoaNu()
    {
        Browser.hoverElement(subcategoryNuocHoaNu);
    }
    public void hoverCategoryNuocHoaNam()
    {
        Browser.hoverElement(subcategoryNuocHoaNam);
    }
    public String getColorOfNuocHoaNu()
    {
        return Browser.getColorOfElement(subcategoryNuocHoaNu);
    }
    public void clickIconCart()
    {
        Browser.click(iconCart);
    }


}
