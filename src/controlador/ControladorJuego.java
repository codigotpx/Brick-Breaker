package controlador;

import modelo.Barra;
import modelo.Pelota;
import vista.PanelJuego;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorJuego {
    private Pelota pelota;
    private Barra barra;
    private PanelJuego panelJuego;
    private Timer temporizador;

    public ControladorJuego(Pelota pelota, Barra barra, PanelJuego panelJuego) {
        this.pelota = pelota;
        this.barra = barra;
        this.panelJuego = panelJuego;

        temporizador = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pelota.mover(800, 600, barra);
                panelJuego.actualizarPanel();
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
