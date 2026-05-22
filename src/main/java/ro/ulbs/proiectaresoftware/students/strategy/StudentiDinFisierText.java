package ro.ulbs.proiectaresoftware.students.strategy;

import ro.ulbs.proiectaresoftware.students.Students;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentiDinFisierText implements IStudentiImport {

    private final String fileName;

    public StudentiDinFisierText(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Students> doImport() {

        List<Students> studenti = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] valori = line.split(";");

                int nrMatricol = Integer.parseInt(valori[0]);
                String prenume = valori[1];
                String nume = valori[2];
                String formatie = valori[3];
                float nota = Float.parseFloat(valori[4]);

                studenti.add(new Students(
                        nrMatricol,
                        prenume,
                        nume,
                        formatie,
                        nota
                ));
            }

            System.out.println("Import TXT realizat!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return studenti;
    }
}