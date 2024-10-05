package modelo;

public class Bloque {
    private int durabilidad;
    private boolean estado;


    public Bloque() {
        this.durabilidad = 0;
        this.estado = false; // True para que se muestre en pantalla
    }

    // Metodos para interactuar con el bloque
    public int getDurabilidad() {
        return durabilidad;
    }

    public void setDurabilidad(int durabilidad) {
        this.durabilidad = durabilidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
