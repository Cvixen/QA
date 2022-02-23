package hh.request;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Request {
    private WebDriver driver;
    private WebDriverWait wait;

    public Request(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(xpath = "//input[@data-qa=\"search-input\"]")
    private WebElement header;
    @FindBy(css = "[data-qa=\"search-button\"]")
    private WebElement buttonSearch;
    private By requestSite = By.cssSelector("[value=\"Android разработчик\"]");

    public void open() {
        driver.get("https://www.hh.ru/");
    }

    public void requestIntroduction() {
        String request = "Andorid разработчик";
        header.sendKeys(request);
        buttonSearch.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(requestSite));
    }
}
