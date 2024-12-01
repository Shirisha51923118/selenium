package ddtpractise;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadTheDataFromTheExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/ddtmyexcel.xlsx");
		 Workbook wb= WorkbookFactory.create(fis);
		    String sheet = wb.getSheet("Sheet1").getRow(0).getCell(1).getStringCellValue();
		    System.out.println(sheet);
		   String data1= wb.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
//		  Row  row=sheet.getRow(0);
//		Cell cell=row.getCell(1);
//		String data=cell.getStringCellValue();
		System.out.println(data1);
		wb.close();
		    
		
		
		
    

	}

}
