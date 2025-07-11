package ar.edu.unlu.molino195157.Modelo.Clases;

import java.io.Serializable;

public class Jugador implements Serializable {
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    // atributos propios
    private String alias;
    private String contrasena;
    private int partidasGanadas;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Jugador(String alias, String contrasena) {
        this.alias = alias;
        this.contrasena = contrasena;
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    public String getAlias() {
        return alias;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public boolean iniciarSesion(String alias, String contrasena)
    {
        return this.alias.equals(alias) && this.contrasena.equals(contrasena);
    }

    public void sumarVictoria(){this.partidasGanadas += 1;}
}
