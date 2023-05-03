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

    private void _evaluateLast(Carta played) {
        this._worth = new HashMap<Carta, Integer>();
        Integer CartaWorth;
        for (Carta Carta : this._mao) {
            CartaWorth = 0;
            if (Carta.getNipe().compareTo(played.getNipe()) == 0) { // Se a tua Carta e a Carta jogada são do mesmo
                // naipe
                CartaWorth--;
                CartaWorth -= this._isCartaDirectlyBelow(Carta, played);
                CartaWorth += this._evaluateBelow(Carta);
                CartaWorth += this._evaluateChanceToPass(Carta);
            } else {
                if (Carta.getNipe().compareTo(Game.getTrunfo().getNipe()) == 0) { // Se a tua Carta é um trunfo
                    if (played.getValor() >= 2) {
                        CartaWorth -= this._evaluateTrumpValue(Carta);
                    } else {
                        CartaWorth += 2;
                    }
                } else if (played.getNipe().compareTo(Game.getTrunfo().getNipe()) == 0) { // Se a Carta do oponente é
                                                                                          // um
                    // trunfo
                    CartaWorth = this._giveWorth(Carta);
                } else { // Se são naipes diferentes
                    CartaWorth = this._giveWorth(Carta);
                    if (CartaWorth < 1) {
                        CartaWorth -= 2;
                    }
                }
            }
            this._worth.put(Carta, CartaWorth);
        }
    }

    private int _evaluateTrumpValue(Carta Carta) {
        int trumpWorth = '2';
        switch (Carta.getValor()) {
            case 11:
                trumpWorth = 1;
                break;
            case 10:
                trumpWorth = 2;
                break;
            case 4:
                trumpWorth = 3;
                break;
            case 3:
                trumpWorth = 4;
                break;
            case 2:
                trumpWorth = 5;
                break;
            default:
                trumpWorth = 6;
                break;
        }
        return trumpWorth;
    }
    private int _isCartaDirectlyBelow(Carta Carta, Carta played) {
        switch (Carta.getTipo()) {
            case "A":
                if (played.getTipo().compareTo("7") == 0) {
                    return 50;
                } else if (played.getTipo().compareTo("K") == 0) {
                    return 1;
                }
                break;
            case "7":
                if (played.getTipo().compareTo("K") == 0) {
                    return 50;
                } else if (played.getTipo().compareTo("J") == 0) {
                    return 1;
                }
                break;
            case "K":
                if (played.getTipo().compareTo("J") == 0) {
                    return 50;
                } else if (played.getTipo().compareTo("Q") == 0) {
                    return 1;
                }
                break;
            case "J":
                if (played.getValor() < 3)
                    return 50;
                break;
            case "Q":
                if (played.getValor() < 2)
                    return 50;
                break;
            default:
                break;
        }

        return 0;
    }
   
    private Carta _pickLowest() {
        Carta Resultado = null;
        Integer worth = 100;
        for (Map.Entry<Carta, Integer> pair : this._worth.entrySet()) {
            if (pair.getValue() < worth) {
                Resultado = pair.getKey();
                worth = pair.getValue();
            } else if (pair.getValue() == worth) {
                if (Resultado.getValor() > pair.getKey().getValor()) {
                    Resultado = pair.getKey();
                    worth = pair.getValue();
                }
            }
        }

        return Resultado;
    }

    
   
   
 
}