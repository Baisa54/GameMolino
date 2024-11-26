package ar.edu.unlu.molino195157.Modelo.Clases;

import ar.edu.unlu.molino195157.Modelo.Enums.Bando;
import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;

import java.util.ArrayList;
import java.util.List;

public class CombinacionDeMolino {

    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    private Casilla p1;
    private Casilla p2;
    private Casilla p3;
    private boolean combinacionMolinoOcupada;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public CombinacionDeMolino(Casilla p1, Casilla p2, Casilla p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.combinacionMolinoOcupada = false;
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    public boolean isCombinacionMolinoOcupada() {
        return this.combinacionMolinoOcupada;
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public boolean isMolino()
    {
        if (p1.isOcupado() && p2.isOcupado() && p3.isOcupado()) {
            if (p1.getFicha().getBando().equals(p2.getFicha().getBando()) && p2.getFicha().getBando().equals(p3.getFicha().getBando())) {
                this.combinacionMolinoOcupada = true;
                return true;
            }
        }
        return false;
    }

    public Bando isMolinoBando()
    {
        return p1.getFicha().getBando();
    }

}