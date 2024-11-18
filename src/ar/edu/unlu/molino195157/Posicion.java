package ar.edu.unlu.molino195157;

public class Posicion
{
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    private boolean ocupado;
    private Ficha ficha;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Posicion()
    {
        this.ocupado = false;
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    // No hay

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public boolean isOcupado()
    {
        if (this.ocupado)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void asignarFicha(Ficha ficha)
    {
        this.ficha = ficha;
        this.ocupado = true;
    }

    public void eliminarFicha()
    {
        this.ocupado = false;
        this.ficha = null;
    }
}
