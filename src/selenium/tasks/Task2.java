package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.FeedBackPage;
import selenium.pages.FeedBackPageSubmitted;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Task2 {
    WebDriver driver;
    static FeedBackPage feedBackPage;
    static FeedBackPageSubmitted feedBackPageSubmitted;
    WebDriverWait wait;


    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        driver.manage().window().maximize();
        feedBackPage = PageFactory.initElements(driver, FeedBackPage.class);
        feedBackPageSubmitted = PageFactory.initElements(driver, FeedBackPageSubmitted.class);

    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
        // TODO:
        // check that all field are empty and no tick are clicked
        // "Don't know" is selected in "Genre"
        // "Choose your option" in "How do you like us?"
        feedBackPage.checkAllFieldsEmptyAndNotSelected();
        //  check that the button send is blue with white letters
        feedBackPage.checkSendBtnColor();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
        // TODO:
        //  click "Send" without entering any data
        feedBackPage.clickOnSendBtn();
        //  check fields are empty or null
        feedBackPageSubmitted.checkAllFieldsEmpty();
        //  check button colors (green with white letter and red with white letters)
        feedBackPageSubmitted.checkButtonColors();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
        // TODO:
        feedBackPage.enterName("Elena");
        feedBackPage.enterAge(35);
        feedBackPage.selectLanguageCheckBox("English");
        feedBackPage.selectGender("Female");
        feedBackPage.selectHowYouLikeUs("Why me");
        feedBackPage.fillTheCommentArea("Everything is fine");
        // fill the whole form, click "Send"
        feedBackPage.clickOnSendBtn();
        // check fields are filled correctly
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourName(), "Elena");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourAge(), 35);
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourLanguage(), "English");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourGender(), "Female");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourOptionOfUs(), "Why me");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourComment(), "Everything is fine");
        // check button colors (green with white letter and red with white letters)
        feedBackPageSubmitted.checkButtonColors();

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
        // TODO:
        // enter only name
        feedBackPage.enterName("Elena");
        // click "Send"
        feedBackPage.clickOnSendBtn();
        // click "Yes"
        feedBackPageSubmitted.clickOnYesBtn();
        // check message text: "Thank you, NAME, for your feedback!"
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getSubmitMessage(), "Thank you, Elena, for your feedback!");
        // color of text is white with green on the background
        feedBackPageSubmitted.checkSubmitMessageColor();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
        // TODO:
        // click "Send" (without entering anything
        feedBackPage.clickOnSendBtn();
        // click "Yes"
        feedBackPageSubmitted.clickOnYesBtn();
        // check message text: "Thank you for your feedback!"
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getSubmitMessage(), "Thank you for your feedback!");
        //  color of text is white with green on the background
        feedBackPageSubmitted.checkSubmitMessageColor();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
        //   TODO:
        //   fill the whole form
        feedBackPage.enterName("Elena");
        feedBackPage.enterAge(35);
        feedBackPage.selectLanguageCheckBox("English");
        feedBackPage.selectGender("Female");
        feedBackPage.selectHowYouLikeUs("Why me");
        feedBackPage.fillTheCommentArea("Everything is fine");
        //   click "Send"
        feedBackPage.clickOnSendBtn();
        //   click "No"
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(feedBackPageSubmitted.getNoBtn()));
        feedBackPageSubmitted.clickOnNoBtn();
        feedBackPage.checkFormAfterNoBtn();
        //   check fields are filled correctly
        feedBackPage.checkIsSelected(feedBackPage.getCheckBoxEnglish());
        feedBackPage.checkIsSelected(feedBackPage.getRadioBtnGender().get(1));
        feedBackPage.checkIsSelected(feedBackPage.getLikeUsDDoptions().get(4));
    }
}
