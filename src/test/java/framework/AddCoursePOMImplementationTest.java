package framework;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import pom.AddNewCoursePage;
import pom.CourseListPage;
import pom.DashboardPage;
import pom.LoginPage;
import utils.DriverUtility;
import utils.ExcelUtility;
import utils.IConstantPath;
import utils.PropertiesUtility;

public class AddCoursePOMImplementationTest {
	public static void main(String[] args) {
		// created an instance for driverutility,properties utility,excel utility
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

		LoginPage login = new LoginPage(driver);
		DashboardPage dashboard = new DashboardPage(driver);
		CourseListPage course = new CourseListPage(driver);
		AddNewCoursePage addCourse = new AddNewCoursePage(driver);

		login.loginToApp(property.getPropertyData("username"), property.getPropertyData("password"));

		dashboard.clickCoursesTab();
		dashboard.clickCopurseListLink();

		course.clickNewBTN();

		Map<String, String> map = excel.readFromExcel();

		addCourse.addName(map.get("name"));
		addCourse.dropdowncategory(driverUtil, map.get("category"));
		addCourse.setprice(map.get("price"));
		addCourse.setPhoto(map.get("photo"));
		addCourse.addDescription(driverUtil, map.get("description"));
		addCourse.clickSave();

		System.out.println(course.getSuccessAlertMessage());
		System.out.println(course.searchCourse(map.get("name")));

		course.deleteCourse(driverUtil, map.get("name"));

		System.out.println(course.getSuccessAlertMessage());
		System.out.println(course.searchCourse(map.get("name")));

		dashboard.signOutOfApp();
		excel.closeWorkbook();
		driverUtil.quitAllWindows();
	}
}
