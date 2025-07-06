import ar.edu.unlu.molino195157.Controlador.Controlador;
import ar.edu.unlu.molino195157.Vista.VistaConsola;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Necesitás un controlador falso o real según tu implementación
        Controlador controladorMock = new Controlador(); // o algún mock simple

        // Lanza la vista en el hilo de Swing
        SwingUtilities.invokeLater(() -> {
            VistaConsola vista = new VistaConsola(controladorMock);
            vista.inicializarTablero();
        });
    }
}


