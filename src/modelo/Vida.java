package modelo;

public class Vida {
    private int Vidas;

    public Vida() {
        this.Vidas = 3;
    }

    public int getVidas() {
        return Vidas;
    }

    public void eliminarVida() {
        this.Vidas--;
    }

}
