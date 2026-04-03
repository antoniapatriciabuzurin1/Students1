package ro.ulbs.proiectaresoftware.students;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class MainBursieri {
    public static void writeToFile(String filename, Collection<? extends Students> colectie) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(filename))) {
            for (Students s : colectie) {
                pw.println(s.toString());
            }
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișier: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        List<StudentBursier> bursieri = new ArrayList<>();

        bursieri.add(new StudentBursier(1025, "Andrei", "Popa", "ISM141/2", 8.70f, 725.50));
        bursieri.add(new StudentBursier(1024, "Ioan", "Mihalcea", "ISM141/1", 9.80f, 801.10));
        bursieri.add(new StudentBursier(1026, "Anamaria", "Prodan", "TI131/1", 8.90f, 745.50));
        bursieri.add(new StudentBursier(1029, "Bianca", "Popescu", "TI131/1", 9.10f, 780.80));

        writeToFile("bursieri_out.txt", bursieri);
    }
}
