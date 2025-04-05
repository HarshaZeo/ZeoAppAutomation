package ConfigFiles;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static XSSFWorkbook wb;
	public static XSSFSheet s;
	
	//Set path details
	public ExcelUtils(String path,String SheetName) throws Exception
	{
		   FileInputStream fis=new FileInputStream(path);
		   wb=new XSSFWorkbook(fis);
		   s=wb.getSheet(SheetName);
	}
	//get value method
	public String getValues(int Rowindex,int colindex)
	{
		return s.getRow(Rowindex).getCell(colindex).getStringCellValue();
	}
	//Sent method
	public void setCellData(int rowindex,int colindex,String result,String excelPath) throws Exception
	{
		s.getRow(rowindex).createCell(colindex).setCellValue(result);
		FileOutputStream fos=new FileOutputStream(excelPath);
		wb.write(fos);
	}
	//get row number
	public int getRows()
	{
		return s.getLastRowNum();
	}
}
