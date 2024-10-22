package modelo;

import javax.swing.*;

public class Nivel {
    private int nivel;
    private Bloques bloques;
    private Pelota pelota;
    private int puntuacion;

    public Nivel(Bloques bloques, Pelota pelota) {
        this.nivel = 0;
        this.bloques = bloques;
        this.pelota = pelota;
        this.puntuacion = 0;
    }

    public void pintarNiveles() {
        if (bloques.todosDestruidos() && nivel < 3) {
            nivel++;
            switch (nivel) {
                case 1:
                    nivel1();
                    break;
                case 2:
                    nivel2();

                    break;
                case 3:
                    nivel3();
                    break;
                default:
                    // No hace nada
            }
        }
    }

    public void nivel1() {
        // Paso 1: Desactivamos todos los bloques
        for (int i = 0; i < bloques.getFilas(); i++) {
            for (int j = 0; j < bloques.getColumnas(); j++) {
                Bloque bloque = bloques.getBloque(i, j);
                bloque.setEstado(false); // Desactiva todos los bloques inicialmente
                if (i == 0) {
                    bloque.setDurabilidad(1);
                } else {
                    bloque.setDurabilidad(0);
                }
            }
        }

        for (int i = 0; i < bloques.getFilas(); i++) {
            int inicio = i;
            int fim = (bloques.getColumnas() - 1) - i;
            for (int j = inicio; j <= fim; j++) {
                Bloque bloque = bloques.getBloque(i, j);
                bloque.setEstado(true);
            }
        }
    }

    public void nivel2() {
        for (int i = 0; i < bloques.getFilas(); i++) {
            for (int j = 0; j < bloques.getColumnas(); j++) {
                Bloque bloque = bloques.getBloque(i, j);
                if (j <= i && j < (bloques.getColumnas() - i)) {
                    bloque.setEstado(true);
                    bloques.getBloque(i, bloques.getColumnas() - 1 - j).setEstado(true);
                }
                if (j < bloques.getColumnas() - i) {
                    bloques.getBloque(i ,bloques.getColumnas() - 1 -j).setEstado(true);
                }

                if (i == j || i + j == bloques.getColumnas() - 1 || i == 0) {
                    bloque.setDurabilidad(1);
                } else {
                    bloque.setDurabilidad(0);
                }
            }
        }
    }

    public void nivel3() {
        for (int i = 0; i < bloques.getFilas(); i++) {
            for (int j = 0; j < bloques.getColumnas(); j++) {
                Bloque bloque = bloques.getBloque(i, j);
                if ( (i == 0 || i == bloques.getFilas() - 1) || (j == 0) || (j == bloques.getColumnas() - 1)){
                    bloque.setDurabilidad(1);
                }  else {
                    bloque.setDurabilidad(0);
                }
                if (i == j || i + j == bloques.getColumnas() - 1) {
                    bloque.setEstado(false);
                } else {
                    bloque.setEstado(true);
                }
            }
        }
    }

    public boolean ganar() {
        if (nivel == 3 && bloques.todosDestruidos()) {
            return true;
        }
        return false;
    }

    public void aumentarPuntuación( int incremento) {
        puntuacion += incremento;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void reiciarPuntos() {
        puntuacion = 0;
    }
}
