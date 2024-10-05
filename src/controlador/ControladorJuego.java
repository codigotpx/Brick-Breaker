package controlador;

import modelo.*;
import vista.PanelJuego;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorJuego {
    private Pelota pelota;
    private Barra barra;
    private Bloque bloque;
    private Bloques bloques;
    private PanelJuego panelJuego;
    private Nivel nivel;
    private Timer temporizador;

    public ControladorJuego(Pelota pelota, Barra barra, PanelJuego panelJuego, Bloque bloque, Bloques bloques, Nivel nivel) {
        this.pelota = pelota;
        this.barra = barra;
        this.panelJuego = panelJuego;
        this.bloque = bloque;
        this.bloques = bloques;
        this.nivel = nivel;

        temporizador = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pelota.mover(800, 600, barra);
                panelJuego.actualizarPanel();
                if (pelota.verificarColisionConBloques(bloques)) {
                    // Darle sonido
                }
                if (pelota.verificarColision(barra)) {
                    // Darle sonido
                }
                if (pelota.verificarColisionInferior(600)) {
                    detener();
                }
            }
        });
    }

    public void iniciar() {
        temporizador.start();
    }

    public void detener() {
        temporizador.stop();
    }
}
