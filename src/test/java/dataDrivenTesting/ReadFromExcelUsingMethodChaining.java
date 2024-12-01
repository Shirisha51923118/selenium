package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadFromExcelUsingMethodChaining {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// Step 1: Convert physical file into java readable object
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		
		// Step 2: Open Workbook
		Workbook wb = WorkbookFactory.create(fis);
		
		String data = wb.getSheet("Sheet1").getRow(0).getCell(1).getStringCellValue();
		System.out.println(data);
		
		double num = wb.getSheet("Sheet1").getRow(3).getCell(1).getNumericCellValue();
		System.out.println(num);
		
		// Step 7: Close Excel
		wb.close();

	}

}
