package by.karpenia.components;

import by.karpenia.pages.*;
import by.karpenia.tools.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationMenu {

    private WebDriver driver;
    private Waiter waiter;

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
        this.waiter = new Waiter(driver);
    }

    public LoginPage navigateLoginPage () {
        navigateLoginLocator.click();
        return new LoginPage(driver);
    }

    public SettingsPage navigateUserSettingsPage() {
        waiter.waitForVisibility(navigateProfileLocator);
        navigateProfileLocator.click();
        navigateSettingsLocator.click();
        return new SettingsPage(driver);
    }

    public RepositoryPage navigateNewRepositoryPage() {
        waiter.waitForVisibility(navigateCreateNewLocator);
        navigateCreateNewLocator.click();
        navigateNewRepositoryLocator.click();
        return new RepositoryPage(driver);
    }

    public Waiter waiter() {
        return new Waiter(driver);
    }

}
