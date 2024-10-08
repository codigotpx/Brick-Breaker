package controlador;

import modelo.*;
import vista.PanelJuego;

import javax.swing.*;
import java.awt.event.*;

public class ControladorJuego {
    private Pelota pelota;
    private Barra barra;
    private Bloque bloque;
    private Bloques bloques;
    private PanelJuego panelJuego;
    private Nivel nivel;
    private Timer temporizador;
    private boolean enMovimiento;  // Variable para verificar si el juego ya está en movimiento
    private Vida vida;

    public ControladorJuego(Pelota pelota, Barra barra, PanelJuego panelJuego, Bloque bloque, Bloques bloques, Nivel nivel, Vida vida) {
        this.pelota = pelota;
        this.barra = barra;
        this.panelJuego = panelJuego;
        this.bloque = bloque;
        this.bloques = bloques;
        this.nivel = nivel;
        this.enMovimiento = false;  // Al principio, el juego no está en movimiento
        this.vida = vida;

        temporizador = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pelota.mover( barra);
                panelJuego.actualizarPanel();

                // Manejar colisiones con bloques
                int resultadoColision = pelota.verificarColisionConBloques(bloques);
                if (resultadoColision == 2) {
                    panelJuego.reproducirSonido("resources/sonidos/vidrio-roto.wav"); // Sonido para colisión destructiva
                } else if (resultadoColision == 1) {
                    panelJuego.reproducirSonido("resources/sonidos/breaking-glass.wav"); // Sonido para colisión normal
                }
                if (pelota.verificarColision(barra)) {
                    panelJuego.reproducirSonido("resources/sonidos/golpe-seco.wav");
                }
                if (pelota.verificarColisionInferior(600)) {
                    panelJuego.reproducirSonido("resources/sonidos/sound-game_over.wav");
                    vida.eliminarVida();
                    pelota.posicionarPelota();
                    barra.posicionarBarra();
                    detener();
                }
                if (bloques.todosDestruidos()) {
                    detener();
                    nivel.pintarNiveles();
                    pelota.posicionarPelota();
                    barra.posicionarBarra();
                    pelota.setVelocidadX(pelota.getVelocidadX() + 5);
                    pelota.setVelocidadY(pelota.getVelocidadY() + 5);
                }
            }
        });

        // Agregar el MouseListener para detectar clics en el PanelJuego
        panelJuego.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!enMovimiento && vida.getVidas() > 0) {  // Verificar si el juego está detenido
                    enMovimiento = true;  // Cambiar el estado para indicar que el juego está en movimiento
                    temporizador.start();
                    iniciar();  // Iniciar el temporizador para que la pelota comience a moverse
                }
            }
        });
    }

    public void iniciar() {
        enMovimiento = true;  // Cambiar el estado para indicar que el juego está en movimiento
        temporizador.start();
    }

    public void detener() {
        enMovimiento = false;  // Cambiar el estado para indicar que el juego está detenido
        temporizador.stop();
    }
    public void pausarJuego(){
        pelota.detenerPelota();
        barra.detenerBarra();
    }

    public void reiniciarJuego() {
        vida.setVidas(3);
        bloques.iniciarBloques(10,10);
        nivel.nivel1();
        pelota.posicionarPelota();
        pelota.activarPelota();
        barra.activarBarra();
        barra.setX((int) barra.getAnchoPanel()/2);
    }
}
