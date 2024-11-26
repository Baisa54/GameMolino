package ar.edu.unlu.molino195157.Modelo.Clases;

import ar.edu.unlu.molino195157.Modelo.Enums.Bando;
import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    private String nombre;
    private Bando bando;
    private List<Ficha> fichas;
    private int cantidadDeFichas;
    private int cantidadDeFichasEliminadas;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Jugador(String nombre, Bando bando) {
        this.nombre = nombre;
        this.bando = bando;
        this.fichas = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            this.fichas.add(new Ficha(bando));
        }
        this.cantidadDeFichas = 9;
        this.cantidadDeFichasEliminadas = 0;
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    public String getNombre() {
        return nombre;
    }

    public int getCantidadDeFichas() {
        return cantidadDeFichas;
    }

    public Bando getBando() {
        return bando;
    }

    public int getCantidadDeFichasEliminadas() {
        return cantidadDeFichasEliminadas;
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public boolean colocarFichaEnTablero(Tablero tablero, Posicion posicion)
    {
        if (!fichas.isEmpty())
        {
            Ficha ficha = fichas.removeFirst();
            if (tablero.ocuparPosicion(posicion, ficha))
            {
                this.cantidadDeFichas--;
                return true;
            }
        }
        return false;
    }

    public boolean ingresarFichaEliminada(Tablero tablero, Posicion posicion)
    {
        if (tablero.verificarFichaDelTablero(posicion).getBando() == this.bando)
        {
            this.fichas.add(tablero.eliminarFichaDelTablero(posicion));
            this.cantidadDeFichasEliminadas += 1;
            return true;
        }
        return false;
    }
}
