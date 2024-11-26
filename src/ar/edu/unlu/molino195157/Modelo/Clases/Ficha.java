package ar.edu.unlu.molino195157.Modelo.Clases;

import ar.edu.unlu.molino195157.Modelo.Enums.Bando;

public class Ficha
{
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    private boolean eliminada;
    private Bando bando;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Ficha(Bando bando)
    {
        this.eliminada = false;
        this.bando = bando;
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    public Bando getBando() {
        return bando;
    }

    public boolean isEliminada() {
        return eliminada;
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public Ficha eliminarFicha()
    {
        this.eliminada = true;
        return this;
    }
}