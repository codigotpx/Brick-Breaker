package controlador;

import modelo. *;
import vista.PanelJuego;
import vista.MenuJuego;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipal {
    private JFrame marco;
    private PanelJuego panelJuego;
    private MenuJuego menuJuego;
    private ControladorJuego controladorJuego;

    public ControladorPrincipal(JFrame marco) {
        this.marco = marco;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Definimos dimensiones del juego
        int ancho = 800;
        int alto = 600;

        // Inicializamos elemntos del juego
        Pelota pelota = new Pelota(350, 450, 20, 5, 5, ancho, alto);
        Barra barra = new Barra(350, alto - 50, 100, 10, ancho);
        Bloque bloque = new Bloque();
        Bloques bloques = new Bloques(bloque, ancho, alto);
        bloques.iniciarBloques(10,10);
        Nivel nivel = new Nivel(bloques);
        Vida vida = new Vida();

        // Creamos panel juego
        panelJuego = new PanelJuego(pelota, barra,bloques, vida);
        panelJuego.setPreferredSize(new Dimension(ancho, alto));
        panelJuego.setFondo("resources/imagenes/Espaciop.jpg");

        // Incializamos el controlador del juego
        controladorJuego = new ControladorJuego(pelota, barra, panelJuego, bloque,bloques, nivel, vida);

        // Creamos panel del menú
        menuJuego = new MenuJuego();

        // Darle accion al boton de iniciar el juego
        menuJuego.IniciarJuegoBoton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarJuego(); // Cambiamos de panel cuando se muestre el boton
            }
        });

        // Mostrar el menú al iniciar
        mostrarMenu();

    }

    public void mostrarMenu() {
        marco.setContentPane(menuJuego);
        marco.revalidate();
        marco.repaint();
    }

    public void mostrarJuego() {
        marco.setContentPane(panelJuego);
        marco.revalidate();
        marco.repaint();
        controladorJuego.iniciar();
    }
}
