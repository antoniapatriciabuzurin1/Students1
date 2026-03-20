package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {

    public static void main(String[] args) {
        Path inputPath = Paths.get("studenti_in.txt");
        Path outputPath = Paths.get("studenti_out.txt");
        List<Students> listaStudenti = new ArrayList<>();

        try {
            List<String> linii = Files.readAllLines(inputPath);

            for (String linie : linii) {
                if (linie.trim().isEmpty()) continue;

                String[] date = linie.split(",");
                int nrMatricol = Integer.parseInt(date[0].trim());
                String prenume = date[1].trim();
                String nume = date[2].trim();
                String formatie = date[3].trim();

                listaStudenti.add(new Students(nrMatricol, prenume, nume, formatie));
            }

            System.out.println("Studentii cititi din fisier:");
            listaStudenti.forEach(System.out::println);

            Collections.sort(listaStudenti, new Comparator<Students>() {
               @Override
               public int compare(Students s1, Students s2) {
                   return s1.getNume().compareTo(s2.getNume());
               }
            });

            List<String> liniiDeSalvat = new ArrayList<>();
            for (Students s : listaStudenti) {
                liniiDeSalvat.add(s.getNumarMatricol() + ", " + s.getPrenume() + ", " + s.getNume() + ", " + s.getFormatieDeStudiu());
            }
            Files.write(outputPath, liniiDeSalvat);
            System.out.println("\nLista sortată a fost salvată în studenti_out.txt");
        } catch (IOException e) {
            System.err.println("Eroare la procesarea fișierelor: " + e.getMessage());
        }
    }
}