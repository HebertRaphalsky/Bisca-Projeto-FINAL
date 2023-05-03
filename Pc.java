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
    
 
}