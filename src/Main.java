import controlador.ControladorJuego;
import modelo.*;
import vista.PanelJuego;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame marco = new JFrame("Brick Breaker");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int ancho = 800;
        int alto = 600;

        Pelota pelota = new Pelota(350, 450, 20, 5, 5, ancho, alto);
        Barra barra = new Barra(350, alto - 50, 100, 10, ancho);
        Bloque bloque = new Bloque();
        Bloques bloques = new Bloques(bloque, ancho, alto);
        bloques.iniciarBloques(10,10);
        Nivel nivel = new Nivel(bloques);
        nivel.nivel3();


        PanelJuego panelJuego = new PanelJuego(pelota, barra, bloques);
        panelJuego.setPreferredSize(new Dimension(ancho, alto));
        panelJuego.setFondo("resources/imagenes/Espaciop.jpg");

        ControladorJuego controlador = new ControladorJuego(pelota, barra, panelJuego,bloque, bloques, nivel);

        marco.add(panelJuego);
        marco.pack();
        marco.setVisible(true);

    }
}