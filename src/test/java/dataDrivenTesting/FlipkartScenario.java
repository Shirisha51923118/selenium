package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartScenario {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("IphonePrices");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.name("q")).sendKeys("iphone" + Keys.ENTER);
		for (;;) {
			try {
				Thread.sleep(3000);
				List<WebElement> iphoneNames = driver.findElements(By.className("KzDlHZ"));
				for (int i = 0; i < iphoneNames.size(); i++) {
					String name = iphoneNames.get(i).getText();
					sheet.createRow(i).createCell(0).setCellValue(name);
					String price = driver
							.findElement(By.xpath("//div[text()='" + name
									+ "']/ancestor::div[@class='yKfJKb row']/descendant::div[@class='Nx9bqj _4b5DiR']"))
							.getText();
					sheet.getRow(i).createCell(1).setCellValue(price);
				}
				driver.findElement(By.xpath("//span[text()='Next']")).click();
			}catch(Exception e) {
				break;
			}			
		}
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData.xlsx");
		wb.write(fos);
		wb.close();
		driver.quit();

	}

}
