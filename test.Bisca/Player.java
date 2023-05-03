package test.Bisca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import bisca.Carta;
import bisca.Player;


public class PlayerTest {

    private Player player;


    @Before
    public void setUp() {
        player = new MyPlayer()a;
    }

    @Test
    public void testGetHand() {
        assertNotNull(player.getHand());
        assertEquals(0, player.getHand().size());
    }

    @Test
    public void testGetPoints() {
        assertNotNull(player.getPoints());
        assertEquals(0, player.getPoints().size());
    }

    @Test
    public void testAddToPoints() {
        ArrayList<Carta> points = player.getPoints();
        Carta c1 = new Carta("A", "Ouro");
        Carta c2 = new Carta("K", "Paus");
        Carta c3 = new Carta("Q", "Copas");
        Carta c4 = new Carta("J", "Espadas");
        player.addToPoints(c1, c2, c3, c4);
        assertEquals(4, points.size());
        assertEquals(c1, points.get(0));
        assertEquals(c2, points.get(1));
        assertEquals(c3, points.get(2));
        assertEquals(c4, points.get(3));
    }

    @Test
    public void testCountPoints() {
        ArrayList<Carta> points = player.getPoints();
        Carta c1 = new Carta("A", "Ouro");
        Carta c2 = new Carta("K", "Paus");
        Carta c3 = new Carta("Q", "Copas");
        Carta c4 = new Carta("J", "Espadas");
        points.add(c1);
        points.add(c2);
        points.add(c3);
        points.add(c4);
        assertEquals(20, player.countPoints());
    }

    // Implementação da classe MyPlayer para os testes
    private class MyPlayer extends Player {
        public MyPlayer() {
            this._mao = new ArrayList<Carta>();
            this._points = new ArrayList<Carta>();
            this.name = "Test Player";
        }

        @Override
        public Carta play() {
            return null;
        }

        @Override
        public Carta play(Carta played) {
            return null;
        }

        @Override
        public void draw(Carta Carta) {
        }
    }
}
