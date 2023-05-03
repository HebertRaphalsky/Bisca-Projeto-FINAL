
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

    private int _Calcvalor() {
        int Resultado;
        switch (this._Tipo) {
            case "A":
                Resultado = 11;
                break;
            case "K":
                Resultado = 4;
                break;
            case "Q":
                Resultado = 2;
                break;
            case "J":
                Resultado = 3;
                break;
            case "7":
                Resultado = 10;
                break;
            default:
                Resultado = 0;
                break;
        }

        return Resultado;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Carta) {
            return this.toString().equals(((Carta) obj).toString());
        }

        return false;
    }
}