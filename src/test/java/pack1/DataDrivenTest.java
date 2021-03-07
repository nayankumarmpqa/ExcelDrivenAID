package pack1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenTest {

	@SuppressWarnings("deprecation")
	public ArrayList<String> getData(String testCaseSelectionString) throws IOException {

		ArrayList<String> als = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("E:\\Selenium Drivers\\Book1.xlsx");
		// fis will have the permission to read this File.class

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// wb has an ability to go inside book file.

		// Getting number of sheets in the workbook
		int numberOfSheets = workbook.getNumberOfSheets();

		// I want to find required sheet.
		for (int i = 0; i < numberOfSheets; i++) {

			if (workbook.getSheetName(i).equalsIgnoreCase("Sheet Nk")) { // comparing the sheet name at index i

				XSSFSheet requiredSheet = workbook.getSheetAt(i); // getting the required sheet as XSSSheet

				// Going to the 1st row to Find the required Column. use iterator
				Iterator<Row> rowsInTheSheetToIterate = requiredSheet.rowIterator();
				Row firstRow = rowsInTheSheetToIterate.next(); // getting control access of the 1st row in the sheet

				// Now scan the firstRow cells and compare the value
				Iterator<Cell> cellsInTheRowToIterate = firstRow.cellIterator();

				int columnIndexNumberCounter = 0;
				int requiredColumnNumber = 0;

				while (cellsInTheRowToIterate.hasNext()) {

					Cell cellValueAtIteratedCell = cellsInTheRowToIterate.next();

					if (cellValueAtIteratedCell.getStringCellValue().equalsIgnoreCase("Testcases")) {
						// Getting my required column by comparing the cell value

						requiredColumnNumber = columnIndexNumberCounter;
					}
					columnIndexNumberCounter++;
				}

				System.out.println("requiredColumnNumber = " + requiredColumnNumber);
				// Above we found the correct column number

				// Now again check row by row in the column to find the Purchase Row

				while (rowsInTheSheetToIterate.hasNext()) {
					Row rowToCheckCellValue = rowsInTheSheetToIterate.next();

					if (rowToCheckCellValue.getCell(requiredColumnNumber).getStringCellValue()
							.equalsIgnoreCase(testCaseSelectionString)) {
						// got ur reqrow grab the cells
						Iterator<Cell> cellsInTheSelecetdRow = rowToCheckCellValue.cellIterator();

						while (cellsInTheSelecetdRow.hasNext()) {

							Cell requiredCell = cellsInTheSelecetdRow.next();
							if (requiredCell.getCellType() == CellType.STRING) {
								als.add(requiredCell.getStringCellValue());
							} else {
								als.add(NumberToTextConverter.toText(requiredCell.getNumericCellValue()));
							}

						}

					}

				}

			}

		}
		return als;

	}

}
