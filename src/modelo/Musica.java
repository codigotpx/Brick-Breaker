package modelo;

import javax.sound.sampled.*;
import java.io.*;

public class Musica {
    private Clip clip;  // Clip para manejar el sonido
    private boolean estado = true;

    // Método para reproducir sonido
    public void iniciarMusica(String rutaArchivo) {
        try {
            // Obtener el InputStream del archivo de sonido
            InputStream audioSrc = getClass().getResourceAsStream(rutaArchivo);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            // Crear un nuevo clip y abrir el flujo de audio
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Reproducir el sonido
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Método para detener el sonido
    public void pararMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();  // Detener la música
        }
    }

    // Método para cerrar y liberar recursos del sonido
    public void cerrarSonido() {
        if (clip != null) {
            clip.stop();  // Asegurarse de detener el clip
            clip.flush();  // Limpiar cualquier dato en el buffer de audio
            clip.close();  // Cerrar el clip y liberar los recursos
        }
    }

    public void cambiarEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean getEstadoMusica() {
        return estado;
    }
}
