package ro.ulbs.proiectaresoftware.students.strategy;

import ro.ulbs.proiectaresoftware.students.Students;

import java.util.List;

public class Importer {

    public List<Students> startImport(IStudentiImport strategy) {
        return strategy.doImport();
    }
}