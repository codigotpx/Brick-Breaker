package modelo;

import java.awt.*;

public class Barra {
    private int x, y; // Posición de la barra
    private int ancho, alto; // Dimensiones de la barra
    private int anchoPanel; // Ancho del área de juego (para limitar el movimiento)

    public Barra(int x, int y, int ancho, int alto, int anchoPanel) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.anchoPanel = anchoPanel;
    }

    public void mover(int nuevaX) {
        if (nuevaX < 0) {
            x = 0;
        } else if (nuevaX + ancho > anchoPanel) {
            x = anchoPanel - ancho;
        } else {
            x = nuevaX;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
