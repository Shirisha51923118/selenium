package ddtpractise;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ReadTheDataByDataFormatter {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream ar=new FileInputStream("src/test/resources/entireexceldatareading.xlsx");
	Workbook wb	=WorkbookFactory.create(ar);
	Sheet sheet=wb.getSheet("Sheet1");
	DataFormatter data=new DataFormatter();
	Map<String, String> map= new HashMap<>();
	for(int i=0;i<=sheet.getLastRowNum();i++) {
		String key=data.formatCellValue(sheet.getRow(i).getCell(0));
		String value=data.formatCellValue(sheet.getRow(1).getCell(1));
		map.put(key, value);
		}
	System.out.println(map);
	wb.close();
	}
	}


