package ar.edu.unlu.molino195157.Modelo.Administradores;

import ar.edu.unlu.molino195157.Modelo.Clases.Juego;
import ar.edu.unlu.molino195157.Modelo.Clases.Jugador;
import ar.edu.unlu.molino195157.Modelo.Interfaces.IAdministrador;

import java.util.ArrayList;
import java.util.List;

public class Administrador implements IAdministrador {

    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    private List<Jugador> jugadores;
    private List<Juego> juegos;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Administrador()
    {
        this.jugadores = new ArrayList<>();
        this.juegos = new ArrayList<>();
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    @Override
    public boolean crearJuego(String nombreSala, boolean privada, String contrasena, String alias) {
        for (Juego juego : juegos) {
            if (juego.getNombreSala().equalsIgnoreCase(nombreSala)) {
                return false;
            }
        }
        Jugador jugador = buscarJugador(alias);
        juegos.add(new Juego(nombreSala, contrasena, privada, jugador));
        return true;
    }

    @Override
    public List<String> listarJuegos()
    {
        List<String> nombres = new ArrayList<>();
        for (Juego juego : juegos) {
            nombres.add(juego.getNombreSala());
        }
        return nombres;
    }


    @Override
    public boolean unirseAJuego(String nombreSala, String contrasena) {
        for (Juego juego : juegos) {
            if (juego.getNombreSala().equalsIgnoreCase(nombreSala)) {
                if (juego.isPrivada() && !juego.getContrasena().equals(contrasena)) {
                    return false;
                }
                return !juego.hayJugadorConAlias(juego.getJugador2().getAlias());
            }
        }
        return false;
    }

    @Override
    public Juego anadirJugadorAJuego(String nombreSala, String alias) {
        for (Juego juego : juegos) {
            if (juego.getNombreSala().equalsIgnoreCase(nombreSala)) {
                Jugador jugador = buscarJugador(alias);
                juego.anadirJugador(jugador);
                return juego;
            }
        }
        return null;
    }

    @Override
    public boolean registrarNuevoJugador(String alias, String contrasena) {
        for (Jugador jugador : jugadores) {
            if (jugador.getAlias().equalsIgnoreCase(alias)) {
                return false;
            }
        }
        jugadores.add(new Jugador(alias, contrasena));
        return true;
    }

    @Override
    public boolean iniciarSesion(String alias, String contrasena) {
        for (Jugador jugador : jugadores) {
            if (jugador.iniciarSesion(alias, contrasena)) {
                return true;
            }
        }
        return false;
    }

    public Jugador buscarJugador(String alias){
        for (Jugador jugador : jugadores) {
            if (jugador.getAlias().equalsIgnoreCase(alias)) {
                return jugador;
            }
        }
        return null;
    }
}
