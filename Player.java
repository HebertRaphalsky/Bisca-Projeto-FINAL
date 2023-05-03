import java.util.ArrayList;

/**
 * Player
 */
public abstract class Player {
    protected ArrayList<Carta> _mao;
    protected ArrayList<Carta> _points;
    protected String name;

    public abstract Carta play();

    public abstract Carta play(Carta played);

    public abstract void draw(Carta Carta);

    public ArrayList<Carta> getHand() {
        return this._mao;
    }
}