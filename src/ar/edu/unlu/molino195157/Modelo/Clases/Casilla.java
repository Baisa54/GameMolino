package ar.edu.unlu.molino195157.Modelo.Clases;

public class Casilla
{
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    private boolean ocupado;
    private Ficha ficha;

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

    public Ficha getFicha() {
        return ficha;
    }

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

    public Ficha eliminarFicha()
    {
        this.ocupado = false;
        Ficha ficha = this.ficha.eliminarFicha();
        this.ficha = null;

        return ficha;
    }

    public void sacarFicha()
    {
        this.ocupado = false;
        this.ficha = null;
    }
}
