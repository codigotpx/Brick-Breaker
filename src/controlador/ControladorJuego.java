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

    public ControladorJuego(Pelota pelota, Barra barra, PanelJuego panelJuego, Bloque bloque, Bloques bloques, Nivel nivel) {
        this.pelota = pelota;
        this.barra = barra;
        this.panelJuego = panelJuego;
        this.bloque = bloque;
        this.bloques = bloques;
        this.nivel = nivel;
        this.enMovimiento = false;  // Al principio, el juego no está en movimiento

        temporizador = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pelota.mover(800, 600, barra);
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
                    detener();
                }
            }
        });

        // Agregar el MouseListener para detectar clics en el PanelJuego
        panelJuego.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!enMovimiento) {  // Verificar si el juego está detenido
                    enMovimiento = true;  // Cambiar el estado para indicar que el juego está en movimiento
                    temporizador.start();
                    iniciar();  // Iniciar el temporizador para que la pelota comience a moverse
                }else{
                    temporizador.stop();
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
}
