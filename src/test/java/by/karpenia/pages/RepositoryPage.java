package by.karpenia.pages;

import by.karpenia.components.AlertBox;
import by.karpenia.tools.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Iterator;
import java.util.Set;
import static by.karpenia.components.AlertBox.alertBoxInputLocator;
import static by.karpenia.components.AlertBox.alertSubmitDeleteLocator;

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

    public RepositoryPage deleteRepository() {
        driver.get(Util.REPOSITORY_SETTINGS_URL);
        deleteRepositoryLocator.click();

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

        return new RepositoryPage(driver);
    }

    public AlertBox alertBox() {
        return alertBox;
    }


}
