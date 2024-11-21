package com.cucumbercraft.framework;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelReader {
    public static String lk;

    public Map<String, String> reqExceldata(String workbook, String sheetname, String lookup) throws IOException {
        lk = lookup;
        File fileinput = new File(workbook);
        FileInputStream stream = new FileInputStream(fileinput);
        XSSFWorkbook wb = new XSSFWorkbook(stream);
        XSSFSheet wbsheet = wb.getSheet(sheetname);
        int lastR = wbsheet.getLastRowNum();
        XSSFRow Row = wbsheet.getRow(0);
//        String lastRowV = wbsheet.getRow(lastR).getCell(0).getStringCellValue();
        int lastCell = Row.getLastCellNum();
        int rowNum = 0;
        for (int i = 1; i <= lastR; i++) {
            String RowCheck = wbsheet.getRow(i).getCell(0).getStringCellValue().trim();
            if (RowCheck.equalsIgnoreCase(lookup)) {
                rowNum = i;
                break;
            }
        }
        Map<String, String> map1 = new HashMap<String, String>();
        if (!(rowNum == 0)) {
            for (int j = 1; j < lastCell; j++) {
                String Value = null;
                XSSFCell valueCell = wbsheet.getRow(rowNum).getCell(j);
                CellType valueType = valueCell.getCellType();
                if (valueType == CellType.STRING) {
                    Value = valueCell.getStringCellValue();
                } else if (valueType == CellType.NUMERIC) {
                    Value = NumberToTextConverter.toText(valueCell.getNumericCellValue());
                }
                String Key = Row.getCell(j).getStringCellValue();
                map1.put(Key, Value);
            }
        } else {
            System.out.println("Row does not exist");
        }
        System.out.println(map1);
        stream.close();
        return map1;
    }

    public Map<String, String> readCSV(String filePath, String testCaseName) throws IOException, CsvException {
        List<Map<String, String>> dataList = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(filePath));


        List<String[]> lines = reader.readAll();
        String[] headers = lines.get(0);
        dataList = lines.stream()
                .skip(1) // Skip the header row
                .map(values -> {

                    Map<String, String> rowMap = new HashMap<>();
                    for (int i = 0; i < headers.length; i++) {
//                        String value = (i < values.length) ? values[i].trim() : "";
                        rowMap.put(headers[i], values[i]);
                    }
                    return rowMap;
                })
                .collect(Collectors.toList());
        Optional<Map<String, String>> dataMap = dataList.stream().filter(entry -> entry.containsValue(testCaseName)).findFirst();

        return dataMap.orElseThrow(() -> new ExceptionUtils("TestCaseName not found"));


    }


    public List<Map<String, String>> getData(String excelFilePath, String sheetName)
            throws InvalidFormatException, IOException {
        Sheet sheet = getSheetByName(excelFilePath, sheetName);
        return readSheet(sheet);
    }

    public List<Map<String, String>> getData(String excelFilePath, int sheetNumber)
            throws InvalidFormatException, IOException {
        Sheet sheet = getSheetByIndex(excelFilePath, sheetNumber);
        return readSheet(sheet);
    }

    private Sheet getSheetByName(String excelFilePath, String sheetName) throws IOException, InvalidFormatException {
        Sheet sheet = getWorkBook(excelFilePath).getSheet(sheetName);
        return sheet;
    }

    private Sheet getSheetByIndex(String excelFilePath, int sheetNumber) throws IOException, InvalidFormatException {
        Sheet sheet = getWorkBook(excelFilePath).getSheetAt(sheetNumber);
        return sheet;
    }

    private static Workbook getWorkBook(String excelFilePath) throws IOException, InvalidFormatException {
        return WorkbookFactory.create(new File(excelFilePath));
    }

    private List<Map<String, String>> readSheet(Sheet sheet) {
        Row row;
        int totalRow = sheet.getPhysicalNumberOfRows();
        List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
        int headerRowNumber = getHeaderRowNumber(sheet);
        if (headerRowNumber != -1) {
            int totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();
            int setCurrentRow = 1;
            for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
                row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
                LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
                }
                excelRows.add(columnMapdata);
            }
        }
        return excelRows;
    }

    private int getHeaderRowNumber(Sheet sheet) {
        Row row;
        int totalRow = sheet.getLastRowNum();
        for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) {
            row = getRow(sheet, currentRow);
            if (row != null) {
                int totalColumn = row.getLastCellNum();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    Cell cell;
                    cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    if (cell.getCellType() == CellType.STRING) {
                        return row.getRowNum();

                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        return row.getRowNum();

                    } else if (cell.getCellType() == CellType.BOOLEAN) {
                        return row.getRowNum();
                    } else if (cell.getCellType() == CellType.ERROR) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return (-1);
    }

    private Row getRow(Sheet sheet, int rowNumber) {
        return sheet.getRow(rowNumber);
    }

    private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
        LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
        Cell cell;
        if (row == null) {
            if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                    .getCellType() != CellType.BLANK) {
                String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
                        .getStringCellValue();
                columnMapdata.put(columnHeaderName, "");
            }
        } else {
            cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cell.getCellType() == CellType.STRING) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, cell.getStringCellValue());
                }
            } else if (cell.getCellType() == CellType.NUMERIC) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
                }
            } else if (cell.getCellType() == CellType.BLANK) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, "");
                }
            } else if (cell.getCellType() == CellType.BOOLEAN) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
                }
            } else if (cell.getCellType() == CellType.ERROR) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
                }
            }
        }
        return columnMapdata;
    }

    public static void statusUpdate(String excelpath, Map<String, String> testCaseStatusMap) {
        try (FileInputStream fis = new FileInputStream(excelpath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Sheet1"); // Assuming data is in the first sheet

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell testCaseNumberCell = row.getCell(0);
                    if (testCaseNumberCell != null) {
                        String testCaseNumber = testCaseNumberCell.getStringCellValue();
                        if (testCaseStatusMap.containsKey(testCaseNumber)) {
                            row.createCell(7, CellType.STRING).setCellValue(testCaseStatusMap.get(testCaseNumber));
                        }
                    }
                }
            }

            try (FileOutputStream fos = new FileOutputStream(excelpath)) {
                workbook.write(fos);
            }


        } catch (Exception e) {
            throw new ExceptionUtils(e.getMessage());
        }
    }

}
