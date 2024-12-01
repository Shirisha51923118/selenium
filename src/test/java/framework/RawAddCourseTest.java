package framework;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
public class RawAddCourseTest
{
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoapp.skillrary.com/login.php?type=login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.name("email")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("last")).click();

		driver.findElement(By.xpath("//span[text()='Courses']")).click();
		driver.findElement(By.xpath("//a[text()=' Course List']")).click();

		driver.findElement(By.id("addproduct")).click();

		driver.findElement(By.id("name")).sendKeys("UiPath");
		WebElement categoryDD = driver.findElement(By.id("category"));
		Select s = new Select(categoryDD);
		s.selectByVisibleText("testing");

		driver.findElement(By.id("price")).sendKeys("20000");
		driver.findElement(By.xpath("(//input[@id='photo'])[2]"))
				.sendKeys("C:\\Users\\User\\Desktop\\wallpaperbetter.jpg");

		WebElement descriptionFrame = driver.findElement(By.xpath("//iframe[contains(@title,'editor1')]"));
		driver.switchTo().frame(descriptionFrame);
		driver.findElement(By.xpath("//html/body/p")).sendKeys("RPA Tool");
		driver.switchTo().defaultContent();

		driver.findElement(By.name("add")).click();

		String successAddMessage = driver.findElement(By.className("alert-dismissible")).getText();
		System.out.println(successAddMessage);

		List<WebElement> courses = driver.findElements(By.xpath("//td[@class='sorting_1']"));
		for (WebElement course : courses) {
			if (course.getText().equalsIgnoreCase("UiPath")) {
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
		driver.quit();
		}
}
