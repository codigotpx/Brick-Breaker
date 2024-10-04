package vista;

import modelo.Barra;
import modelo.Pelota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PanelJuego extends JPanel {
    private Pelota pelota;
    private Barra barra;

    public PanelJuego(Pelota pelota, Barra barra) {
        this.pelota = pelota;
        this.barra = barra;


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

        g.setColor(Color.RED);
        g.fillOval((int) (pelota.getX() - pelota.getRadio()), (int) (pelota.getY() - pelota.getRadio()),
                (int) (pelota.getRadio() * 2), (int) (pelota.getRadio() * 2));

        g.setColor(Color.BLUE);
        g.fillRect(barra.getX(), barra.getY(), barra.getAncho(), barra.getAlto());
    }

    public void actualizarPanel() {
        repaint();
    }
}

