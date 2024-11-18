import ar.edu.unlu.molino195157.Ficha;
import ar.edu.unlu.molino195157.Jugador;
import ar.edu.unlu.molino195157.Posicion;
import ar.edu.unlu.molino195157.Tablero;

public class Main
{
    public static void main(String[] args)
    {
        Jugador jugador = new Jugador();
        Ficha ficha = new Ficha(jugador);
        Tablero tablero = new Tablero();
        tablero.ocuparPosicion("A1", ficha);
        tablero.mostrarPosiciones();
    }
}