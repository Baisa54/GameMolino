package ar.edu.unlu.molino195157.Modelo.Interfaces;

import ar.edu.unlu.molino195157.Modelo.Clases.Juego;

import java.util.List;

public interface IAdministrador {

    public boolean crearJuego(String nombreSala, boolean privada, String contraseña, String alias);

    public List<String> listarJuegos();

    public boolean unirseAJuego(String nombreSala, String contrasena);

    public Juego anadirJugadorAJuego(String nombreSala, String alias);

    public boolean registrarNuevoJugador(String alias, String contrasena);

    public boolean iniciarSesion(String alias, String contrasena);
}
