package ar.edu.unlu.molino195157.Modelo.Clases;

import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;

import java.io.Serializable;

public class TransformadorDePosiciones implements Serializable {
    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public Posicion stringToPosicion(String posible)
    {
        for (Posicion p : Posicion.values()) {
            if (p.name().equalsIgnoreCase(posible)) {
                return p;
            }
        }
        return Posicion.POSICION_NULA;
    }
}
