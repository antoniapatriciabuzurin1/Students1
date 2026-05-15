package lab9;
import ro.ulbs.proiectaresoftware.students.Students;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentApplication {
    public static void main(String[] args) {
        List<Students> studentiCuNote = Arrays.asList(
                new Students(1025,"Andrei","Popa","ISM141/2", 8.70f),
                new Students(1024,"Ioan","Mihalcea","ISM141/1", 10),
                new Students(1026,"Anamaria","Prodan","TI131/1", 8.90f),
                new Students(1029,"Bianca","Popescu","TI131/1,", 10),
                new Students(1029,"Maria","Pana","TI131/2,", 4.10f),
                new Students(1029,"Gabriela","Mohanu","TI131/2,", 7.33f),
                new Students(1029,"Marius","Nasta","TI131/2,", 3.20f),
                new Students(1029,"Marius","Nasta","TI131/1,", 5.12f),
                new Students(1029,"Andrei","Dobrescu","TI131/2,", 2.22f)
        );

        System.out.println("Studenti cu nota 10: ");
        studentiCuNote.stream()
                .filter(s -> s.getNota() == 10)
                .forEach(System.out::println);

        System.out.println("\nStudenti cu nota sub 5: ");
        studentiCuNote.stream()
                .filter(s -> s.getNota() < 5)
                .forEach(System.out::println);

        System.out.println("Lista modificata: ");
        List<Students> studentiModificati = studentiCuNote.stream()
                .map(s -> {
                    if (s.getNota() < 4) {
                        return new Students(
                                s.getNumarMatricol(),
                                s.getPrenume(),
                                s.getNume(),
                                s.getFormatieDeStudiu(),
                                4f
                        );
                    }
                    return s;
                })
                .collect(Collectors.toList());
        studentiModificati.forEach(System.out::println);

        float suma = (float) studentiCuNote.stream()
                .mapToDouble(Students::getNota)
                .sum();
        System.out.println("\nSuma notelor: " + suma);

        double media = studentiCuNote.stream()
                .mapToDouble(Students::getNota)
                .average()
                .orElse(0);
        System.out.println("Media notelor: " + media);
    }
}
