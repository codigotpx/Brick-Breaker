package vista;

import modelo.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PanelJuego extends JPanel {
    private Pelota pelota;
    private Barra barra;
    private Bloques bloques;
    private Image imagenPelota;
    private Image imagenBarra;
    private Image imagenBloqueVivo;
    private Image imagenBloqueMuerto;
    private Image imagenVida;
    private Image imagenPerder;
    private Image imagenGanar;
    private Image fondo;
    private JLabel puntuacion;
    private Vida vida;
    private Nivel nivel;

    public PanelJuego(Pelota pelota, Barra barra, Bloques bloques, Vida vida, Nivel nivel) {
        this.pelota = pelota;
        this.barra = barra;
        this.bloques = bloques;
        this.vida = vida;
        this.nivel = nivel;

        // Cargar la imagenes del juego
        imagenPelota = cargarImagen("/resources/imagenes/bolap.png");
        imagenBarra = cargarImagen("/resources/imagenes/NaveRaqueta.png");
        imagenBloqueVivo = cargarImagen("/resources/imagenes/CristalNuevo.png");
        imagenBloqueMuerto = cargarImagen("/resources/imagenes/CristalRoto.png");
        imagenVida = cargarImagen("/resources/imagenes/vida.png");
        imagenPerder = cargarImagen("/resources/imagenes/Game-Over.png");
        imagenGanar = cargarImagen("/resources/imagenes/You-win.png");

        // Configurar el JLabel de puntuación
        puntuacion = new JLabel("Puntuación: " + nivel.getPuntuacion());
        puntuacion.setBounds(600, 600, 200, 10);
        puntuacion.setFont(new Font("Arial", Font.BOLD, 14));
        puntuacion.setForeground(java.awt.Color.WHITE);
        puntuacion.setOpaque(false);
        this.setLayout(null); // Asegura que puedes usar `setBounds`
        this.add(puntuacion); // Añade el JLabel una sola vez

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                barra.mover(e.getX());
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }
        });

        setPreferredSize(new Dimension(800, 600));
    }

    private Image cargarImagen(String ruta) {
        Image imagen = null;
        try {
            imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
        } catch (Exception e) {
            System.out.println("Error en la imagen de la imagen: " + ruta);
            e.printStackTrace();
        }
        return imagen;
    }

    public void setFondo(String ruta) {
        try {
            // Cargar la imagen del fondo
            URL url = getClass().getClassLoader().getResource(ruta);
            if (url != null) {
                fondo = ImageIO.read(url);
                repaint();
            } else {
                System.out.println("No se encontró la ruta: " + ruta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reproducirSonido(String rutaArchivo) {
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(rutaArchivo);
            if (url == null) {
                System.out.println("No se encontró el archivo " + rutaArchivo);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();

            audioStream.close();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Actualizar la puntuación
        puntuacion.setText("Puntuación: " + nivel.getPuntuacion());

        // Dibujar la imagen de fondo
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }

        // Dibujar la pelota
        int anchoPelota = (int) (pelota.getRadio() * 2);
        int altoPelota = (int) (pelota.getRadio() * 2);
        if (imagenPelota != null) {
            g.drawImage(imagenPelota,
                    (int) (pelota.getX() - pelota.getRadio()),
                    (int) (pelota.getY() - pelota.getRadio()),
                    anchoPelota,
                    altoPelota,
                    this);
        }

        // Dibujar la barra
        if (imagenBarra != null) {
            g.drawImage(imagenBarra,
                    barra.getX(),
                    barra.getY() - 40,
                    barra.getAncho(),
                    barra.getAlto() + 70,
                    this);
        }

        // Dibujar los bloques
        int margen = 5;
        for (int i = 0; i < bloques.getFilas(); i++) {
            for (int j = 0; j < bloques.getColumnas(); j++) {
                Bloque bloque = bloques.getBloque(i, j);
                if (bloque.isEstado()) {
                    int x = bloques.calcularPosicionX(j, margen) - 20;
                    int y = bloques.calcularPosicionY(i, margen) - 30;
                    int ancho = bloques.getAnchoBloque(margen) + 40;
                    int alto = bloques.getAltoBloque(margen) + 100;

                    Image imagenBloque = bloque.getDurabilidad() == 1 ? imagenBloqueVivo : imagenBloqueMuerto;
                    g.drawImage(imagenBloque, x, y, ancho, alto, this);
                }
            }
        }

        // Dibujar las vidas
        if (imagenVida != null) {
            int x = 0;
            for (int i = 0; i < vida.getVidas(); i++) {
                g.drawImage(imagenVida, x, 550, 110, 100, this);
                x += 20;
            }
        }

        // Dibujar "Game Over"
        if (imagenPerder != null && vida.getVidas() == 0) {
            g.drawImage(imagenPerder, 30, 5, 700, 700, this);
        }

        // Dibujar "You Win"
        if (imagenGanar != null && nivel.ganar()) {
            g.drawImage(imagenGanar, 30, 5, 700, 700, this);
        }
    }

    public void actualizarPanel() {
        repaint();
    }
}
