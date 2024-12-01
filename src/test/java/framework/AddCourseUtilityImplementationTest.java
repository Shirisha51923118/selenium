package framework;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.DriverUtility;
import utils.ExcelUtility;
import utils.IConstantPath;
import utils.PropertiesUtility;
public class AddCourseUtilityImplementationTest
{
	public static void main(String[] args) {
		//created an instance for driverutility,properties utility,excel utility
		DriverUtility driverUtil = new DriverUtility();
		PropertiesUtility property = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH, "Sheet1");
		
		WebDriver driver = driverUtil.launchBrowser(property.getPropertyData("browser"));
		driverUtil.maximizeBrowser();
		driverUtil.navigateToApp(property.getPropertyData("url"));
		
		long time = Long.parseLong(property.getPropertyData("timeouts"));
		driverUtil.waitTillElementFound(time);
		
		
		driver.findElement(By.name("email")).sendKeys(property.getPropertyData("username"));
		driver.findElement(By.id("password")).sendKeys(property.getPropertyData("password"));
		driver.findElement(By.id("last")).click();

		driver.findElement(By.xpath("//span[text()='Courses']")).click();
		driver.findElement(By.xpath("//a[text()=' Course List']")).click();

		driver.findElement(By.id("addproduct")).click();
		
		Map<String, String> map = excel.readFromExcel();

		driver.findElement(By.id("name")).sendKeys(map.get("name"));
		WebElement categoryDD = driver.findElement(By.id("category"));
		
		driverUtil.selectFromDropdown(categoryDD, map.get("category"));

		driver.findElement(By.id("price")).sendKeys(map.get("price"));
		driver.findElement(By.xpath("(//input[@id='photo'])[2]"))
				.sendKeys(map.get("photo"));

		WebElement descriptionFrame = driver.findElement(By.xpath("//iframe[contains(@title,'editor1')]"));
		
		driverUtil.switchToFrame(descriptionFrame);
		driver.findElement(By.xpath("//html/body/p")).sendKeys(map.get("description"));
		driverUtil.switchBackFromFrame();
		driver.findElement(By.name("add")).click();

		String successAddMessage = driver.findElement(By.className("alert-dismissible")).getText();
		System.out.println(successAddMessage);

		List<WebElement> courses = driver.findElements(By.xpath("//td[@class='sorting_1']"));
		for (WebElement course : courses) {
			if (course.getText().equalsIgnoreCase(map.get("name"))) {
				driver.findElement(By.xpath(
						"//td[text()='" + course.getText() + "']/following-sibling::td/button[text()=' Delete']"))
						.click();
				driver.findElement(By.name("delete")).click();
				break;
			}
		}
		String successDeleteMessage = driver.findElement(By.className("alert-dismissible")).getText();

		System.out.println(successDeleteMessage);
		driver.findElement(By.xpath("//span[text()='SkillRary Admin']")).click();
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		excel.closeWorkbook();
		driverUtil.quitAllWindows();
		}
}
