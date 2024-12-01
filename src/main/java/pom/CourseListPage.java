package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.DriverUtility;

public class CourseListPage {

	@FindBy(xpath = "//h1")
	private WebElement pageHeader;
	
	@FindBy(id = "addproduct")
	private WebElement newBTN;
	
	@FindBy(xpath = "//td[@class='sorting_1']")
	private List<WebElement> courseNames;
	
	private String deletePath = "//td[text()='%s']/following-sibling::td/button[text()=' Delete']";
	
	@FindBy(name = "delete")
	private WebElement deleteBTN;
	
	@FindBy(className = "alert-dismissible")
	private WebElement successAlert;
	
	public CourseListPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void clickNewBTN() {
		newBTN.click();
	}
	
	public void deleteCourse(DriverUtility driverUtil, String courseName) {
		driverUtil.convertDynamicXpathToElement(deletePath, courseName).click();
		deleteBTN.click();
	}
	
	public boolean searchCourse(String courseName) {
		boolean isPresent = false;
		for (WebElement course : courseNames) {
			if (course.getText().equalsIgnoreCase(courseName)) {
				isPresent = true;
				break;
			}
		}
		return isPresent;
	}
	
	public String getSuccessAlertMessage() {
		return successAlert.getText();
	}
}
