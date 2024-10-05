package vista;

import modelo.Barra;
import modelo.Bloque;
import modelo.Bloques;
import modelo.Pelota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PanelJuego extends JPanel {
    private Pelota pelota;
    private Barra barra;
    private Bloques bloques;

    public PanelJuego(Pelota pelota, Barra barra, Bloques bloques) {
        this.pelota = pelota;
        this.barra = barra;
        this.bloques = bloques;


        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                barra.mover(e.getX());
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) { }
        });

        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujamos la pelota
        g.setColor(Color.RED);
        g.fillOval((int) (pelota.getX() - pelota.getRadio()), (int) (pelota.getY() - pelota.getRadio()),
                (int) (pelota.getRadio() * 2), (int) (pelota.getRadio() * 2));

        // Dibujamos la raqueta
        g.setColor(Color.BLUE);
        g.fillRect(barra.getX(), barra.getY(), barra.getAncho(), barra.getAlto());

        // Dibujamos los bloques
        g.setColor(Color.GREEN);

        int areaBloquesAlto = bloques.getAnchoPanel() / 5;
        int margen = 5;

        int anchoDisponible = bloques.getAnchoPanel();
        int bloqueAncho = (anchoDisponible - margen * (bloques.getColumnas() - 1)) / bloques.getColumnas();
        int bloqueAlto = areaBloquesAlto / bloques.getFilas();

        int anchoBloquesTotal = (bloqueAncho * bloques.getColumnas()) + (margen * (bloques.getColumnas() - 1));
        int margenLateral = (anchoDisponible - anchoBloquesTotal) / 2;

        for (int i = 0; i < bloques.getFilas(); i++) {
            for (int j = 0; j < bloques.getColumnas(); j++) {
                if (bloques.isBloquePresente(i, j).isEstado()) {
                    int x = margenLateral + j * (bloqueAncho + margen);
                    int y = i * (bloqueAlto + margen);
                    g.fillRect(x, y, bloqueAncho, bloqueAlto);
                }
            }
        }

    }

    public void actualizarPanel() {
        repaint();
    }
}

