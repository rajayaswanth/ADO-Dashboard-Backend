package com.imagin.service.impl;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.imagin.model.SprintDetails;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelGenerator {

    private List<SprintDetails> sprintDetails;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List <SprintDetails> sprintDetails) {
        this.sprintDetails = sprintDetails;
        workbook = new XSSFWorkbook();
    }
    
    private void writeHeader() {
        sheet = workbook.createSheet("Current Sprint");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat((short) BuiltinFormats.getBuiltinFormat("m/d/yy h:mm"));
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Number", style);
        createCell(row, 1, "Title", style);
        createCell(row, 2, "Assigned To", style);
        createCell(row, 3, "Story Points", style);
        createCell(row, 4, "Status", style);
        createCell(row, 5, "Dev Start", cellStyle);
        createCell(row, 6, "PR Raised", cellStyle);
        createCell(row, 7, "Dev Complete", cellStyle);
        createCell(row, 8, "QA Complete", cellStyle);
    }
    
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof Date) {
            cell.setCellValue((String) valueOfCell.toString());
        }
        cell.setCellStyle(style);
    }
    
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (SprintDetails record: sprintDetails) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getUserStoryNumber(), style);
            createCell(row, columnCount++, record.getTitle(), style);
            createCell(row, columnCount++, record.getAssignedTo(), style);
            createCell(row, columnCount++, record.getStoryPoints(), style);
            createCell(row, columnCount++, record.getState(), style);
            createCell(row, columnCount++, record.getDevStartDate(), style);
            createCell(row, columnCount++, record.getPrCreatedDate(), style);
            createCell(row, columnCount++, record.getDevCompletedDate(), style);
            createCell(row, columnCount++, record.getQaCompletedDate(), style);
        }
    }
    
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}