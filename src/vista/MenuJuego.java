package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MenuJuego extends JPanel {
    private JButton botonIniciar;
    private JButton botonSalir;
    private JButton botonMusica;
    private JLabel tituloIcono;
    private Image fondoMenu;
    private Image sonido;
    private Image sinSonido;
    private ImageIcon iconoSonido; // ImageIcon para el icono de sonido
    private ImageIcon iconoSinSonido; // ImageIcon para el icono de sin sonido

    public MenuJuego() {
        // Cargar la imagen de fondo
        fondoMenu = new ImageIcon(getClass().getResource("/resources/imagenes/Espaciop.jpg")).getImage();

        // Cargar imagen de sonido
        sonido = new ImageIcon(getClass().getResource("/resources/imagenes/sonido.png")).getImage();
        sinSonido = new ImageIcon(getClass().getResource("/resources/imagenes/sin-sonido.png")).getImage();

        // Comprobación de las imágenes
        if (sonido == null) {
            System.out.println("La imagen de sonido no se encontró.");
        }
        if (sinSonido == null) {
            System.out.println("La imagen de sin sonido no se encontró.");
        }

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
        gbc.insets = new Insets(10, 10, 20, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(tituloIcono, gbc);

        // Crear el botón de Iniciar Juego
        botonIniciar = new JButton("Iniciar Juego");
        botonIniciar.setFont(new Font("Arial", Font.PLAIN, 24));
        botonIniciar.setBackground(new Color(9, 39, 83));
        botonIniciar.setForeground(Color.WHITE);
        botonIniciar.setFocusPainted(false);
        botonIniciar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(botonIniciar, gbc);

        // Crear el botón de Salir
        botonSalir = new JButton("Salir");
        botonSalir.setFont(new Font("Arial", Font.PLAIN, 24));
        botonSalir.setBackground(new Color(1, 24, 59));
        botonSalir.setForeground(Color.WHITE);
        botonSalir.setFocusPainted(false);
        botonSalir.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(botonSalir, gbc);

        // Crear el botón de música
        botonMusica = new JButton();
        botonMusica.setPreferredSize(new Dimension(150, 150));
        botonMusica.setFocusPainted(false);
        botonMusica.setBorder(BorderFactory.createEmptyBorder());
        botonMusica.setContentAreaFilled(false);

        // Inicializa el botón con la imagen de sonido escalada
        iconoSonido = new ImageIcon(sonido.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        iconoSinSonido = new ImageIcon(sinSonido.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        actualizarIconoMusica(true); // Inicialmente se muestra el icono de sonido

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(botonMusica, gbc);

        // Personalizar el panel
        setPreferredSize(new Dimension(800, 600));

        // Listener para detectar cuando el botón cambia de tamaño
        botonMusica.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarIconoMusica(true); // Actualiza el icono cuando se redimensiona
            }
        });
    }

    // Método para agregar el ActionListener al botón de Iniciar Juego
    public void IniciarJuegoBoton(ActionListener actionListener) {
        botonIniciar.addActionListener(actionListener);
    }

    // Método para agregar el ActionListener al botón de Salir
    public void SalirBoton(ActionListener actionListener) {
        botonSalir.addActionListener(actionListener);
    }

    // Método para agregar el ActionListener al botón de Música
    public void MusicaBoton(ActionListener actionListener) {
        botonMusica.addActionListener(actionListener);
    }

    // Método para cambiar el icono del botón de música
    public void actualizarIconoMusica(boolean estadoMusica) {
        // Establecer el icono del botón basado en el estado de la música
        botonMusica.setIcon(estadoMusica ? iconoSonido : iconoSinSonido);
    }

    // Sobrescribir el método paintComponent para dibujar el fondo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondoMenu, 0, 0, getWidth(), getHeight(), this);
    }
}
