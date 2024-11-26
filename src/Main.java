import ar.edu.unlu.molino195157.Controlador.Controlador;
import ar.edu.unlu.molino195157.Modelo.Enums.Bando;
import ar.edu.unlu.molino195157.Modelo.Enums.Eventos;
import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;
import ar.edu.unlu.molino195157.Vista.TableroVista;

public class Main {
    public static void main(String[] args) {
        Controlador controlador = new Controlador("Alumno", "Profesor");
        TableroVista vista = new TableroVista(controlador);
        controlador.setVista(vista);
        controlador.arranque();

    }
}

