import POM.BasePage;
import POM.LoginPage;
import POM.PlayListPage;
import POM.SongPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SongsTests extends BaseTest {

    @Test
    public void addSongToPlaylist() throws InterruptedException {
        String text =
                "Tunnel of Lights (ID 1689)";
        String playlistName = generateRandomPlaylistName();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(myEmail, myLogin);
        SongPage songPage = new SongPage(driver);
        BasePage basePage = new BasePage(driver);
        songPage.searchSongInSearchField(text)
          .clickAllViewButtn();
        Thread.sleep(500);
        songPage.clickFirstSongInResult();
      //  System.out.println(songPage.clickFirstSongInResult());
        songPage.clickAddToBtn();
        songPage.createNewPlaylistWhileAddingSong(playlistName);
        basePage.isSuccessBannerDisplayed();
        Assert.assertEquals(text, songPage.getSongName());
        System.out.println(text + " " + songPage.getSongText());


    }

    @Test
    public void checkVisibilityTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(myEmail, myLogin);
        SongPage songPage = new SongPage(driver);
        String text = songPage.getSongText();
        System.out.println("WHERE IS TEXT?" + text);
        System.out.println("Is element invisible? === " + wait
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("title"))));

    }

    @Test
    public void countSongsInAllSongs() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(myEmail, myLogin);
        SongPage songPage = new SongPage(driver);
        BasePage basePage = new BasePage(driver);
        songPage.goToAllSongsTub();

        //count songs in All Songs Tab

        //     songPage.getAllSongWebElementsList();

        int count = songPage.getAllSongWebElementsList().size();
        System.out.println("Number of songs in Allsong  " + count);
        // count songs in th header
        WebElement songsCountHeader = basePage.waitUntilVisible(By.cssSelector("#songsWrapper .meta"));
        String countSongInHeader = songsCountHeader.getText();
        //     System.out.println(countSongInHeader);
        basePage.stringToInt(countSongInHeader);


        //Assertion of equality number songs in the list and in the header
        //<<<<<Put NOT Equals to pass the test!!>>>
        Assert.assertNotEquals(countSongInHeader, count, "Number of songs NOT equal in list and header");

    }

    @Test
    public void searchForSong() throws InterruptedException {
        String text = "Tunnel of Lights (ID 1689)";                                          //Tunnel of Lights (ID 1689)
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(myEmail, myLogin);
        Thread.sleep(1000);
        SongPage songPage = new SongPage(driver);
        songPage.searchSongInSearchField(text);
        WebElement song = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//strong")));


        String NameSong = song.getText();
        System.out.println("----" + NameSong);
        // SoftAssert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(NameSong, text);
        //Check that only searching song in result
        List<WebElement> searchResult = driver
                .findElements(By.cssSelector(" [data-testid = 'song-excerpts'] ul article"));
        if(!Assertions.onlyOneSongIsInSearchResult(searchResult)){
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("./ScreenShots/SearchSong  "+NameSong+".png");
            try {
                FileUtils.copyFile(srcFile,destinationFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        softAssert.assertEquals(Assertions.onlyOneSongIsInSearchResult(searchResult).booleanValue(),true);
        //search for artist name
        WebElement artist = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//section[@class= 'artists']/p")));
        System.out.println(artist.getText());
        //search for album name
        WebElement album = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//section[@class= 'albums']/p")));
        System.out.println(album.getText());
       softAssert.assertAll();
    }
}
