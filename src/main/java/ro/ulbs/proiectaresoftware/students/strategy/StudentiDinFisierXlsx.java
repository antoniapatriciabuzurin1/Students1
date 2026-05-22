package ro.ulbs.proiectaresoftware.students.strategy;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ro.ulbs.proiectaresoftware.students.Students;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentiDinFisierXlsx implements IStudentiImport {

    private final String fileName;

    public StudentiDinFisierXlsx(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Students> doImport() {

        List<Students> studenti = new ArrayList<>();

        try (Workbook workbook =
                     new XSSFWorkbook(new FileInputStream(fileName))) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                int nrMatricol =
                        (int) row.getCell(0).getNumericCellValue();

                String prenume =
                        row.getCell(1).getStringCellValue();

                String nume =
                        row.getCell(2).getStringCellValue();

                String formatie =
                        row.getCell(3).getStringCellValue();

                float nota =
                        (float) row.getCell(4).getNumericCellValue();

                studenti.add(new Students(
                        nrMatricol,
                        prenume,
                        nume,
                        formatie,
                        nota
                ));
            }

            System.out.println("Import XLSX realizat!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return studenti;
    }
}