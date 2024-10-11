import controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Crear el marco
        JFrame marco = new JFrame("Brick Breaker");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(810, 670);
        marco.setLocationRelativeTo(null);

        // Cargar el ícono
        Image icono = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/resources/imagenes/icon.png"));
        marco.setIconImage(icono);

        // Crear y configurar el controlador principal
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(marco);

        // Hacer visible el marco después de todas las configuraciones
        marco.setVisible(true);
    }
}
