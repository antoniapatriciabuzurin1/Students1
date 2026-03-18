package ro.ulbs.proiectaresoftware.students;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {

    public static void main(String[] args) {
        List<Students> listaStudenti = new ArrayList<>();

        listaStudenti.add(new Students(112, "Ioan", "Popa", "TI21/1"));
        listaStudenti.add(new Students(112, "Maria", "Oprea", "TI21/1"));
        listaStudenti.add(new Students(120, "Alis", "Popa", "TI21/2"));
        listaStudenti.add(new Students(122, "Mihai", "Vecerdea", "TI22/1"));
        listaStudenti.add(new Students(122, "Eugen", "Uritescu", "TI22/2"));

        System.out.println("Lista de studenti:");
        for (Students s : listaStudenti) {
            System.out.println(s);
        }
        System.out.println("--------------------------------------");

        Set<Students> cautareRapida = new HashSet<>(listaStudenti);

        Students cautat1 = new Students(120, "Alis", "Popa", "TI21/2");
        Students cautat2 = new Students(112, "Maria", "Popa", "TI21/1");

        System.out.println("Exista Alis Popa? (Căutare O(1)): " + cautareRapida.contains(cautat1));
        System.out.println("Exista Maria Popa? (Căutare O(1)): " + cautareRapida.contains(cautat2));
    }
}