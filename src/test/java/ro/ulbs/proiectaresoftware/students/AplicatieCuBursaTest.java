package ro.ulbs.proiectaresoftware.students;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class AplicatieCuBursaTest {
    private AplicatieCuBursa app;
    @BeforeEach
    public void setUp(){
        app = new AplicatieCuBursa();
    }
    @Test
    public void testSorteaza() {
        List<StudentBursier> lista = app.genereaza();
        app.sorteaza(lista);
        Assertions.assertEquals("ISM141/1", lista.get(0).getFormatieDeStudiu());
        Assertions.assertEquals("Ioan", lista.get(0).getPrenume());

        boolean gasitOrdineaCorecta = false;
        for(int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getNume().equals("Popescu") && lista.get(i+1).getNume().equals("Popescu")) {
                if(lista.get(i).getCuantumBursa() < lista.get(i+1).getCuantumBursa()) {
                    gasitOrdineaCorecta = true;
                }
            }
        }
        Assertions.assertTrue(gasitOrdineaCorecta, "Studentii cu acelasi nume trebuie sortati dupa bursa");
    }
}
