package ar.edu.unlu.molino195157.Modelo.Interfaces;

public interface IJuego {

    public boolean ingresarFicha (String posicion, String alias);

    public boolean moverFicha (String posicionA, String posicionB, String alias);

    public boolean eliminarFicha (String posicion, String alias);
}
