package ro.ulbs.proiectaresoftware.students.strategy;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ro.ulbs.proiectaresoftware.students.Students;

import java.io.FileOutputStream;
import java.util.List;

public class StudentiInFisierXlsx implements IStudentiExport {

    private final String fileName;

    public StudentiInFisierXlsx(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void doExport(List<Students> studenti) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Studenti");

            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("Nr matricol");
            header.createCell(1).setCellValue("Prenume");
            header.createCell(2).setCellValue("Nume");
            header.createCell(3).setCellValue("Formatie");
            header.createCell(4).setCellValue("Nota");

            int rowIndex = 1;

            for (Students student : studenti) {

                Row row = sheet.createRow(rowIndex++);

                row.createCell(0)
                        .setCellValue(student.getNumarMatricol());

                row.createCell(1)
                        .setCellValue(student.getPrenume());

                row.createCell(2)
                        .setCellValue(student.getNume());

                row.createCell(3)
                        .setCellValue(student.getFormatieDeStudiu());

                row.createCell(4)
                        .setCellValue(student.getNota());
            }

            FileOutputStream fos =
                    new FileOutputStream(fileName);

            workbook.write(fos);

            fos.close();

            System.out.println("Export XLSX realizat!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}