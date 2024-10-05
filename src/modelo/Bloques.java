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
    public Bloque getBloque(int fila, int columna) {
        return bloques[fila][columna];
    }

    public int getFilas() {
        return bloques.length;
    }

    public int getColumnas() {
        return bloques[0].length;
    }

    // Métodos para calcular la posición de un bloque en la fila/columna indicada
    public int calcularPosicionX(int columna, int margen) {
        int bloqueAncho = (anchoPanel - margen * (getColumnas() - 1)) / getColumnas();
        return columna * (bloqueAncho + margen);
    }

    public int calcularPosicionY(int fila, int margen) {
        int areaBloquesAlto = altoPanel / 3; // por ejemplo, los bloques ocupan 1/3 del alto del panel
        int bloqueAlto = (areaBloquesAlto - margen * (getFilas() - 1)) / getFilas();
        return fila * (bloqueAlto + margen);
    }

    // Métodos para obtener el tamaño de los bloques
    public int getAnchoBloque(int margen) {
        return (anchoPanel - margen * (getColumnas() - 1)) / getColumnas();
    }

    public int getAltoBloque(int margen) {
        int areaBloquesAlto = altoPanel / 3; // 1/3 del alto total del panel para bloques
        return (areaBloquesAlto - margen * (getFilas() - 1)) / getFilas();
    }
}
