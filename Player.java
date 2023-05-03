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

    public ArrayList<Carta> getPoints() {
        return this._points;
    }

    public void addToPoints(Carta c1, Carta c2, Carta c3, Carta c4) {
        this._points.add(c1);
        this._points.add(c2);
        this._points.add(c3);
        this._points.add(c4);
    }

    public int countPoints() {
        int Resultado = 0;
        for (int i = 0; i < this._points.size(); i++) {
            Resultado += this._points.get(i).getValor();
        }

        return Resultado;
    }

    @Override
    public String toString() {
        return this.name;
    }
}