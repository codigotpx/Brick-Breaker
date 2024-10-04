import controlador.ControladorJuego;
import modelo.Barra;
import modelo.Pelota;
import vista.PanelJuego;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame marco = new JFrame("Pelota que Rebota con Barra");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int ancho = 800;
        int alto = 600;

        Pelota pelota = new Pelota(100, 100, 20, 5, 5, ancho, alto);
        Barra barra = new Barra(350, alto - 50, 100, 10, ancho);

        PanelJuego panelJuego = new PanelJuego(pelota, barra);
        panelJuego.setPreferredSize(new Dimension(ancho, alto));

        ControladorJuego controlador = new ControladorJuego(pelota, barra, panelJuego);

        marco.add(panelJuego);
        marco.pack();
        marco.setVisible(true);

        controlador.iniciar();
    }
}