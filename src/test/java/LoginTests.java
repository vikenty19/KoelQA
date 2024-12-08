import POM.HomePage;
import POM.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTests extends BaseTest {


    @Test//(groups = {"smoke1"})
    public void loginSucceedTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.login(myEmail, myLogin);

        // find if avatar exists
        Assert.assertTrue(homePage.getAvatar(), " User is NOT Logged in");
        System.out.println("User logged successfully   " + homePage.getAvatar());
    }


    @Test
    public void loginEmptyPasswordTest() {
       LoginPage loginpage = new LoginPage(driver);
        loginpage.login("vicplach123@gmail.com", "");
        System.out.println("Is Submit button is displayed?  " + loginpage.isSubmitLoginBtnDisplayed());
        Assert.assertTrue(loginpage.isSubmitLoginBtnDisplayed());
    }

    @Test
    public void loginInvalidEmailTest() {
        LoginPage loginpage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginpage.login("notexists@class.com", myLogin);
        System.out.println("Is Submit button is displayed?  " + loginpage.isSubmitLoginBtnDisplayed());
        Assert.assertTrue(loginpage.isSubmitLoginBtnDisplayed());
        //       Assert.assertFalse(homePage.getAvatar());
    }

    @Test(dataProvider = "IncorrectLoginProviders")
    public void negativeLoginTests(String email, String password) {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login(email, password);
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    //        Email("demo@class.com");
//        Password("te$t$tudent");
}
