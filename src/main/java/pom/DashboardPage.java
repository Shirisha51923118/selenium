package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	@FindBy(xpath = "//h1")
	private WebElement pageHeader;
	
	@FindBy(xpath = "//span[text()='Courses']")
	private WebElement coursesTab;
	
	@FindBy(xpath = "//a[text()=' Course List']")
	private WebElement courseListLink;
	
	@FindBy(xpath = "//span[text()='SkillRary Admin']")
	private WebElement skillraryAdminTab;
	
	@FindBy(xpath = "//a[text()='Sign out']")
	private WebElement signOutLink;
	
	public DashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void clickCoursesTab() {
		coursesTab.click();
	}
	
	public void clickCopurseListLink() {
		courseListLink.click();
	}
	
	public void signOutOfApp() {
		skillraryAdminTab.click();
		signOutLink.click();
	}
}
