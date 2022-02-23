package hh.signInByPassword;

import hh.WebDriverSettings;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class TestSignIn extends WebDriverSettings {
   @Test
    public void testSignInSuccess(){
       SignIn signIn = PageFactory.initElements(driver, SignIn.class);
       signIn.open();
       signIn.enterSignIn();
       SignInSuccess signInSuccess = PageFactory.initElements(driver, SignInSuccess.class);
       signInSuccess.inputEmail();
    }
    @Test
    public void testSignInFailEmail(){
        SignIn signIn = PageFactory.initElements(driver, SignIn.class);
        signIn.open();
        signIn.enterSignIn();
        SignInFail signInFail = PageFactory.initElements(driver, SignInFail.class);
        signInFail.failEmail();
    }

    @Test
    public void testSignInFailPassword(){
        SignIn signIn = PageFactory.initElements(driver, SignIn.class);
        signIn.open();
        signIn.enterSignIn();
        SignInFail signInFail = PageFactory.initElements(driver, SignInFail.class);
        signInFail.failPassword();
    }
}