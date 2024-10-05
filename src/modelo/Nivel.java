package modelo;

public class Nivel {
    private int nivel;
    private Bloques bloques;

    public Nivel(Bloques bloques) {
        this.nivel = 0;
        this.bloques = bloques;
    }

    public void nivel1() {
        for (int i = 0; i < bloques.getFilas(); i++) {
            for (int j = 0; j < bloques.getColumnas(); j++) {
                Bloque bloque = bloques.getBloque(i, j);
                if (i == j || i + j == bloques.getColumnas() - 1) {
                    bloque.setEstado(false);
                } else {
                    bloque.setEstado(true);
                }
            }
        }
    }

    public void nivel2() {
        // Paso 1: Desactivamos todos los bloques
        for (int i = 0; i < bloques.getFilas(); i++) {
            for (int j = 0; j < bloques.getColumnas(); j++) {
                Bloque bloque = bloques.getBloque(i, j);
                bloque.setEstado(false); // Desactiva todos los bloques inicialmente
            }
        }

        // Paso 2: Activamos los bloques en un patrón de pirámide invertida
        for (int i = 0; i < bloques.getFilas(); i++) {
            // Al principio activamos todos, luego reducimos el rango de bloques activos
            int inicio = i;  // Aumentamos el inicio conforme bajamos
            int fim = (bloques.getColumnas() - 1) - i; // Reducimos el final conforme bajamos

            // Activamos los bloques en el rango permitido para formar la pirámide invertida
            for (int j = inicio; j <= fim; j++) {
                Bloque bloque = bloques.getBloque(i, j);
                bloque.setEstado(true); // Activa el bloque en la pirámide invertida
            }
        }
    }

    public void nivel3() {
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
            }
        }
    }
}
