package ar.edu.unlu.molino195157.Modelo.Clases;

import ar.edu.unlu.molino195157.Modelo.Enums.Bando;
import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;

import java.util.ArrayList;
import java.util.List;

public class Casilla
{
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    private boolean ocupado;
    private Bando bando;
    private List<Posicion> listaAdyacente;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Casilla()
    {
        this.ocupado = false;
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    public Bando getBando()
    {
        return bando;
    }

    public void setListaAdyascenteAdyascente(Posicion p1, Posicion p2)
    {
        this.listaAdyacente = new ArrayList<>();
        this.listaAdyacente.add(p1);
        this.listaAdyacente.add(p2);
    }

    public void setListaAdyascenteAdyascente(Posicion p1, Posicion p2, Posicion p3)
    {
        this.listaAdyacente = new ArrayList<>();
        this.listaAdyacente.add(p1);
        this.listaAdyacente.add(p2);
        this.listaAdyacente.add(p3);
    }

    public void setListaAdyascenteAdyascente(Posicion p1, Posicion p2, Posicion p3, Posicion p4)
    {
        this.listaAdyacente = new ArrayList<>();
        this.listaAdyacente.add(p1);
        this.listaAdyacente.add(p2);
        this.listaAdyacente.add(p3);
        this.listaAdyacente.add(p4);
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public boolean isOcupado()
    {
        return this.ocupado;
    }

    public void asignarCasillaConFicha(Bando bando)
    {
        this.bando = bando;
        this.ocupado = true;
    }

    public void eliminarFichaDeCasilla()
    {
        this.ocupado = false;
        this.bando = Bando.NINGUNO;
    }

    public boolean esPosicionAdyacente(Posicion posicion) {
        return listaAdyacente.contains(posicion);
    }
}
