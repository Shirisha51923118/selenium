package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage1 {

	// Declaration
	@FindBy(xpath = "//div[@id='login']/h3")
	private WebElement pageHeader;

	@FindBy(name = "email")
	private WebElement emailTF;

	@FindBy(id = "password")
	private WebElement passwordTF;

	@FindBy(css = "input[type=\"checkbox\"]")
	private WebElement keepMeLoggedInCB;

	@FindBy(xpath = "//a[text()='Forgot your password?']")
	private WebElement forgotPWDLink;
	
	@FindBy(id="last")
	private WebElement loginBTN;
	
	// Initialization
	public LoginPage1(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Utilization
	public boolean isPageHeaderPresent() {
		return pageHeader.isDisplayed();
	}
	
	public void setEmail(String email) {
		emailTF.sendKeys(email);
	}
	
	public void setPassword(String pwd) {
		passwordTF.sendKeys(pwd);
	}
	
	public void clickKeepMeLoggedIn() {
		keepMeLoggedInCB.click();
	}
	
	public void clickForgotPWD() {
		forgotPWDLink.click();
	}
	
	public void clickLoginBTN() {
		loginBTN.click();
	}
}
