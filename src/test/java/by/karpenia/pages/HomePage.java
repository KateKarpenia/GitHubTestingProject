package by.karpenia.pages;

import by.karpenia.tools.Util;
import by.karpenia.components.NavigationMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

    private final WebDriver driver;
    private final NavigationMenu navigationMenu;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.navigationMenu = new NavigationMenu(driver);
    }

    public HomePage open() {
        driver.get(Util.BASE_URL);
        return this;
    }

    public NavigationMenu navigationMenu() {
        return navigationMenu;
    }

}
