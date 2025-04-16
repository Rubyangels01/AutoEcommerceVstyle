package support;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.*;
import java.util.*;

public class DataProviderUtils {

    @DataProvider(name = "productData")
    public static Object[][] productData() throws IOException {
        return new Object[][]
                {
                        {"Nhung","123"},
                        {"Khanh","345"}
                };
    }

    public static Iterator<Object[]> readExcelData(String filePath, String sheetName) throws IOException {
        List<Object[]> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(new File(filePath));

        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        Iterator<Row> rowIterator = sheet.iterator();

        // Bỏ qua header row nếu có
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            List<Object> rowData = new ArrayList<>();

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case STRING:
                        rowData.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            rowData.add(cell.getDateCellValue());
                        } else {
                            rowData.add(cell.getNumericCellValue());
                        }
                        break;
                    case BOOLEAN:
                        rowData.add(cell.getBooleanCellValue());
                        break;
                    default:
                        rowData.add(null);
                }
            }
            data.add(rowData.toArray());
        }

        workbook.close();
        fis.close();

        return data.iterator();
    }
    @DataProvider(name = "excelData")
    public static Iterator<Object[]> provideExcelData() throws IOException {
        return readExcelData(
                "src/test/resources/data/product_data.xlsx",
                "Sheet1"
        );
    }
    @DataProvider(name = "firstRowData")
    public static Object[][] provideFirstRowData() throws IOException {
        Iterator<Object[]> allData = readExcelData(
                "src/test/resources/data/product_data.xlsx",
                "Sheet1"
        );

        // Lấy dòng đầu tiên
        return new Object[][] { allData.next() };
    }
}

//    List<Object[]> data = new ArrayList<>();
//    InputStream is = DataProviderUtils.class.getClassLoader().getResourceAsStream("data/product_data.csv");
//    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//
//    String line = reader.readLine(); // skip header
//
//        while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                data.add(new Object[]{parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]});
//                }
//                reader.close();
//
//                return data.toArray(new Object[6][6]);