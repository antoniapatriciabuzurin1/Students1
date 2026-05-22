package ro.ulbs.proiectaresoftware.students.strategy;

import ro.ulbs.proiectaresoftware.students.Students;

import java.util.List;

public class Exporter {

    public void startExport(IStudentiExport strategy,
                            List<Students> studenti) {

        strategy.doExport(studenti);
    }
}