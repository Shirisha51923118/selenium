package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	// Declaration
	@FindBy(xpath = "//h3[text()='Login']")
	private WebElement pageHeader;
	
	@FindBy(id = "email")
	private WebElement emailTF;
	
	@FindBy(name = "password")
	private WebElement passwordTF;
	
	@FindBy(id = "last")
	private WebElement loginBTN;
	
	// Initialization
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Utilization
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void loginToApp(String username, String password) {
		emailTF.sendKeys(username);
		passwordTF.sendKeys(password);
		loginBTN.click();
	}
}
