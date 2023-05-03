package test.Bisca;
import bisca.Carta;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CartaTest {
    private static final String[] _Nipes = { "Ouro", "Paus", "Copas", "Espadas" };
    private static final String[] _Tipos = { "A", "K", "Q", "J", "7" };

    @Test
    public void testCartaCreation() {
        for (String nipe : _Nipes) {
            for (String tipo : _Tipos) {
            	Carta carta = new Carta(tipo, nipe);
                assertNotNull(carta);
                assertEquals(tipo, carta.getTipo());
                assertEquals(nipe, carta.getNipe());
            }
        }
    }

    private String getTipo() {
        return this.getTipo();
    }

    private String getNipe() {
        return this.getNipe();
    }

	@Test
    public void testCartaCalcValue() {
        for (String nipe : _Nipes) {
            for (String tipo : _Tipos) {
                Carta carta = new Carta(tipo, nipe);
                int expectedValue = getExpectedValue(tipo);
                assertEquals(expectedValue, carta.getValor());
            }
        }
    }

    private int getExpectedValue(String tipo) {
        switch (tipo) {
            case "A":
                return 11;
            case "K":
                return 4;
            case "Q":
                return 2;
            case "J":
                return 3;
            case "7":
                return 10;
            default:
                return 0;
        }
    }
}