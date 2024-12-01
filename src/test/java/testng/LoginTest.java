package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class LoginTest extends BaseClass{
	public void LoginTest() {
		driver.findElement(By.xpath("//input[@type=\"text\"]")).sendKeys("admin"+Keys.TAB+"admin"+Keys.ENTER);
	
	}
	

}
