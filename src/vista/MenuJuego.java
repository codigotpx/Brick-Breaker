package vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuJuego extends JFrame {
    private JButton iniciarJuego;
    private JButton cerrarJuego;

    public MenuJuego() {
        setTitle("Menu Juego");
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        iniciarJuego = new JButton("Jugar");
        iniciarJuego.setBounds(150, 100, 100, 30);
        panel.add(iniciarJuego);

        cerrarJuego = new JButton("Salir");
        cerrarJuego.setBounds(150, 150, 100, 30);
        panel.add(cerrarJuego);
    }

    public void addPlayButtonListener(ActionListener listenForPlayButton) {
        iniciarJuego.addActionListener(listenForPlayButton);
    }

    public void addExitButtonListener(ActionListener listenForExitButton) {
        cerrarJuego.addActionListener(listenForExitButton);
    }
}
