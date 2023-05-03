import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * Game
 */
public class Game {
    private static Stack<Carta> _Mao;
    public static Carta _Trunfo;
    private Player _p1, _p2, _p3, _p4;
    private static final String[] _Nipes = { "Ouro", "Paus", "Copas", "Espadas" };

    /**
     * @param p1
     * @param p2
     */
    public Game(Player p1, Player p2, Player p3, Player p4) {
        this._DarMao();
        this._p1 = p1;
        this._p2 = p2;
        this._p3 = p3;
        this._p4 = p4;
    }

/**
     * @return
     */
    public Player play() {
        Carta playedFirst, playedSecond, playedThird, playedFourt;
        int Resultado;
        this._chooseStarter();
        this._giveCartas();
        this._Trunfo = _Mao.firstElement();

        System.out.println("----------------------------");
        System.out.println("*   Carta Trunfo é " + this._Trunfo + "   *");
        System.out.println("----------------------------");

        while (this._p1.getHand().size() != 0 && this._p2.getHand().size() != 0
                && this._p3.getHand().size() != 0 && this._p4.getHand().size() != 0) {
            this._printPlay(this._p1);
            playedFirst = this._p1.play();

            this._printPlay(this._p2);
            playedSecond = this._p2.play(playedFirst);

            this._printPlay(this._p3);
            playedThird = this._p3.play(playedSecond);

            this._printPlay(this._p4);
            playedFourt = this._p4.play(playedThird);

            Resultado = this._evaluatePlay(playedFirst, playedSecond, playedThird, playedFourt);
            if (this._p1 instanceof Pc)

            {
                ((Pc) this._p1).seePlayedCarta(playedSecond);
            }
            if (Resultado == 1) {
                this._printRoundvencedor(this._p1, playedFirst, playedSecond, playedThird, playedFourt);
                this._p1.addToPoints(playedFirst, playedSecond, playedThird, playedFourt);
                this._chooseNextStart(this._p1, this._p2, this._p3, this._p4);
            } else {
                this._printRoundvencedor(this._p2, playedSecond, playedFirst, playedFourt, playedThird);
                this._p2.addToPoints(playedFirst, playedSecond, playedThird, playedFourt);
                this._chooseNextStart(this._p2, this._p1, this._p4, this._p4);

                this._p1.draw(_Mao.pop());
                this._p2.draw(_Mao.pop());
                this._p3.draw(_Mao.pop());
                this._p4.draw(_Mao.pop());
    
            }
            if (this._p1.countPoints() > this._p2.countPoints()
                    && this._p3.countPoints() > this._p4.countPoints()) {
                return this._p1;
            }
    
            else if (this._p2.countPoints() > this._p1.countPoints()
                    && this._p3.countPoints() > this._p4.countPoints())
                return this._p2;
    
            else if (this._p3.countPoints() > this._p1.countPoints()
                    && this._p2.countPoints() > this._p4.countPoints())
                return this._p3;
    
            else if (this._p4.countPoints() > this._p1.countPoints()
                    && this._p2.countPoints() > this._p3.countPoints())
                return this._p4;
            return _p4;
    
        }

    private void _chooseNextStart(Player _p12, Player _p22, Player _p32, Player _p42) {
    }

    public static Carta getTrunfo() {
        return _Trunfo;
    }

    public static Stack<Carta> getDeck() {
        return _Mao;
    }

    private void _printPlay(Player p) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("*   Now playing: " + p + "   *");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private void _printRoundvencedor(Player p, Carta c1, Carta c2, Carta c3, Carta c4) {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("    ROUND vencedor: " + p);
        System.out.println("    " + c1 + " > " + c2);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

    }

    private int _evaluatePlay(Carta c1, Carta c2, Carta c3, Carta c4) {
        int Resultado;
        if (c1.getNipe() != c2.getNipe()) {
            if (c1.getNipe().compareTo(this._Trunfo.getNipe()) != 0
                    && c2.getNipe().compareTo(this._Trunfo.getNipe()) != 0) {
                Resultado = 1;
            } else if (c1.getNipe().compareTo(this._Trunfo.getNipe()) == 0) {
                Resultado = 1;
            } else {
                Resultado = -1;
            }
        } else {
            if (c1.getValor() > c2.getValor()) {
                Resultado = 1;
            } else if (c1.getValor() < c2.getValor()) {
                Resultado = -1;
            } else {
                if (c1.getTipo().compareTo(c2.getTipo()) > 0) {
                    Resultado = 1;
                } else {
                    Resultado = -1;
                }
            }
        }
        return Resultado;
    }

    private void _chooseStarter() {
        int coin = new Random().nextInt(2);
        Player temp;
        if (coin != 0) {
            temp = this._p1;
            this._p1 = this._p2;
            this._p2 = temp;
        }
    }

    private void _giveCartas() {
        for (int i = 0; i < 3; i++) {
            this._p1.draw(_Mao.pop());
            this._p2.draw(_Mao.pop());
            this._p3.draw(_Mao.pop());
            this._p4.draw(_Mao.pop());
        }
    }

    private void _DarMao() {
        _Mao = new Stack<Carta>();
        for (String Nipe : _Nipes) {
            for (Integer i = 1; i < 11; i++) {
                if (i != 1 && i <= 7) {
                    _Mao.add(new Carta(i.toString(), Nipe));
                } else {
                    switch (i) {
                        case 1:
                            _Mao.add(new Carta("As", Nipe));
                            break;
                        case 8:
                            _Mao.add(new Carta("J", Nipe));
                            break;
                        case 9:
                            _Mao.add(new Carta("Q", Nipe));
                            break;
                        case 10:
                            _Mao.add(new Carta("K", Nipe));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        Collections.shuffle(_Mao);
    }
}