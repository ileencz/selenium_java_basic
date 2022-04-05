package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.LoadingColor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Sample9Task {
    WebDriver driver;
    static LoadingColor loadingColor;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }
    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {
        loadingColor = PageFactory.initElements(driver, LoadingColor.class);
        // TODO:
        //  * 1) click on start loading green button
        loadingColor.clickStartGreen();
        /*driver.findElement(By.id("start_green")).click();*/
        //  * 2) check that button does not appear,
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        //  * but loading text is seen instead   "Loading green..."
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());
        //  * 3) check that both button
        //  * and loading text is not seen,
        Thread.sleep(5000);
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        //  * success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }
    @Test
    public void loadGreenImplicit() throws Exception {
        // TODO:
        // * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
        // * 2) check that button does not appear,
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        //  * but loading text is seen instead   "Loading green..."
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());
        // * 3) check that both button
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("finish_green")).isDisplayed();
        // * and loading text is not seen,
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        // * success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        // TODO:
        //* 1) click on start loading green button
        WebElement loadGreenButton = driver.findElement(By.id("start_green"));
        loadGreenButton.click();
        //* 2) check that button does not appear,
        //* but loading text is seen instead   "Loading green..."
        assertFalse(loadGreenButton.isDisplayed());
        //* 3) check that both button
        //* and loading text is not seen,
        //* success is seen instead "Green Loaded"
        WebElement loadingGreenText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreenText.isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */
    }
}
