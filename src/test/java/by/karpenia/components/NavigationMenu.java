package by.karpenia.components;

import by.karpenia.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class NavigationMenu {

    private WebDriver driver;

    @FindBy(linkText = "Sign in")
    private WebElement navigateLoginLocator;

    @FindBy(xpath = "//a[contains(@href,'/settings/profile')]")
    private WebElement navigateSettingsLocator;

    @FindBy(css = ".header-nav-link.name>span")
    private WebElement navigateProfileLocator;

    @FindBy(xpath = "//a[contains(@href,'/new')]")
    private WebElement navigateCreateNewLocator;

    @FindBy(xpath = "//a[contains(text(), 'New repository')]")
    private WebElement navigateNewRepositoryLocator;

    public NavigationMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public LoginPage navigateLoginPage () {
        navigateLoginLocator.click();
        return new LoginPage(driver);
    }

    public HomePage navigateProfilePage() {
        navigateProfileLocator.click();
        return new HomePage(driver);
    }

    public SettingsPage navigateSettingsPage() {
        navigateSettingsLocator.click();
        return new SettingsPage(driver);
    }

    public SettingsPage navigateCreateNewRepositoryPage() {
        navigateCreateNewLocator.click();
        return new SettingsPage(driver);
    }

    public RepositoryPage navigateNewRepositoryPage() {
        navigateNewRepositoryLocator.click();
        return new RepositoryPage(driver);
    }



}
