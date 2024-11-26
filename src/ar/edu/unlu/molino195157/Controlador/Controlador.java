package ar.edu.unlu.molino195157.Controlador;

import ar.edu.unlu.molino195157.Modelo.Clases.Juego;
import ar.edu.unlu.molino195157.Modelo.Enums.Eventos;
import ar.edu.unlu.molino195157.Observer.Observado;
import ar.edu.unlu.molino195157.Observer.Observador;
import ar.edu.unlu.molino195157.Vista.Vista;

public class Controlador implements Observador{

    private Juego juego; // conexion con modelo
    private Vista vista;

    public Controlador(String jugador1, String jugador2) {
        this.juego = new Juego(jugador1, jugador2);
    }

    @Override
    public void notificar(Eventos evento) {
        switch(evento)
        {
            case COMIENZO -> {

            }
            case INGRESOFICHAJUGADOR1 -> {
            }
            case JUGADOR1MOLINO -> {
            }
            case INGRESOFICHAJUGADOR2 -> {
            }
            case JUGADOR2MOLINO -> {
            }
            case CANTIDADFICHASRESTANTES -> {
            }
            case EMPIEZASEGUNDAFASE -> {
            }
            case MUEVEJUGADOR1SELECCION -> {
            }
            case MUEVEJUGADOR2SELECCION -> {
            }
            case MUEVEJUGADOR1CASILLA -> {
            }
            case MUEVEJUGADOR2CASILLA -> {
            }
            case GANADORJUGADOR2 -> {
            }
            case GANADORJUGADOR1 -> {
            }
        }
    }
}


