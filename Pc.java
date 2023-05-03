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


    private int _evaluateChanceToPass(Carta Carta) {
        double beingEatenChance;
        int eatingCartas = 0, edibleCartas = 0;
        for (Carta CartaIn : this._unknownCartas) {
            if (CartaIn.getNipe().compareTo(Carta.getNipe()) == 0) {
                if (CartaIn.getValor() > Carta.getValor()) {
                    eatingCartas++;
                } else {
                    edibleCartas++;
                }
            } else if (CartaIn.getNipe().compareTo(getTrunfo().getNipe()) == 0) {
                eatingCartas++;
            }
        }

        beingEatenChance = ((eatingCartas - edibleCartas) / (eatingCartas + edibleCartas)) * 100;
        if (beingEatenChance >= 55) {
            return -1;
        }

        return 1;
    }
   
    /**
     * @return
     */
    private Carta getTrunfo() {
        Carta Tru = new Carta("7", "Ouro");

        return Tru;
    }
   
    private int _evaluateBelow(Carta Carta) {
        int nrCartasBelow = 0;
        for (Carta CartaIn : this._unknownCartas) {
            if (CartaIn.getNipe().compareTo(Carta.getNipe()) == 0) {
                switch (Carta.getTipo()) {
                    case "A":
                        if (CartaIn.getTipo().compareTo("7") == 0 || CartaIn.getTipo().compareTo("K") == 0) {
                            nrCartasBelow++;
                        }
                        break;
                    case "K":
                        if (CartaIn.getTipo().compareTo("J") == 0 || CartaIn.getTipo().compareTo("Q") == 0) {
                            nrCartasBelow++;
                        }
                        break;
                    case "7":
                        if (CartaIn.getTipo().compareTo("K") == 0 || CartaIn.getTipo().compareTo("J") == 0) {
                            nrCartasBelow++;
                        }
                        break;
                    default:
                        break;
                }
                if (nrCartasBelow == 2) {
                    return 2;
                }
            }
        }
        return -1;
    }
 
    public void seePlayedCarta(Carta Carta) {
        this._unknownCartas.remove(Carta);
    }
    
    private void _populateUnknownCartas() {
        this._unknownCartas = new ArrayList<Carta>();
        for (String Nipe : _Nipes) {
            for (Integer i = 1; i < 11; i++) {
                if (i != 1 && i <= 7) {
                    this._unknownCartas.add(new Carta(i.toString(), Nipe));
                } else {
                    switch (i) {
                        case 1:
                            this._unknownCartas.add(new Carta("A", Nipe));
                            break;
                        case 8:
                            this._unknownCartas.add(new Carta("J", Nipe));
                            break;
                        case 9:
                            this._unknownCartas.add(new Carta("Q", Nipe));
                            break;
                        case 10:
                            this._unknownCartas.add(new Carta("K", Nipe));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        this._unknownCartas.remove(Game.getTrunfo());
    }
    
    private void _evaluateFirst() {
        this._worth = new HashMap<Carta, Integer>();
        Integer CartaWorth;
        for (Carta Carta : this._mao) {
            CartaWorth = this._giveWorth(Carta);
            CartaWorth += this._evaluateByNipe(Carta);
            if (!this.trumpsExist()) {
                CartaWorth += this._checkEaters(Carta);
            }

            this._worth.put(Carta, CartaWorth);
        }
    }
    
    private int _giveWorth(Carta Carta) {
        int Resultado = 0;
        switch (Carta.getTipo()) {
            case "A":
                Resultado = 9;
                break;
            case "7":
                Resultado = 8;
                break;
            case "K":
                Resultado = 4;
                break;
            case "J":
                Resultado = 2;
                break;
            case "Q":
                Resultado = 1;
                break;
            default:
                Resultado = 0;
                break;
        }

        return Resultado;
    }
    

    private int _evaluateByNipe(Carta Carta) {
        if (Carta.getNipe().compareTo(Game.getTrunfo().getNipe()) == 0) {
            return 3;
        }

        return 0;
    }
    private int _checkEaters(Carta Carta) {
        boolean noEatNoEaten = false, eatNoEaten = false, noEatEaten = false, eatEaten = false;
        for (Carta inCarta : this._unknownCartas) {
            if (inCarta.getNipe().compareTo(Carta.getNipe()) == 0) {
                noEatNoEaten = true;
                if (inCarta.getValor() < Carta.getValor()) {
                    noEatNoEaten = false;
                    eatNoEaten = true;
                }
                if (inCarta.getValor() > Carta.getValor()) {
                    noEatNoEaten = false;
                    noEatEaten = true;
                }
                if (eatNoEaten && noEatEaten) {
                    eatEaten = true;
                }
            }
        }
        if (noEatNoEaten) {
            return -10;
        } else if (eatEaten) {
            return 7;
        } else if (noEatEaten) {
            return 6;
        } else if (eatNoEaten) {
            return 1;
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

    private boolean trumpsExist() {
        for (Carta Carta : this._unknownCartas) {
            if (Carta.getNipe().compareTo(Game.getTrunfo().getNipe()) == 0) {
                return true;
            }
        }

        return false;
    }
   
    
   
   
 

}