package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Application {

    public static void main(String[] args) {
        Path inputPath = Paths.get("studenti.txt");
        Path notesPath = Paths.get("note_anon.txt");

        Map<Integer, Students> mapStudenti = new HashMap<>();

        try {
            List<String> liniiStudenti = Files.readAllLines(inputPath);

            for (String linie : liniiStudenti) {
                if (linie.trim().isEmpty()) continue;

                String[] date = linie.split(",");

                int nrMatricol = Integer.parseInt(date[0].trim());
                String prenume = date[1].trim();
                String nume = date[2].trim();
                String formatie = date[3].trim();

                mapStudenti.put(
                        nrMatricol,
                        new Students(nrMatricol, prenume, nume, formatie)
                );
            }

            if (Files.exists(notesPath)) {
                List<String> liniiNote = Files.readAllLines(notesPath);

                for (String linie : liniiNote) {
                    if (linie.trim().isEmpty()) continue;

                    String[] dateNote = linie.split(",");

                    int nrMatricolNote = Integer.parseInt(dateNote[0].trim());
                    float notaVal = Float.parseFloat(dateNote[1].trim());

                    if (mapStudenti.containsKey(nrMatricolNote)) {
                        Students s = mapStudenti.get(nrMatricolNote);

                        mapStudenti.put(
                                nrMatricolNote,
                                new Students(
                                        s.getNumarMatricol(),
                                        s.getPrenume(),
                                        s.getNume(),
                                        s.getFormatieDeStudiu(),
                                        notaVal
                                )
                        );
                    }
                }
            }

            System.out.println("Catalog Studenti:");
            for (Students s : mapStudenti.values()) {
                System.out.println(s);
            }

            System.out.println("\n--- Rezultate cautare nota ---");

            float notaM = gasesteNota("Bianca", "Popescu", mapStudenti);
            System.out.println("Nota lui Bianca Popescu: " + notaM);

            float notaN = gasesteNota("Ioan", "Popa", mapStudenti);
            System.out.println("Nota lui Ioan Popa: " + notaN);

            Set<Students> setStudenti = new HashSet<>(mapStudenti.values());

            setStudenti = imparteInDouaFormatii(
                    setStudenti,
                    "TI 211_1",
                    "TI 211_2"
            );

            System.out.println("\nStudenti impartiti in doua formatii:");
            for (Students s : setStudenti) {
                System.out.println(s);
            }

            // 8.5.4 a
            String xlsFileName = "laborator8_students.xlsx";
            writeToXls(setStudenti, xlsFileName);

            // 8.5.4 b
            List<Students> studentsFromXls = readFromXls(xlsFileName);

            System.out.println("\nStudenti cititi din xlsx:");
            for (Students st : studentsFromXls) {
                System.out.println(st);
            }

        } catch (IOException e) {
            System.err.println("Eroare la procesarea fisierelor: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Eroare la formatul numerelor in fisier: " + e.getMessage());
        }
    }

    public static float gasesteNota(String prenume,
                                    String nume,
                                    Map<Integer, Students> tineri) {

        Map<String, Students> mapDupaNume = new HashMap<>();

        for (Students s : tineri.values()) {
            String cheieUnica = s.getNume() + " " + s.getPrenume();
            mapDupaNume.put(cheieUnica, s);
        }

        String deCautat = nume + " " + prenume;

        if (mapDupaNume.containsKey(deCautat)) {
            return mapDupaNume.get(deCautat).getNota();
        }

        return 0.0f;
    }

    public static Students schimbaFormatia(Students st,
                                           String nouaFormatieDeStudiu) {

        return new Students(
                st.getNumarMatricol(),
                st.getPrenume(),
                st.getNume(),
                nouaFormatieDeStudiu,
                st.getNota()
        );
    }

    public static Set<Students> imparteInDouaFormatii(Set<Students> studenti,
                                                      String formatia1,
                                                      String formatia2) {

        Set<Students> rezultat = new HashSet<>();

        int limita = (studenti.size() + 1) / 2;
        int index = 0;

        for (Students st : studenti) {
            if (index < limita) {
                rezultat.add(schimbaFormatia(st, formatia1));
            } else {
                rezultat.add(schimbaFormatia(st, formatia2));
            }

            index++;
        }

        return rezultat;
    }

    public static void writeToXls(Set<Students> studenti, String fileName) {

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(fileName)) {

            Sheet sheet = workbook.createSheet("Studenti");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Nr matricol");
            header.createCell(1).setCellValue("Prenume");
            header.createCell(2).setCellValue("Nume");
            header.createCell(3).setCellValue("Formatie");
            header.createCell(4).setCellValue("Nota");

            int rowIndex = 1;

            for (Students st : studenti) {
                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(st.getNumarMatricol());
                row.createCell(1).setCellValue(st.getPrenume());
                row.createCell(2).setCellValue(st.getNume());
                row.createCell(3).setCellValue(st.getFormatieDeStudiu());
                row.createCell(4).setCellValue(st.getNota());
            }

            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(fos);

            System.out.println("\nFisierul " + fileName + " a fost creat.");

        } catch (Exception e) {
            System.out.println("Eroare la scrierea fisierului xlsx: " + e.getMessage());
        }
    }

    public static List<Students> readFromXls(String fileName) {

        List<Students> studenti = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row == null) continue;

                int nrMatricol = (int) row.getCell(0).getNumericCellValue();
                String prenume = row.getCell(1).getStringCellValue();
                String nume = row.getCell(2).getStringCellValue();
                String formatie = row.getCell(3).getStringCellValue();
                float nota = (float) row.getCell(4).getNumericCellValue();

                studenti.add(new Students(
                        nrMatricol,
                        prenume,
                        nume,
                        formatie,
                        nota
                ));
            }

        } catch (Exception e) {
            System.out.println("Eroare la citirea fisierului xlsx: " + e.getMessage());
        }

        return studenti;
    }
}