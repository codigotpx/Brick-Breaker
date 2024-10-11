package modelo;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class Musica {
    private Clip clip;
    private boolean estado;

    public Musica() {
        this.estado = true;
    }

    public void iniciarMusica(String url) {
        try {
            // Cargar el archivo de música como un InputStream
            InputStream audioSrc = getClass().getResourceAsStream(url);
            if (audioSrc == null) {
                throw new IOException("El recurso no se pudo encontrar: " + url);
            }

            // Crear el AudioInputStream desde el InputStream
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Reproduce en bucle
            clip.start(); // Inicia la reproducción
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de archivo no soportado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al cargar la música: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Línea no disponible: " + e.getMessage());
        }
    }

    public void pararMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void cambiarEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean getEstadoMusica() {
        return estado;
    }
}
