package ro.ulbs.proiectaresoftware.students;
import java.util.Objects;


public class Students {

    private int numarMatricol;
    private String prenume;
    private String nume;
    private String formatieDeStudiu;
    private float nota;

    public Students(int numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
    }

    public float getNota() {
        return nota;
    }
    public void setNota(float nota) {
        this.nota = nota;
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
        return String.format("%-10d %-10s %-12s %-10s | Nota: %.2f",
                numarMatricol, prenume, nume, formatieDeStudiu, nota);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students student = (Students) o;
        return numarMatricol == student.numarMatricol &&
                Objects.equals(prenume, student.prenume) &&
                Objects.equals(nume, student.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numarMatricol, prenume, nume);
    }
}