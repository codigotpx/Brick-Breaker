package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuJuego extends JPanel {
    private JButton botonIniciar;
    private JButton botonSalir;
    private JLabel tituloIcono; // JLabel para usar una imagen de icono
    private Image fondoMenu;

    public MenuJuego() {
        // Cargar la imagen de fondo
        fondoMenu = new ImageIcon(getClass().getResource("/resources/imagenes/Espaciop.jpg")).getImage();

        // Cargar la imagen del icono
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/resources/imagenes/Titulo.png"));
        Image iconoEscalado = iconoOriginal.getImage().getScaledInstance(350, 150, Image.SCALE_SMOOTH);
        tituloIcono = new JLabel(new ImageIcon(iconoEscalado));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Establecer bordes y márgenes
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Establecer restricciones para el icono
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 20, 10); // Agregar márgenes alrededor del icono
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el icono
        add(tituloIcono, gbc); // Agregar el icono al panel

        // Crear el botón de Iniciar Juego
        botonIniciar = new JButton("Iniciar Juego");
        botonIniciar.setFont(new Font("Arial", Font.PLAIN, 24));
        botonIniciar.setBackground(new Color(9, 39, 83)); // Cambiar color de fondo
        botonIniciar.setForeground(Color.WHITE); // Cambiar color de texto
        botonIniciar.setFocusPainted(false);
        botonIniciar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        gbc.gridx = 0;
        gbc.gridy = 1; // Fila 1 para el botón de Iniciar
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre botones
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        add(botonIniciar, gbc);

        // Crear el botón de Salir
        botonSalir = new JButton("Salir");
        botonSalir.setFont(new Font("Arial", Font.PLAIN, 24));
        botonSalir.setBackground(new Color(1, 24, 59)); // Color de fondo del botón
        botonSalir.setForeground(Color.WHITE); // Cambiar color del texto
        botonSalir.setFocusPainted(false);
        botonSalir.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        gbc.gridx = 0;
        gbc.gridy = 2; // Fila 2 para el botón de Salir
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        add(botonSalir, gbc);

        // Personalizar el panel
        setPreferredSize(new Dimension(800, 600)); // Tamaño del panel
    }

    // Método para agregar el ActionListener al botón de Iniciar Juego
    public void IniciarJuegoBoton(ActionListener actionListener) {
        botonIniciar.addActionListener(actionListener);
    }

    // Método para agregar el ActionListener al botón de Salir
    public void SalirBoton(ActionListener actionListener) {
        botonSalir.addActionListener(actionListener);
    }

    // Sobrescribir el método paintComponent para dibujar el fondo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la imagen de fondo ajustada al tamaño del panel
        g.drawImage(fondoMenu, 0, 0, getWidth(), getHeight(), this);
    }
}
