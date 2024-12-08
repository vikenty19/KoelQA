import POM.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Homework17 extends BaseTest {

    @Test
    public void addSongToPlaylist() throws InterruptedException {
        String text = "Ketsa - Beautiful";
        String playlistName = generateRandomPlaylistName();
        // login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("vicplach123@gmail.com", "MEGAdelta06@");
        // search for song

        searchForSong(text);

        // click results view all button

        clickViewAllbtn();

        // click first song
        clickFirstSong();

        // click Add To

        clickAddTo();

        // create new playlist
        newPlaylistWhileSearchingSong(playlistName);


        // assertions - success banner and song name in playlist

        Assert.assertTrue(isSuccessBannerDisplayed());

        Assert.assertEquals(text, getSongName());
    }

    private void newPlaylistWhileSearchingSong(String playlistName) {
        WebElement newPlaylistNameInput = driver.findElement(By.cssSelector("[id='songResultsWrapper'] [placeholder='Playlist name']"));
        newPlaylistNameInput.click();
        newPlaylistNameInput.clear();
        newPlaylistNameInput.sendKeys(playlistName);
        System.out.println(playlistName);
        // click Enter
        new Actions(driver)
                .keyDown(Keys.ENTER)
                .perform();
    }

    private void clickAddTo() {
        WebElement addToBtn = driver.findElement(By.cssSelector("[data-test='add-to-btn']"));
        addToBtn.click();

    }

    private void clickFirstSong() {
        List<WebElement> songsInResults = driver.findElements(By.cssSelector(".search-results .song-item .title"));
        songsInResults.get(0).click();

    }

    private void clickViewAllbtn() {
        WebElement viewAllBtn = driver.findElement(By.xpath("//button[@data-test='view-all-songs-btn']"));
        viewAllBtn.click();
    }

    public boolean isSuccessBannerDisplayed() {
        WebElement successBanner = driver.findElement(By.cssSelector(".success"));
        return successBanner.isDisplayed();
    }

    public String getSongName() {
        WebElement songName = driver.findElement(By.cssSelector("#playlistWrapper .song-item .title"));
        String songText = songName.getText();
        System.out.println(songText);
        return songText;

    }
}
