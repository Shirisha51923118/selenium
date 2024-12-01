package utils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import pom.AddNewCoursePage;
import pom.CourseListPage;
import pom.DashboardPage;
import pom.LoginPage;

public class BaseClass {

	protected DriverUtility driverUtil;
	protected PropertiesUtility property;
	protected ExcelUtility excel;
	protected WebDriver driver;
	long time;
	public static WebDriver sdriver;
	
	protected LoginPage login;
	protected DashboardPage dashboard;
	protected CourseListPage course;
	protected AddNewCoursePage addCourse;
	
	protected SoftAssert soft;
	
	@BeforeClass
	public void classConfig() {
		driverUtil = new DriverUtility();
		property = new PropertiesUtility();
		excel = new ExcelUtility();
		soft = new SoftAssert();
		
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH, "Sheet1");

		driver = driverUtil.launchBrowser(property.getPropertyData("browser"));
		driverUtil.maximizeBrowser();
		time = Long.parseLong(property.getPropertyData("timeouts"));
		driverUtil.waitTillElementFound(time);
		
		sdriver = driver;
	}
	
	@BeforeMethod
	public void methodConfig() {
		login = new LoginPage(driver);
		dashboard = new DashboardPage(driver);
		course = new CourseListPage(driver);
		addCourse = new AddNewCoursePage(driver);
		
		driverUtil.navigateToApp(property.getPropertyData("url"));
		Assert.assertEquals(login.getPageHeader(), "Login");
		login.loginToApp(property.getPropertyData("username"), property.getPropertyData("password"));
		Assert.assertEquals(dashboard.getPageHeader(), "Dashboard");
	}
	
	@AfterMethod
	public void methodTeardown() {
		dashboard.signOutOfApp();
	}
	
	@AfterClass
	public void classTeardown() {
		excel.closeWorkbook();
		driverUtil.quitAllWindows();
	}
}
