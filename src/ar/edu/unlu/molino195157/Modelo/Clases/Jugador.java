package ar.edu.unlu.molino195157.Modelo.Clases;

public class Jugador {
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

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


    public String getAlias() {
        return alias;
    }

    public String getContrasena() {
        return contrasena;
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public boolean iniciarSesion(String alias, String contrasena)
    {
        return this.alias.equals(alias) && this.contrasena.equals(contrasena);
    }
}
