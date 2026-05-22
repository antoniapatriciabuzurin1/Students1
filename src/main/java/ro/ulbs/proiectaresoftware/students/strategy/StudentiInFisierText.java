package ro.ulbs.proiectaresoftware.students.strategy;

import ro.ulbs.proiectaresoftware.students.Students;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class StudentiInFisierText implements IStudentiExport {

    private final String fileName;

    public StudentiInFisierText(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void doExport(List<Students> studenti) {

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(fileName))) {

            for (Students student : studenti) {

                writer.printf("%d;%s;%s;%s;%.2f%n",
                        student.getNumarMatricol(),
                        student.getPrenume(),
                        student.getNume(),
                        student.getFormatieDeStudiu(),
                        student.getNota());
            }

            System.out.println("Export TXT realizat!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}