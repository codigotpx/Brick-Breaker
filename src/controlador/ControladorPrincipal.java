package controlador;

import modelo.*;
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
    private boolean enJuego;
    private Musica musica;
    private boolean estadoMusica; // Controlar el estado de la música

    public ControladorPrincipal(JFrame marco) {
        this.marco = marco;
        this.musica = new Musica();
        this.estadoMusica = true; // Inicialmente, la música no está reproduciéndose
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Definimos dimensiones del juego
        int ancho = 800;
        int alto = 600;

        // Inicializamos elementos del juego
        Pelota pelota = new Pelota(350, 480, 20, 1, 1, ancho, alto);
        Barra barra = new Barra(350, alto - 50, 120, 12, ancho, alto);
        Bloque bloque = new Bloque();
        Bloques bloques = new Bloques(bloque, ancho, alto);
        bloques.iniciarBloques(10, 10);
        Nivel nivel = new Nivel(bloques, pelota);
        Vida vida = new Vida();

        // Iniciamos la musica
        musica.iniciarMusica("/resources/sonidos/musica-fondo.wav");

        // Creamos panel juego
        panelJuego = new PanelJuego(pelota, barra, bloques, vida, nivel);
        panelJuego.setPreferredSize(new Dimension(ancho, alto));
        panelJuego.setFondo("resources/imagenes/Espaciop.jpg");

        // Inicializamos el controlador del juego
        controladorJuego = new ControladorJuego(pelota, barra, panelJuego, bloque, bloques, nivel, vida);

        // Creamos panel del menú
        menuJuego = new MenuJuego();

        // Darle acción al botón de iniciar el juego
        menuJuego.IniciarJuegoBoton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarJuego(); // Cambiamos de panel cuando se muestre el botón
            }
        });

        // Botón de salir
        menuJuego.SalirBoton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Botón de música
        menuJuego.MusicaBoton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Alternar el estado de la música
                if (!estadoMusica) {
                    musica.iniciarMusica("/resources/sonidos/musica-fondo.wav");
                    musica.cambiarEstado(true);
                    menuJuego.actualizarIconoMusica(musica.getEstadoMusica());
                } else {
                    musica.pararMusica();
                    musica.cambiarEstado(false);
                    menuJuego.actualizarIconoMusica(musica.getEstadoMusica());
                }
                estadoMusica = !estadoMusica; // Cambia el estado
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

        new Thread(this::bucleJuego).start();
    }

    private void bucleJuego() {
        enJuego = true;
        while (true) {
            panelJuego.actualizarPanel();

            if (controladorJuego.getVida().getVidas() == 0) {
                enJuego = false;
                controladorJuego.pausarJuego();
                try {
                    Thread.sleep(3000); // Pausar por 3 segundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pausarYMostrarMenu();
                controladorJuego.reiniciarJuego();
                break;
            }

            if (controladorJuego.nivel().ganar()) {
                enJuego = false;
                controladorJuego.pausarJuego();
                try {
                    Thread.sleep(3000); // Pausar por 3 segundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pausarYMostrarMenu();
                controladorJuego.reiniciarJuego();
                break;
            }

            panelJuego.repaint();
        }
    }

    private void pausarYMostrarMenu() {
        // Crear un nuevo hilo para manejar la pausa
        new Thread(() -> {
            try {
                Thread.sleep(0); // Pausar por 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Volver a mostrar el menú después de la pausa
            controladorJuego.reiniciarJuego();
            SwingUtilities.invokeLater(this::mostrarMenu);
        }).start();
    }
}
