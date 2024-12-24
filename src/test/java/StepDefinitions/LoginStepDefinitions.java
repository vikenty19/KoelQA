package StepDefinitions;

import POM.HomePage;
import POM.LoginPage;
import hooks.MyHooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LoginStepDefinitions  {
     public  WebDriver driver;
    public static String url ="https://qa.koel.app/";
    public static WebDriverWait wait = null;
 @After("@login")
    public void tearDown() {
        driver.quit();
    }
   @Given ("^I open browser$")
    public void setUpDriver(){
WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");

          driver = new ChromeDriver(options);
          wait = new WebDriverWait(driver,Duration.ofSeconds(10));

    }
    @When("I open login page")
    public void iOpenLoginPage(){
        driver.get(url);
    }
    @And("I enter valid email {string}")
    public void iEnterEmail(String email){
        WebElement emailInput = driver.findElement(By.cssSelector("[type='email']"));
                emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);

    }
    @And("I enter valid password {string}")
    public void iEnterPassword(String password) {
        WebElement passwordInput = driver.findElement(By.cssSelector("[type='password']"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

    }

    @And("I click Submit")
    public void iClickSubmit(){

     LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginBtn();
           }


    @Then("I am logged in")
    public void iLoggedIn(){

        HomePage homePage = new HomePage(driver);
       Assert.assertTrue(homePage.getAvatar());
    }




    @And("I enter wrong email {string}")
    public void iEnterWrongEmail(String email) {
        WebElement emailInput = driver.findElement(By.cssSelector("[type='email']"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
    }




    @Then("I am not logged in")
    public void iAmNotLoggedIn() {
        LoginPage loginpage = new LoginPage(driver);
               System.out.println("Is Submit button is displayed?  " + loginpage.isSubmitLoginBtnDisplayed());
        Assert.assertTrue(loginpage.isSubmitLoginBtnDisplayed());
    }


    @And("I enter details below into fields")
    public void iEnterDetailsBelowIntoFields(DataTable dataTable) {
       Map<String,String> map  =  dataTable.asMap(String.class,String.class);
    //email
        WebElement emailInput = driver.findElement(By.cssSelector("[type='email']"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(map.get("email"));
    //password
        WebElement passwordInput = driver.findElement(By.cssSelector("[type='password']"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(map.get("password"));
    }
    @When("User click on AllSongs button")
    public void userClickOnAllSongsButton() {
        WebElement allSongs =wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".music .songs")));
        allSongs.click();
    }


}
