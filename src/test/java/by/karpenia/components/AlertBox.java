package by.karpenia.components;

import by.karpenia.tools.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertBox {

    private WebDriver driver;
    private Waiter waiter;

    @FindBy(id = "js-flash-container")
    public static WebElement alertBoxLocator;

    @FindBy(xpath = "(//input[@name='verify'])[3]")
    public static WebElement alertBoxInputLocator;

    @FindBy(xpath = "(//button[@type='submit'])[11]")
    public static WebElement alertSubmitDeleteLocator;

    public AlertBox (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.waiter = new Waiter(driver);
    }

    public AlertBox navigateAlertBox() {
        waiter.waitForVisibility(alertBoxLocator);
        alertBoxLocator.click();
        return new AlertBox(driver);

    }

    public boolean alertBoxIsDisplayed() {
        return alertBoxLocator.isDisplayed();
    }

    public Waiter waiter() {
        return new Waiter(driver);
    }

}
