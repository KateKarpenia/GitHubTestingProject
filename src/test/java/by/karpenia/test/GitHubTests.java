package by.karpenia.test;

import by.karpenia.pages.*;
import by.karpenia.tools.Util;
import by.karpenia.tools.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

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
        Thread.sleep(5000);
        loginPage = new LoginPage(driver).open();

        loginPage.loginGitHub(USERNAME, PASS);

        Thread.sleep(2000);

    }

    @Test
    public void changePublicProfileSettingsTest() throws InterruptedException {
        loginPage.navigationMenu().navigateProfilePage();
        loginPage.navigationMenu().navigateSettingsPage();
        settingsPage = new SettingsPage(driver);
        settingsPage.navigateUserSettingsPage();
        settingsPage.changeUserName(NAME);
        settingsPage.changePublicEmail();
        settingsPage.changeUserLocation(LOCATION);
        settingsPage.updateProfileButton();

        Thread.sleep(2000);
        textOnAlert = alertBoxLocator.getText();
        Assert.assertEquals(PROFILE_UPDATED, textOnAlert);

    }

    @Test
    public void newRepositoryCreateAndDeleteTest () throws InterruptedException {
        homePage.navigationMenu().navigateCreateNewRepositoryPage();
        homePage.navigationMenu().navigateNewRepositoryPage();
        repositoryPage = new RepositoryPage(driver);

        repositoryPage.createNewRepository(REPOSITORY_NAME);

        currentRepositoryName = currentRepositoryNameLocator.getText();
        Assert.assertEquals(TOTAL_REPOSITORY_NAME, currentRepositoryName);

        repositoryPage.openSettings();
        repositoryPage.deleteRepository();

        String parentWindowHandler = driver.getWindowHandle();
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
        alertBoxInputLocator.clear();
        alertBoxInputLocator.sendKeys(Util.REPOSITORY_NAME);

        alertSubmitDeleteLocator.submit();
        driver.switchTo().window(parentWindowHandler);
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
