package by.karpenia.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertBox {

    private WebDriver driver;

    @FindBy(id = "js-flash-container")
    public static WebElement alertBoxLocator;

    @FindBy(xpath = "(//input[@name='verify'])[3]")
    public static WebElement alertBoxInputLocator;

    @FindBy(xpath = "(//button[@type='submit'])[11]")
    public static WebElement alertSubmitDeleteLocator;

    public AlertBox (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public boolean alertBoxIsDisplayed() {
        return alertBoxLocator.isDisplayed();
    }

}
