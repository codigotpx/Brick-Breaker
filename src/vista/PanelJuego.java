package vista;

import modelo.Barra;
import modelo.Bloque;
import modelo.Bloques;
import modelo.Pelota;

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

    public PanelJuego(Pelota pelota, Barra barra, Bloques bloques) {
        this.pelota = pelota;
        this.barra = barra;
        this.bloques = bloques;

        // Cargar la imagen de la pelota
        imagenPelota = new ImageIcon(getClass().getResource("/resources/imagenes/bolaFinal.png")).getImage(); // Cargar desde carpeta resources

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calcular el tamaño de la pelota en base a su radio
        int anchoPelota = (int) (pelota.getRadio() * 2);
        int altoPelota = (int) (pelota.getRadio() * 2);

        // Verificar si la imagen se cargó correctamente
        if (imagenPelota == null) {
            System.out.println("Error: Imagen de la pelota no encontrada o no se pudo cargar.");
        } else {
            // Dibujar la imagen de la pelota centrada en su posición actual
            g.drawImage(imagenPelota,
                    (int) (pelota.getX() - pelota.getRadio()), // Coordenada X ajustada usando el radio
                    (int) (pelota.getY() - pelota.getRadio()), // Coordenada Y ajustada usando el radio
                    anchoPelota,
                    altoPelota,
                    this
            );
        }

        // Dibujar la raqueta
        g.setColor(Color.BLUE);
        g.fillRect(barra.getX(), barra.getY(), barra.getAncho(), barra.getAlto());

        // Dibujar los bloques
        g.setColor(Color.GREEN);
        int margen = 5;

        for (int i = 0; i < bloques.getFilas(); i++) {
            for (int j = 0; j < bloques.getColumnas(); j++) {
                Bloque bloque = bloques.getBloque(i, j);
                if (bloque.isEstado()) { // Si el bloque está presente
                    int x = bloques.calcularPosicionX(j, margen);
                    int y = bloques.calcularPosicionY(i, margen);
                    int ancho = bloques.getAnchoBloque(margen);
                    int alto = bloques.getAltoBloque(margen);

                    g.fillRect(x, y, ancho, alto);
                }
            }
        }
    }

    public void reproducirSonido(String rutaArchivo) {
        try {
            // Obtener el recurso desde el classpath
            URL url = Thread.currentThread().getContextClassLoader().getResource(rutaArchivo);
            if (url == null) {
                System.out.println("No se encontró el archivo " + rutaArchivo);
                return;
            }

            // Cargar el archivo de audio
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);

            // Obtener el formato del audio y preparar el clip
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);  // Abrir el flujo de audio en el Clip

            // Reproducir el sonido
            clip.start();

            // Cerrar el flujo de audio para liberar recursos
            audioStream.close();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarPanel() {
        repaint();
    }
}
