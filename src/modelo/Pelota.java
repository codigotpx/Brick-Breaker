package modelo;

import java.awt.*;

public class Pelota {
    private double x, y; // Posición de la pelota
    private double velocidadX, velocidadY; // Velocidad de la pelota
    private double radio; // Radio de la pelota
    private double velocidadInicial; // Velocidad constante que siempre tendrá la pelota
    private boolean pelotaActiva;
    private int anchoPanel;
    private int altoPanel;

    public Pelota(double x, double y, double radio, double velocidadX, double velocidadY, int anchoPnel, int altoPanel) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.pelotaActiva = true;
        this.anchoPanel = anchoPnel;
        this.altoPanel = altoPanel;
    }

    public void mover(Barra barra) {
        if(pelotaActiva){
            // Actualizar posición
            x += velocidadX;
            y += velocidadY;
            velocidadInicial = Math.sqrt(velocidadX * velocidadX + velocidadY * velocidadY);

            // Rebote en los bordes horizontales
            if (x - radio < 0 || x + radio > anchoPanel) {
                velocidadX = -velocidadX;
                x = Math.max(radio, Math.min(anchoPanel - radio, x)); // Mantener dentro de los limites
                normalizarVelocidad(); // Mantener velocidad constante
            }

            // Rebote en el suelo y en el techo
            if (y + radio > altoPanel) {
                velocidadY = -velocidadY;
                y = altoPanel - radio;
                normalizarVelocidad(); // Mantener velocidad constante
            } else if (y - radio < 0) {
                velocidadY = -velocidadY;
                y = radio;
                normalizarVelocidad(); // Mantener velocidad constante
            }

            // Colisión con la raqueta tipo "Brick Breaker"
            if (verificarColision(barra)) {
                // Rebote hacia arriba
                velocidadY = -Math.abs(velocidadY); // Rebote vertical, siempre hacia arriba

                // Ajuste en la velocidad horizontal basado en la posición de impacto
                double desplazamientoCentro = (x - (barra.getX() + barra.getAncho() / 2)) / (barra.getAncho() / 2);

                // Ajustar la velocidad horizontal según el punto de impacto
                velocidadX = desplazamientoCentro * 5; // El factor 5 ajusta la sensibilidad del rebote

                normalizarVelocidad(); // Asegurar que la velocidad sea constante
            }
        }
    }

    // posicionar la pelota en el centro del panel
    public void posicionarPelota(){
        x = (double) anchoPanel / 2; //posicion central en el eje x
        y = (double) altoPanel * 0.83; // posicion del 83% sin importar el alto del panel
    }
    // Verificar colision inferior
    public boolean verificarColisionInferior(int altoPanel) {
        return y + radio >= altoPanel;
    }

    // Método para asegurar que la velocidad de la pelota siempre sea constante
    private void normalizarVelocidad() {
        double velocidadActual = Math.sqrt(velocidadX * velocidadX + velocidadY * velocidadY);
        velocidadX = (velocidadX / velocidadActual) * velocidadInicial;
        velocidadY = (velocidadY / velocidadActual) * velocidadInicial;
    }

    public boolean verificarColision(Barra barra) {
        Rectangle barraRect = barra.getBounds();

        // Verificar si la pelota está tocando la raqueta
        double closestX = Math.max(barraRect.x, Math.min(x, barraRect.x + barraRect.width));
        double closestY = Math.max(barraRect.y, Math.min(y, barraRect.y + barraRect.height));

        double distanciaX = x - closestX;
        double distanciaY = y - closestY;

        // Si la distancia es menor que el radio de la pelota, hay colisión.
        return (distanciaX * distanciaX + distanciaY * distanciaY) < (radio * radio);
    }

    public int verificarColisionConBloques(Bloques bloques) {
        int margen = 5; // Margen entre los bloques
        int colision = 0;

        for (int i = 0; i < bloques.getFilas(); i++) {
            for (int j = 0; j < bloques.getColumnas(); j++) {
                Bloque bloque = bloques.getBloque(i, j);

                if (bloque.isEstado()) { // Si el bloque está activo
                    // Obtener las coordenadas y dimensiones del bloque
                    int bloqueX = bloques.calcularPosicionX(j, margen);
                    int bloqueY = bloques.calcularPosicionY(i, margen);
                    int bloqueAncho = bloques.getAnchoBloque(margen);
                    int bloqueAlto = bloques.getAltoBloque(margen);

                    // Verificar si la pelota colisiona con el bloque
                    if (x + radio > bloqueX && x - radio < bloqueX + bloqueAncho &&
                            y + radio > bloqueY && y - radio < bloqueY + bloqueAlto) {

                        // Determinamos qué lado del bloque fue golpeado
                        boolean colisionLateral = x < bloqueX || x > bloqueX + bloqueAncho;
                        boolean colisionSuperiorInferior = y < bloqueY || y > bloqueY + bloqueAlto;

                        if (colisionLateral && !colisionSuperiorInferior) {
                            // Colisión en los lados del bloque (izquierda o derecha)
                            velocidadX = -velocidadX;
                        } else if (colisionSuperiorInferior) {
                            // Colisión en la parte superior o inferior del bloque
                            velocidadY = -velocidadY;
                        }
                        if (bloque.getDurabilidad() == 0) {
                            // Desactivar el bloque tras la colisión
                            bloque.setEstado(false);
                            colision = 1; // Colisión destructiva
                        } else {
                            int durabilidad = bloque.getDurabilidad();
                            bloque.setDurabilidad(durabilidad - 1);
                            colision = 2; // Colisión normal
                        }

                        // Normalizar la velocidad para que no se acelere o desacelere
                        normalizarVelocidad();

                        // Salir después de encontrar una colisión
                        return colision; // Devuelve inmediatamente tras la primera colisión
                    }
                }
            }
        }
        return colision; // Devuelve 0 si no hay colisión
    }

    public void detenerPelota(){
        pelotaActiva = false;
    }

    public void activarPelota(){
        pelotaActiva = true;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadio() {
        return radio;
    }

    public void setVelocidadX(double velocidadX) {
        this.velocidadX = velocidadX;
    }

    public void setVelocidadY(double velocidadY) {
        this.velocidadY = velocidadY;
    }

    public int getVelocidadX() {
        return (int) velocidadX;
    }

    public int getVelocidadY() {
        return (int) velocidadY;
    }
}
