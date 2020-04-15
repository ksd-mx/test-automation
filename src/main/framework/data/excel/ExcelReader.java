package main.framework.data.excel;

import main.framework.configuration.ConfigurationManager;
import main.framework.data.IDataReader;
import main.framework.serialization.IFormatter;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Until we figure out whether or not other file formats
 * will be introduced as test data sources, we can keep
 * this parser as an embedded object.
 */
public class ExcelReader implements IDataReader {
    private final XSSFWorkbook workbook;

    public ExcelReader(ConfigurationManager configurationManager, String datasourceKey) throws IOException {
        // This is a workaround the ZIPBOMB exception
        // Don't remove it unless you know how to solve this
        ZipSecureFile.setMinInflateRatio(0);

        this.workbook = this.importWorkbook(configurationManager, datasourceKey);
    }

    @Override
    public String read(String valueAddress) {
        return this.read(valueAddress, null);
    }

    @Override
    public <T> T read(String valueAddress, IFormatter<T> formatter) {
        return formatter.format(this.translateAddress(valueAddress));
    }

    private String translateAddress(String valueAddress) {
        String parsed = valueAddress.replace("\"", "");
        String[] parts = parsed.split("!");
        XSSFSheet sheet = this.workbook.getSheet(parts[0]);

        sheet.setActiveCell(new CellAddress(parts[1]));
        int rowId = sheet.getActiveCell().getRow();
        int colId = sheet.getActiveCell().getColumn();
        XSSFCell activeCell = sheet.getRow(rowId).getCell(colId);
        switch (activeCell.getCellType()) {
            case BOOLEAN:
                return String.valueOf(activeCell.getBooleanCellValue());
            case NUMERIC:
                return String.valueOf(activeCell.getNumericCellValue());
            default:
                return activeCell.getStringCellValue();
        }
    }

    public void close() throws IOException {
        this.workbook.close();
    }

    private XSSFWorkbook importWorkbook(
            ConfigurationManager configurationManager,
            String dataSourceKey)
            throws IOException {
        String filePath =
                configurationManager
                        .getExecutionSettings()
                        .getDatasource(dataSourceKey);

        FileInputStream stream = new FileInputStream(filePath);

        XSSFWorkbook result = new XSSFWorkbook(stream);
        System.out.println(String.format("FILE '%s' IS READY.", filePath));

        return result;
    }
}