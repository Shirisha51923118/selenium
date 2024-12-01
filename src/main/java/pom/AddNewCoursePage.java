package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.DriverUtility;

public class AddNewCoursePage {
	
	@FindBy(xpath = "//b[text()='Add New Course']")
	private WebElement pageHeader;
	@FindBy(id = "name")
	private WebElement nameTf;
	@FindBy(id = "price")
	private WebElement priceTF;
	@FindBy(id = "category")
	private WebElement category;
	@FindBy(xpath = "(//input[@id='photo'])[2]")
	private WebElement choosefile;
	@FindBy(xpath = "//iframe[contains(@title,'editor1')]")
	private WebElement decriptionFrame;
	@FindBy(xpath = "//html/body/p")
	private WebElement descriptionBox;
	@FindBy(name = "add")
	private WebElement saveBtn;

	// init
	public AddNewCoursePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void addName(String name) {
		nameTf.sendKeys(name);
	}

	public void setprice(String price) {
		priceTF.sendKeys(price);
	}

	public void dropdowncategory(DriverUtility driverUtil, String dropdown) {
		driverUtil.selectFromDropdown(category, dropdown);
	}

	public void addDescription(DriverUtility driverUtil, String description) {
		driverUtil.switchToFrame(decriptionFrame);
		descriptionBox.sendKeys(description);
		driverUtil.switchBackFromFrame();
	}

	public void clickSave() {
		saveBtn.click();
	}

	public void setPhoto(String photo) {
		choosefile.sendKeys(photo);
	}

}
