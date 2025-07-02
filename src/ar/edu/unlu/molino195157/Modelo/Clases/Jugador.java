package ar.edu.unlu.molino195157.Modelo.Clases;

import ar.edu.unlu.molino195157.Modelo.Enums.Bando;

public class Jugador {
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    // atributos Enum
    private Bando bando;

    // atributos propios
    private String alias;
    private String contrasena;

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

    public Bando getBando() {
        return bando;
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public boolean iniciarSesion(String alias, String contrasena)
    {
        return this.alias.equals(alias) && this.contrasena.equals(contrasena);
    }

    public void asignarBandoNegras()
    {
        this.bando = Bando.NEGRAS;
    }
    public void asignarBandoBlancas()
    {
        this.bando = Bando.BLANCAS;
    }
    public void quitarBando()
    {
        this.bando = Bando.NINGUNO;
    }
}
