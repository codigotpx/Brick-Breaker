package vista;

import modelo.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URL;

public class PanelJuego extends JPanel {
    private Pelota pelota;
    private Barra barra;
    private Bloques bloques;
    private Image imagenPelota;
    private Image imagenBarra; // Declarar la imagen de la raqueta
    private Image imagenBloqueVivo; // Imagen para bloque con resistencia
    private Image imagenBloqueMuerto; // Imagen para bloque sin resistencia
    private Image imagenVida; // Imagen para las vidas
    private Image imagenPerder; // Imagen para el fin del juego
    private Image imagenGanar;
    private Image fondo;
    private Vida vida;
    private Nivel nivel;

    public PanelJuego(Pelota pelota, Barra barra, Bloques bloques, Vida vida, Nivel nivel) {
        this.pelota = pelota;
        this.barra = barra;
        this.bloques = bloques;
        this.vida = vida;
        this.nivel = nivel;

        // Cargar la imagen de la pelota
        imagenPelota = new ImageIcon(getClass().getResource("/resources/imagenes/bolap.png")).getImage();

        // Cargar la imagen de la raqueta
        imagenBarra = new ImageIcon(getClass().getResource("/resources/imagenes/NaveRaqueta.png")).getImage();

        // Cargar imágenes de bloques
        imagenBloqueVivo = new ImageIcon(getClass().getResource("/resources/imagenes/CristalNuevo.png")).getImage();
        imagenBloqueMuerto = new ImageIcon(getClass().getResource("/resources/imagenes/CristalRoto.png")).getImage();

        imagenVida = new ImageIcon(getClass().getResource("/resources/imagenes/vida.png")).getImage();

        // Cargar imagenes de game over
        imagenPerder = new ImageIcon(getClass().getResource("/resources/imagenes/Game-Over.png")).getImage();

        // Cargar imagen de ganar
        imagenGanar = new ImageIcon(getClass().getResource("/resources/imagenes/You-win.png")).getImage();

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                barra.mover(e.getX());
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) { }
        });

        setPreferredSize(new Dimension(800, 600));
    }

    public void setFondo(String ruta) {
        try {
            // Cargar la imagen del fondo
            URL url = getClass().getClassLoader().getResource(ruta);
            if (url != null) {
                fondo = ImageIO.read(url);
                repaint();
            } else {
                System.out.println("No se encontro la ruta: " + ruta);
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

        // Dibujar la imagen de fondo
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null); // Escalar la imagen para llenar el panel
        }

        // Calcular el tamaño de la pelota en base a su radio
        int anchoPelota = (int) (pelota.getRadio() * 2);
        int altoPelota = (int) (pelota.getRadio() * 2);

        // Dibujar la imagen de la pelota
        if (imagenPelota != null) {
            g.drawImage(imagenPelota,
                    (int) (pelota.getX() - pelota.getRadio()),
                    (int) (pelota.getY() - pelota.getRadio()),
                    anchoPelota,
                    altoPelota,
                    this);
        }

        // Dibujar la imagen de la raqueta (barra)
        if (imagenBarra != null) {
            g.drawImage(imagenBarra,
                    barra.getX(),
                    barra.getY() - 40,
                    barra.getAncho(),
                    barra.getAlto() + 70,
                    this);
        }

        // Dibujar los bloques con el tamaño y posición calculados
        int margen = 5; // Margen entre bloques

        for (int i = 0; i < bloques.getFilas(); i++) {
            for (int j = 0; j < bloques.getColumnas(); j++) {
                Bloque bloque = bloques.getBloque(i, j);
                if (bloque.isEstado()) { // Si el bloque está presente
                    // Calcular posición y tamaño basados en la clase Bloques
                    int x = bloques.calcularPosicionX(j, margen) - 20;
                    int y = bloques.calcularPosicionY(i, margen) - 30;
                    int ancho = bloques.getAnchoBloque(margen) + 40; // Ancho exacto del bloque
                    int alto = bloques.getAltoBloque(margen) + 100; // Alto exacto del bloque

                    // Elegir la imagen según el estado del bloque
                    Image imagenBloque = bloque.getDurabilidad() == 1 ? imagenBloqueVivo : imagenBloqueMuerto;

                    // Dibujar la imagen del bloque en la posición y tamaño calculados
                    g.drawImage(imagenBloque, x, y, ancho, alto, this);
                }
            }
        }

        // Dibujamos las vidas
        if (imagenVida != null) {
            int x = 0;
            for (int i = 0; i < vida.getVidas(); i++) {
                g.drawImage(imagenVida,
                        x,
                        550,
                        110,
                        100,
                        this);
                x += 20;
            }
        }

        // Dibujamos el game over
        if (imagenPerder != null) {
            if (vida.getVidas() == 0) {
                g.drawImage(imagenPerder,
                         30,
                        5,
                        700,
                        700,
                        this);
            }
        }

        // Dibujamos el tu ganas
        if (imagenGanar != null) {
            if (nivel.ganar()) {
                g.drawImage(imagenGanar,
                        30,
                        5,
                        700,
                        700,
                        this);
            }
        }

    }
    public void actualizarPanel() {
        repaint();
    }
}
