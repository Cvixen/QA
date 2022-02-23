package hh.signInByPassword;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignIn {
    private WebDriver driver;
    private WebDriverWait wait;

    public SignIn(WebDriver driver) {
        this.driver = driver;
        wait =  new WebDriverWait(driver, 10);
    }
    @FindBy( xpath = "/html/body/div[6]/div[2]/div/div[1]/div[1]/div/div[6]/a")
    private WebElement buttonSingIn;
    private By siteSignIn = By.xpath("//input[@data-qa =\"account-signup-email\"]");

    public void open() {
        driver.get("https://www.hh.ru/");
    }

    public void enterSignIn(){

        buttonSingIn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(siteSignIn));

    }
}