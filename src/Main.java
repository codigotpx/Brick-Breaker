import controlador.ControladorPrincipal;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame marco = new JFrame("Brick Breake");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(810, 670);
        marco.setLocationRelativeTo(null);

        // Crear y configurar el controlador principal
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(marco);

        marco.setVisible(true);
    }
}