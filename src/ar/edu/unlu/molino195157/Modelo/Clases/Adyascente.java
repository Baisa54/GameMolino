package ar.edu.unlu.molino195157.Modelo.Clases;

import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;

import java.util.ArrayList;
import java.util.List;

public class Adyascente {

    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    private List<Posicion> listaAdyascente;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Adyascente(Posicion p1, Posicion p2) {
        this.listaAdyascente = new ArrayList<>();
        this.listaAdyascente.add(p1);
        this.listaAdyascente.add(p2);
    }

    public Adyascente(Posicion p1, Posicion p2, Posicion p3) {
        this.listaAdyascente = new ArrayList<>();
        this.listaAdyascente.add(p1);
        this.listaAdyascente.add(p2);
        this.listaAdyascente.add(p3);
    }

    public Adyascente(Posicion p1, Posicion p2, Posicion p3, Posicion p4) {
        this.listaAdyascente = new ArrayList<>();
        this.listaAdyascente.add(p1);
        this.listaAdyascente.add(p2);
        this.listaAdyascente.add(p3);
        this.listaAdyascente.add(p4);
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public boolean esPosicionAdyacente(Posicion posicion) {
        return listaAdyascente.contains(posicion);
    }
}
