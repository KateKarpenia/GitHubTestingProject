package by.karpenia.test;

import by.karpenia.pages.*;
import by.karpenia.tools.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static by.karpenia.pages.RepositoryPage.currentRepositoryNameLocator;
import static by.karpenia.tools.Util.*;
import static by.karpenia.components.AlertBox.*;

public class GitHubTests {

    private HomePage homePage;
    private LoginPage loginPage;
    private SettingsPage settingsPage;
    private RepositoryPage repositoryPage;
    private String textOnAlert;
    private String currentRepositoryName;

    WebDriver driver = WebDriverSingleton.getInstance();

    @BeforeClass
    public void setUpAndLogin () throws InterruptedException {

        homePage = new HomePage(driver).open();
        homePage.navigationMenu().navigateLoginPage();
        loginPage = new LoginPage(driver).open();
        loginPage.loginGitHub(USERNAME, PASS);
    }

    @Test
    public void changePublicProfileSettingsTest() throws InterruptedException {
        loginPage.navigationMenu().navigateUserSettingsPage();
        settingsPage = new SettingsPage(driver);
        settingsPage.changeUserInfo(NAME, LOCATION);
        Thread.sleep(2000);
        settingsPage.alertBox().alertBoxIsDisplayed();
        textOnAlert = alertBoxLocator.getText();
        Assert.assertEquals(PROFILE_UPDATED, textOnAlert);
    }

    @Test
    public void newRepositoryCreateAndDeleteTest () throws InterruptedException {
        homePage.navigationMenu().navigateNewRepositoryPage();
        repositoryPage = new RepositoryPage(driver);
        repositoryPage.createNewRepository(REPOSITORY_NAME);

        currentRepositoryName = currentRepositoryNameLocator.getText();
        Assert.assertEquals(TOTAL_REPOSITORY_NAME, currentRepositoryName);

        repositoryPage.deleteRepository();
        Thread.sleep(2000);
        repositoryPage.alertBox().alertBoxIsDisplayed();
        textOnAlert = alertBoxLocator.getText();
        Assert.assertEquals(SUCCESS_DELETE, textOnAlert);

    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

}
