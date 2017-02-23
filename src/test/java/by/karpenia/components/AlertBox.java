package by.karpenia.components;

import by.karpenia.pages.RepositoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AlertBox {

    private WebDriver driver;

    @FindBy(id = "js-flash-container")
    public static WebElement alertBoxLocator;

    @FindBy(xpath = ".//*[@id='facebox']/div/div/form/p/input")
    public static WebElement alertBoxInputLocator;
//    public final static By alertBoxInputLocator = By.className("input-block");

    @FindBy(xpath = ".//*[@id='facebox']/div/div/form/button")
//    @FindBy(css = ".btn-danger")
    public static WebElement alertSubmitDeleteLocator;

    public AlertBox (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public boolean alertBoxIsDisplayed() {
        return alertBoxLocator.isDisplayed();
    }

}
