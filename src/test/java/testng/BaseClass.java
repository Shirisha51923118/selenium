package testng;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	WebDriver driver;
	
@BeforeSuite
	public void suite() {
		System.out.println("before suite");
		
	}
   @BeforeClass
public void Class() {
	   driver=new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   
	  }
   @BeforeMethod
   public void method() {
	   System.out.println("http://localhost:8888/");
   }

}
