package framework;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utils.BaseClass;

@Listeners(utils.ListenerImplementation.class)
public class AddCourseTestNGImplementationTest extends BaseClass{
	@Test
	public void addCourseTest() throws InterruptedException {
		dashboard.clickCoursesTab();
		dashboard.clickCopurseListLink();
		soft.assertEquals(course.getPageHeader(), "Course List");
		course.clickNewBTN();
		Thread.sleep(2000);
		soft.assertEquals(addCourse.getPageHeader(), "Add New Course");
		Map<String, String> map = excel.readFromExcel();

		addCourse.addName(map.get("name"));
		addCourse.dropdowncategory(driverUtil, map.get("category"));
		addCourse.setprice(map.get("price"));
		addCourse.setPhoto(map.get("photo"));
		addCourse.addDescription(driverUtil, map.get("description"));
		addCourse.clickSave();

		soft.assertTrue(course.searchCourse(map.get("name")));
		soft.assertTrue(course.getSuccessAlertMessage().contains("Success"));

		course.deleteCourse(driverUtil, map.get("name"));

		soft.assertFalse(course.searchCourse(map.get("name")));
		soft.assertTrue(course.getSuccessAlertMessage().contains("Success"));
		soft.assertAll();
	}
}
