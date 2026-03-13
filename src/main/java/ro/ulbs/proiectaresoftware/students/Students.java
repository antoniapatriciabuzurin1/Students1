package ro.ulbs.proiectaresoftware.students;

public class Students {

    private int numarMatricol;
    private String prenume;
    private String nume;
    private String formatieDeStudiu;

    public Students(int numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
    }

    public int getNumarMatricol() {
        return numarMatricol;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public String getFormatieDeStudiu() {
        return formatieDeStudiu;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-10s %-12s %-10s",
                numarMatricol,
                prenume,
                nume,
                formatieDeStudiu);
    }
}