package ro.ulbs.proiectaresoftware.students;
import ro.ulbs.proiectaresoftware.students.strategy.*;
import java.util.Arrays;
import java.util.List;

public class AplicatieCuStrategy {

    public static void main(String[] args) {

        List<Students> studenti = Arrays.asList(

                new Students(1025,
                        "Andrei",
                        "Popa",
                        "ISM141/2",
                        8.70f),

                new Students(1024,
                        "Ioan",
                        "Mihalcea",
                        "ISM141/1",
                        10f),

                new Students(1026,
                        "Anamaria",
                        "Prodan",
                        "TI131/1",
                        8.90f),

                new Students(1029,
                        "Bianca",
                        "Popescu",
                        "TI131/1",
                        10f)
        );

        Exporter exporter = new Exporter();

        System.out.println("=== CONSOLE ===");

        exporter.startExport(
                new StudentiInConsola(),
                studenti
        );

        System.out.println();

        System.out.println("=== TXT ===");

        exporter.startExport(
                new StudentiInFisierText("studenti.txt"),
                studenti
        );

        System.out.println();

        System.out.println("=== XLSX ===");

        exporter.startExport(
                new StudentiInFisierXlsx("studenti.xlsx"),
                studenti
        );

        Importer importer = new Importer();

        System.out.println();

        System.out.println("=== IMPORT TXT ===");

        List<Students> studentiTxt =
                importer.startImport(
                        new StudentiDinFisierText("studenti.txt")
                );

        studentiTxt.forEach(System.out::println);

        System.out.println();

        System.out.println("=== IMPORT XLSX ===");

        List<Students> studentiXlsx =
                importer.startImport(
                        new StudentiDinFisierXlsx("studenti.xlsx")
                );

        studentiXlsx.forEach(System.out::println);
    }
}