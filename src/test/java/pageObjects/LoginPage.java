package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtLoginEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtLoginPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLoginButton;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setLoginEmail(String loginEmail) {
		txtLoginEmail.sendKeys(loginEmail);
	}
	
	public void setLoginPassword(String loginPassword) {
		txtLoginPassword.sendKeys(loginPassword);
	}
	
	public void clickLoginBtn() {
		btnLoginButton.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
}
