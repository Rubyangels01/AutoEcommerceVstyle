package TestMain;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import support.Browser;



public class TestRecurment {
    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    InfomationPage infomationPage;
    FinishPage finishPage;
    @BeforeClass
    public void setUp()
    {
        Browser.launchBrowser("chrome");
        loginPage = new LoginPage();
        homePage = new HomePage();
        cartPage = new CartPage();
        infomationPage = new InfomationPage();
        checkoutPage = new CheckoutPage();
        finishPage = new FinishPage();
    }

    @Test(priority = 1)
    void LoginTC() throws InterruptedException {

        loginPage.open();
        Thread.sleep(2000);
        loginPage.fillUsername();
        loginPage.fillPassword();
        loginPage.clickLogin();
    }

    @Test(priority = 2)
    void AddOneProductToCart() throws InterruptedException {
        Thread.sleep(3000);
        homePage.clickOneElement(0);
        Thread.sleep(3000);
        Assert.assertEquals(homePage.getTextCartRemove(0), "Remove", "Nút không hiển thị đúng");
        Assert.assertEquals(homePage.getTextCartIcon(),"1","hiển thị không đúng số lượng");

    }
    @Test(priority = 3)
    void CheckProductJustAdd() throws InterruptedException {
        homePage.navigateToCart();
        Thread.sleep(3000);
        Assert.assertEquals(cartPage.getProductList().getLast().getNameProduct().getText(),homePage.nameProductAdd(), "Tên sản phẩm checkout không đúng");
        Assert.assertEquals(cartPage.getProductList().getLast().getDescriProduct().getText(),homePage.descripProductAdd(),"Mô tả sản phẩm checkout không đúng");
        Assert.assertEquals(cartPage.getProductList().getLast().getPriceProduct().getText(),homePage.priceProductAdd(),"Giá sản phẩm checkout không đúng");

    }
 void fillInformation()
 {
     infomationPage.fillFirstname();
     infomationPage.fillLastName();
     infomationPage.fillpostalCode();
 }
    @Test(priority = 4)
    void CheckProductToCheckout() throws InterruptedException {
        cartPage.clickCheckout();
        Thread.sleep(3000);
        fillInformation();
        infomationPage.clickContinue();
        Thread.sleep(3000);
        Assert.assertEquals(checkoutPage.getProductList().getLast().getNameProduct().getText(),homePage.nameProductAdd(), "Tên sản phẩm checkout không đúng");
        Assert.assertEquals(checkoutPage.getProductList().getLast().getDescriProduct().getText(),homePage.descripProductAdd(),"Mô tả sản phẩm checkout không đúng");
        Assert.assertEquals(checkoutPage.getProductList().getLast().getPriceProduct().getText(),homePage.priceProductAdd(),"Giá sản phẩm checkout không đúng");



    }
    @Test(priority = 5)
    void Finish() throws InterruptedException {
        checkoutPage.clickFinish();
        Thread.sleep(3000);

        Assert.assertEquals(finishPage.isDisplayTitle(),true,"Trang hoàn tất không hiển thị đúng");



    }
    @AfterClass
    public void close()
    {
        Browser.quit();
    }

}
