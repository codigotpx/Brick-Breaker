package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuJuego extends JPanel {
    private JButton botonIniciar;
    private JButton botonSalir;

    public MenuJuego() {
        botonIniciar = new JButton("Iniciar Juego");
        botonSalir = new JButton("Salir");

        setLayout(new GridLayout(2, 1));
        add(botonIniciar);
        add(botonSalir);
    }

    public void IniciarJuegoBoton(ActionListener listener) {
        botonIniciar.addActionListener(listener);
    }
}