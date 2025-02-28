package genericUtility;



	import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
	/**
	 * This class contains method related to Excel file
	 * @author RAJU	
	 */
public class ExcelFileUtility {

		/**
		 * 
		 * @param sheetname
		 * @param row
		 * @param cell
		 * @return 
		 * @throws IOException 
		 * @throws EncryptedDocumentException 
		 */
		public String toReadDataFromExcel(String sheetname, int row, int cell) throws EncryptedDocumentException, IOException {
			FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testData.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			String value = wb.getSheet(sheetname).getRow(row).getCell(cell).toString();
			return value;
		}
		
		public int getRowCount(String sheetname) throws Throwable {
			FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testData.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			int value = wb.getSheet(sheetname).getLastRowNum();
			return value;
		}
		
		public static void main(String[] args, String sheetname, int row, int cell) throws Throwable {
			FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testData.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			 wb.getSheet(sheetname).getRow(row).getCell(cell);
			 
			 FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\testData.xlsx");
			
		}
}
