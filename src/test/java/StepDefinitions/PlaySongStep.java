package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class PlaySongStep {

    private final LoginStepDefinitions loginStepDefinitions;
    public PlaySongStep(){
        this.loginStepDefinitions = new LoginStepDefinitions();

    }

    @Given("I am logged in ahd on SongPage {string},{string}")
    public void iAmLoggedIn(String email,String password){
        loginStepDefinitions.setUpDriver();
        loginStepDefinitions.iOpenLoginPage();
        loginStepDefinitions.iEnterEmail(email);
        loginStepDefinitions.iEnterPassword(password);
        loginStepDefinitions.iClickSubmit();
        loginStepDefinitions.iLoggedIn();

    }


    @And("Click on the song title {string}")
    public void clickOnTheSongName(String songName) {
        loginStepDefinitions.driver.findElement(By.xpath("//td[contains(text(), '"+songName+"')]")).click();

    }

    @And("click on the play button")
    public void clickOnThePlayButton() {

      WebElement playBtn =  loginStepDefinitions.driver.findElement(By.cssSelector(".play .fa-play"));
      new Actions(loginStepDefinitions.driver)
              .moveToElement(playBtn)
              .perform();
      playBtn.click();

    }



    @Then("Equalizer button is appeared")
    public void equalizerButtonIsAppeared() {
        Assert.assertTrue(loginStepDefinitions.driver.findElement(By.cssSelector("div.bars")).isDisplayed());
    }
    @After("@playSong")
    public void tearDown(){
        loginStepDefinitions.driver.quit();
    }
}
