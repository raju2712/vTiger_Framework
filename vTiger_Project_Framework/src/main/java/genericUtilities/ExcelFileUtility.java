package genericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	public String toReadDataFromExcel(String sheetname, int row, int cell) throws Throwable, IOException {
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
		 wb.write(fos);
	}
}
	