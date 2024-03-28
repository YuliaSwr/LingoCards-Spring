package ua.sida.lingocards.app.filebuilder;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ua.sida.lingocards.app.model.Flashcard;
import ua.sida.lingocards.app.model.FlashSet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Service class for building Excel files containing flashcards
 */
@Service
public class ExcelFileBuilder implements FileBuilder {

    public byte[] buildFile(FlashSet flashSet) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Flashcards");

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment((short) 1);

        Row flashSetNameRow = sheet.createRow(0);
        Cell cell1 = flashSetNameRow.createCell(0);
        cell1.setCellValue("Flashcard Set Name");
        cell1.setCellStyle(style);
        Cell cell2 = flashSetNameRow.createCell(1);
        cell2.setCellValue(flashSet.getName());

        Row authorNameRow = sheet.createRow(1);
        Cell cell3 = authorNameRow.createCell(0);
        cell3.setCellValue("FlashSet Author");
        cell3.setCellStyle(style);
        Cell cell4 = authorNameRow.createCell(1);
        cell4.setCellValue(flashSet.getUser().getFirstName() + " " + flashSet.getUser().getLastName());

        Row dateRow = sheet.createRow(2);
        Cell cell5 = dateRow.createCell(0);
        cell5.setCellValue("Generation Date");
        cell5.setCellStyle(style);
        Cell cell6 = dateRow.createCell(1);
        cell6.setCellValue(LocalDate.now().toString());

        Row emptyRow = sheet.createRow(3);

        List<Flashcard> flashcards = flashSet.getFlashcards();
        int rowNum = 4;
        for (Flashcard flashcard : flashcards) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(flashcard.getFront());
            row.createCell(1).setCellValue(flashcard.getBack());
        }

        for (int i = 0; i < 2; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        outputStream.close();

        return outputStream.toByteArray();
    }
}

