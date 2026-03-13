package ro.ulbs.proiectaresoftware.students;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<Students> listaStudenti = new ArrayList<>();

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

        for (Students s : listaStudenti) {
            System.out.println(s);
        }

        Students cautat1 = new Students(120, "Alis", "Popa", "TI21/2");
        System.out.println("Exista Alis Popa? " + existaStudent(listaStudenti, cautat1));

        Students cautat2 = new Students(112, "Maria", "Popa", "TI21/1");
        System.out.println("Exista Maria Popa? " + existaStudent(listaStudenti, cautat2));

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
    }
}