package modelo;

import java.awt.*;

public class Barra {
    private int x, y; // Posición de la barra
    private int ancho, alto; // Dimensiones de la barra
    private int anchoPanel; // Ancho del área de juego (para limitar el movimiento)
    private int altoPanel;
    private boolean barraActiva;

    public Barra(int x, int y, int ancho, int alto, int anchoPanel, int altoPanel) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.anchoPanel = anchoPanel;
        this.barraActiva = true;
        this.altoPanel = altoPanel;
    }

    public void mover(int nuevaX) {
        if(barraActiva){
            if (nuevaX < 0) {
                x = 0;
            } else if (nuevaX + ancho > anchoPanel) {
                x = anchoPanel - ancho;
            } else {
                x = nuevaX;
            }
        }
    }

    public void posicionarBarra() {
        x = (anchoPanel/2) - 45;
        y = (int) (altoPanel * 0.9);
    }
    public void detenerBarra() {
        barraActiva = false;
    }

    public void activarBarra() {
        barraActiva = true;
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

    public void setX(int x) {
        this.x = x;
    }

    public int getAnchoPanel() {
        return anchoPanel;
    }

    public void disminuirAnchoBarra(int disminucion) {
        ancho -= disminucion;
    }
    public void disminuirAltoBarra(int barra) {
        alto -= barra;
    }

    public void reiniciarBarra() {
        ancho = 115;
        alto = 12;
    }
}
