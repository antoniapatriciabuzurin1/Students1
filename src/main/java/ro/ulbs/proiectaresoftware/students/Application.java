package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
}