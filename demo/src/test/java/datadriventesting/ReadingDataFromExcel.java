package datadriventesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingDataFromExcel {

    public static void readExcel() throws IOException {
        // FileInputStream file = new
        // FileInputStream("/Users/anirbanmishra/Desktop/Playground/SELENIUM
        // Webdriver/demo/testdata/data.xlsx");
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/demo/testdata/data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1"); // XSSFSheet sheet = workbook.getSheetAt(0);
        int totalRows = sheet.getLastRowNum();
        int totalCells = sheet.getRow(1).getLastCellNum();
        System.out.println(totalRows + " - " + totalCells);

        for (int r = 0; r <= totalRows; r++) {
            System.out.println(r);
            XSSFRow currentRow = sheet.getRow(r);
            for (int c = 0; c < totalCells; c++) {
                XSSFCell cell = currentRow.getCell(c);
                System.out.print(cell.toString() + "\t");
            }
            System.out.println();
        }
        workbook.close();
    }

    public static void writeExcel() throws IOException {
        // FileInputStream file = new
        // FileInputStream("/Users/anirbanmishra/Desktop/Playground/SELENIUM
        // Webdriver/demo/testdata/data.xlsx");
        FileOutputStream file = new FileOutputStream(
                System.getProperty("user.dir") + "/demo/testdata/write_excel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Data");
        XSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("Test1");
        row1.createCell(1).setCellValue("Test2");
        row1.createCell(2).setCellValue("Test3");
        row1.createCell(4).setCellValue("Test4");
        XSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("Hello1");
        row2.createCell(1).setCellValue("Hello2");
        row2.createCell(2).setCellValue("Hello3");
        row2.createCell(4).setCellValue("Hello4");
        workbook.write(file);
        workbook.close();
        file.close();
        System.out.println("File is created");
    }

    public static void main(String[] args) throws IOException {
        readExcel();
        // writeExcel();
    }
}
