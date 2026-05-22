package ro.ulbs.proiectaresoftware.students.strategy;

import ro.ulbs.proiectaresoftware.students.Students;

import java.util.List;

public class StudentiInConsola implements IStudentiExport {

    @Override
    public void doExport(List<Students> studenti) {

        for (Students student : studenti) {
            System.out.println(student);
        }
    }
}