package by.karpenia.pages;

import by.karpenia.components.NavigationMenu;
import by.karpenia.tools.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    private final WebDriver driver;
    private final NavigationMenu navigationMenu;

    @FindBy(id = "login_field")
    private WebElement usernameLocator;

    @FindBy(id = "password")
    private WebElement passwordLocator;

    @FindBy(css = ".btn-block")
    private WebElement loginButtonLocator;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.navigationMenu = new NavigationMenu(driver);
    }

    public LoginPage open() {
        driver.get(Util.LOGIN_URL);
        return this;
    }

    public HomePage loginGitHub(String username, String password) {
        usernameLocator.sendKeys(username);
        passwordLocator.sendKeys(password);
        loginButtonLocator.submit();
        return new HomePage(driver);
    }

    public NavigationMenu navigationMenu() {
        return navigationMenu;
    }

}
