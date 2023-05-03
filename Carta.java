
/**
 * Carta
 */
public class Carta {
    private String _Tipo;
    private String _Nipe;
    private int _Valor;

    public Carta(String c, String _Nipe) {
        this._Tipo = c;
        this._Nipe = _Nipe;
        this._Valor = this._Calcvalor();
    }

    public String toString() {
        return this._Tipo + this._Nipe;
    }

    public String getTipo() {
        return this._Tipo;
    }

    public String getNipe() {
        return this._Nipe;
    }

    public int getValor() {
        return this._Valor;
    }

    
}