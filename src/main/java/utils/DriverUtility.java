package utils;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
public class DriverUtility {
	
	//generic reusable methods for driver
	WebDriver driver;
	public WebDriver launchBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if(browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if(browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else
			System.out.println("Invalid browser data");
		
		return driver;
	}
	
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}
	
	public void navigateToApp(String url) {
		driver.get(url);
	}
	
	public void waitTillElementFound(long time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}
	
	public void selectFromDropdown(WebElement dropdown, String text) {
		Select s = new Select(dropdown);
		s.selectByVisibleText(text);
	}
	//with the help of frame element we have to switch
	
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
	
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}
	
	public void quitAllWindows() {
		driver.quit();
	}
	
	public WebElement convertDynamicXpathToElement(String path, String replaceData) {
		String requiredPath = String.format(path, replaceData);
		return driver.findElement(By.xpath(requiredPath));
	}
	
	public String getScreenshot(WebDriver driver, String methodName) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/"+ methodName+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dest.getAbsolutePath();
	}
}
