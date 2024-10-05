package modelo;

public class Bloques {
    private Bloque bloque;
    private Bloque[][] bloques; // True si el bloque sigue presente
    private int anchoPanel;
    private int altoPanel;

    public Bloques(Bloque bloque, int anchoPanel, int altoPanel) {
        this.bloque = bloque;
        this.anchoPanel = anchoPanel;
        this.altoPanel = altoPanel;
    }

    // Inicialisamos los bloques con los valores por defecto
    public void iniciarBloques(int fila, int columna) {
        bloques = new Bloque[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                bloques[i][j] = new Bloque();
            }
        }
    }

    // Metodos getter
    public Bloque[][] getBloques() {
        return bloques;
    }

    public int getAnchoPanel() {
        return anchoPanel;
    }

    public int getAltoPanel() {
        return altoPanel;
    }

    public int getFilas() {
        return bloques.length;
    }

    public int getColumnas() {
        return bloques[0].length;
    }

    public Bloque isBloquePresente(int fila, int columna) {
        return bloques[fila][columna];
    }
}
