package hh.signInByPassword;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInSuccess {
    private WebDriver driver;
    private WebDriverWait wait;

    public SignInSuccess(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(xpath = "//input[@data-qa =\"account-signup-email\"]")
    private WebElement writeEmail;
    @FindBy(xpath = "//button[@data-qa=\"expand-login-by-password\"]")
    private WebElement buttonEnterWithPassword;
    @FindBy(xpath = "//input[@data-qa=\"login-input-password\"]")
    private WebElement writepassword;
    @FindBy(xpath = "//button[@data-qa=\"account-login-submit\"]")
    private WebElement buttonEnterAccountData;
    By siteSingInSuccess = By.xpath("//h2[@data-qa=\"bloko-header-2\"]");

    public void inputEmail() {
        String testEmail = "testformytesting03@gmail.com";
        String testPassword = "Qwerty132";
        writeEmail.sendKeys(testEmail);
        buttonEnterWithPassword.click();
        writepassword.sendKeys(testPassword);
        buttonEnterAccountData.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteSingInSuccess));
    }

}
