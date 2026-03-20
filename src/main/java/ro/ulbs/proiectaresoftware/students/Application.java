package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Path inputPath = Paths.get("studenti_in.txt");
        Path outputPath = Paths.get("studenti_out.txt");
        Path outputSortedPath = Paths.get("studenti_out_sorted.txt");
        List<Students> listaStudenti = new ArrayList<>();

       /* List<Students> listaStudenti = new ArrayList<>();

        Students s1 = new Students(112, "Ioan", "Popa", "TI21/1");
        Students s2 = new Students(112, "Maria", "Oprea", "TI21/1");
        Students s3 = new Students(120, "Alis", "Popa", "TI21/2");
        Students s4 = new Students(122, "Mihai", "Vecerdea", "TI22/1");
        Students s5 = new Students(122, "Eugen", "Uritescu", "TI22/2");

        listaStudenti.add(s1);
        listaStudenti.add(s2);
        listaStudenti.add(s3);
        listaStudenti.add(s4);
        listaStudenti.add(s5);

        // a) afisare lista folosind for-each
        for (Students s : listaStudenti) {
            System.out.println(s);
        }

        // b)
        Students cautat1 = new Students(120, "Alis", "Popa", "TI21/2");
        System.out.println("Exista Alis Popa? " + existaStudent(listaStudenti, cautat1));

        // c)
        Students cautat2 = new Students(112, "Maria", "Popa", "TI21/1");
        System.out.println("Exista Maria Popa? " + existaStudent(listaStudenti, cautat2));

         Set<Students> setStudenti = new HashSet<>(listaStudenti);

        // b)
        Students cautat1 = new Students(120, "Alis", "Popa", "TI21/2");
        System.out.println("Exista Alis Popa? " + setStudenti.contains(cautat1));

        // c)
        Students cautat2 = new Students(112, "Maria", "Popa", "TI21/1");
        System.out.println("Exista Maria Popa? " + setStudenti.contains(cautat2));
    }

    public static boolean existaStudent(List<Students> lista, Students student) {

        for (Students s : lista) {

            if (s.getPrenume().equals(student.getPrenume()) &&
                    s.getNume().equals(student.getNume()) &&
                    s.getFormatieDeStudiu().equals(student.getFormatieDeStudiu())) {

                return true;
            }
        }

        return false;
    } */

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
            Collections.sort(listaStudenti, new Comparator<Students>() {
                @Override
                public int compare(Students s1, Students s2) {
                    int res = s1.getFormatieDeStudiu().compareTo(s2.getFormatieDeStudiu());

                    if (res == 0) {
                        return s1.getNume().compareTo(s2.getNume());
                    }
                    return res;
                }
            });
            List<String> liniiTema = new ArrayList<>();
            for (Students s : listaStudenti) {
                liniiTema.add(s.getNumarMatricol() + ", " + s.getPrenume() + ", " + s.getNume() + ", " + s.getFormatieDeStudiu());
            }
            Files.write(outputSortedPath, liniiTema);
            System.out.println("Tema (3.5.3) a fost salvată în studenti_out_sorted.txt");
        } catch (IOException e) {
            System.err.println("Eroare la procesarea fișierelor: " + e.getMessage());
        }
    }
}