package by.karpenia.pages;

import by.karpenia.components.AlertBox;
import by.karpenia.tools.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RepositoryPage {

    private final WebDriver driver;
    private final AlertBox alertBox;

    @FindBy(xpath = "//input[@id='repository_name']")
    private WebElement repositoryNameLocator;

    @FindBy(css = ".first-in-line")
    private WebElement submitRepositoryLocator;

    @FindBy(linkText = "Settings")
    private WebElement settingsRepositoryLocator;

    @FindBy(xpath = "//*[contains(text(), 'GitHubTestingProject/TestRepository')]")
    public static WebElement currentRepositoryNameLocator;

    @FindBy(xpath = "//*[contains(text(), 'Delete this repository')]")
    private WebElement deleteRepositoryLocator;

    public RepositoryPage(WebDriver driver)  {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.alertBox = new AlertBox(driver);
    }

    public RepositoryPage createNewRepository(String repositoryName) {
        repositoryNameLocator.sendKeys(repositoryName);
        submitRepositoryLocator.submit();
        return new RepositoryPage(driver);
    }

    public RepositoryPage openSettings() {
        driver.get(Util.REPOSITORY_SETTINGS_URL);
        return this;
    }

    public RepositoryPage deleteRepository() {
        deleteRepositoryLocator.click();
        return new RepositoryPage(driver);
    }

    public AlertBox alertBox() {
        return alertBox;
    }


}
