package by.karpenia.pages;

import by.karpenia.components.NavigationMenu;
import by.karpenia.components.AlertBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class SettingsPage {

    private final WebDriver driver;
    private final NavigationMenu navigationMenu;
    private final AlertBox alertBox;

    @FindBy(id = "user_profile_name")
    private WebElement userNameSettingLocator;

    @FindBy(id = "user_profile_location")
    private WebElement userLocationSettingLocator;

    @FindBy(xpath = "//a[contains(@href,'/settings/profile')]")
    private WebElement navigateUserSettingsPageLocator;

    @FindBy(id = "user_profile_email")
    private WebElement changePublicEmailLocator;

    @FindBy(css = "p .btn")
    private WebElement updateProfileButtonLocator;

    public SettingsPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.navigationMenu = new NavigationMenu(driver);
        this.alertBox = new AlertBox(driver);
    }

    public SettingsPage changeUserInfo (String name, String location) {
        userNameSettingLocator.clear();
        userNameSettingLocator.sendKeys(name);

        userLocationSettingLocator.clear();
        userLocationSettingLocator.sendKeys(location);

        changePublicEmailLocator.click();

        Select dropdownMenu = new Select(changePublicEmailLocator);
        WebElement selectedValue = dropdownMenu.getFirstSelectedOption();

        String value = selectedValue.getText();
        if(value.contains("Don’t show my email address")) {
            dropdownMenu.selectByIndex(1);
        } else {
            dropdownMenu.selectByIndex(0);
        }

        updateProfileButtonLocator.click();

        return new SettingsPage(driver);

    }

    public NavigationMenu navigationMenu() {
        return navigationMenu;
    }

    public AlertBox alertBox() {
        return alertBox;
    }



}
