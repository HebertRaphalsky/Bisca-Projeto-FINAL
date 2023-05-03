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
