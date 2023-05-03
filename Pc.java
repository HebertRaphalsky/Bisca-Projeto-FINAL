import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Pc
 */
public class Pc extends Player {
    private static final String[] _Nipes = { "Ouro", "Paus", "Copas", "Espadas" };
    private ArrayList<Carta> _unknownCartas;
    private HashMap<Carta, Integer> _worth;

    public Pc(String name) {
        this.name = name;
        this._mao = new ArrayList<Carta>();
        this._points = new ArrayList<Carta>();
        this._populateUnknownCartas();
    }
    public void draw(Carta carta) {
        this._mao.add(carta);
        if (Game.getDeck().size() == 0 || Game.getDeck().size() == 1) {
            this._unknownCartas.add(Game.getTrunfo());
        }
        this._unknownCartas.remove(carta);
    }

    public Carta play() {
        System.out.println(this._mao); // For testing purposes
        this._evaluateFirst();
        Carta Carta = this._pickLowest();
        this._mao.remove(Carta);
        return Carta;
    }

    public Carta play(Carta played) {
        System.out.println(this._mao); // For testing purposes
        this._unknownCartas.remove(played);
        this._evaluateLast(played);
        Carta Carta = this._pickLowest();
        this._mao.remove(Carta);
        return Carta;
    }


 
}