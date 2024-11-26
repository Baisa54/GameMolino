package ar.edu.unlu.molino195157.Modelo.Clases;
import ar.edu.unlu.molino195157.Modelo.Enums.Bando;
import ar.edu.unlu.molino195157.Modelo.Enums.Eventos;
import ar.edu.unlu.molino195157.Modelo.Enums.Fase;
import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;
import ar.edu.unlu.molino195157.Observer.Observado;
import ar.edu.unlu.molino195157.Observer.Observador;

import java.util.ArrayList;
import java.util.Scanner;

public class Juego implements Observado
{
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private Posicion posi;
    private Fase fase;
    private ArrayList<Observador> observadores;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Juego(String nombre1, String nombre2)
    {
        this.tablero = new Tablero();
        this.jugador1 = new Jugador(nombre1, Bando.BLANCAS);
        this.jugador2 = new Jugador(nombre2, Bando.NEGRAS);
        this.fase = Fase.PRIMERFASE;
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    @Override
    public void agregarObservador(Observador observador) {
        this.observadores.add(observador);
    }

    @Override
    public void notificarObservador(Eventos evento) {
        for (Observador observador : observadores) {
            observador.notificar(evento);
        }
    }

    public void InicioJuegoSinVuelo()
    {
        this.fase = Fase.PRIMERFASE;

        notificarObservador(Eventos.COMIENZO);

        //---------------------------------------------------------------------------------------------

        while(this.fase == Fase.PRIMERFASE)
        {
            //Jugador 1
            notificarObservador(Eventos.INGRESOFICHAJUGADOR1);


            //Jugador2
            notificarObservador(Eventos.INGRESOFICHAJUGADOR2);


            //Decir cantidad de fichas restantes
            notificarObservador(Eventos.CANTIDADFICHASRESTANTES);

            if (jugador1.getCantidadDeFichas() == 0 && jugador2.getCantidadDeFichas() == 0)
            {
                this.fase = Fase.SEGUNDAFASE;
            }
        }

        notificarObservador(Eventos.EMPIEZASEGUNDAFASE);

        while(this.fase == Fase.SEGUNDAFASE)
        {
            notificarObservador(Eventos.MUEVEJUGADOR1SELECCION);
            notificarObservador(Eventos.MUEVEJUGADOR1CASILLA);

            notificarObservador(Eventos.MUEVEJUGADOR2SELECCION);
            notificarObservador(Eventos.MUEVEJUGADOR2CASILLA);

            if(jugador1.getCantidadDeFichasEliminadas() == 7)
            {
                notificarObservador(Eventos.GANADORJUGADOR2);
            } else if (jugador2.getCantidadDeFichasEliminadas() == 7) {
                notificarObservador(Eventos.GANADORJUGADOR1);
            }
        }
    }

    public boolean ingresoFichaJugador1(Posicion posicion)
    {
        if(jugador1.colocarFichaEnTablero(tablero, posicion))
        {
            return true;
        }
        if(tablero.verificadorDeMolinos() == jugador1.getBando())
        {
            notificarObservador(Eventos.JUGADOR1MOLINO);
        }
        return false;
    }

    public boolean jugador1EliminaFichaJugador2(Posicion posicion)
    {
        return jugador2.ingresarFichaEliminada(tablero, posicion);
    }

    public boolean ingresoFichaJugador2(Posicion posicion)
    {
        if(jugador2.colocarFichaEnTablero(tablero, posicion))
        {
            return true;
        }
        if(tablero.verificadorDeMolinos() == jugador2.getBando())
        {
            notificarObservador(Eventos.JUGADOR2MOLINO);
        }
        return false;
    }

    public boolean jugador2EliminaFichaJugador1(Posicion posicion)
    {
        return jugador1.ingresarFichaEliminada(tablero, posicion);
    }

    public boolean jugador1VerificarPosicion(Posicion posicion) {
        return tablero.verificarFichaDelTablero(posicion).getBando() == jugador1.getBando();
    }

    public boolean jugador1MueveFicha(Posicion posicionOriginal, Posicion posicion) {
        if (tablero.moverFicha(posicionOriginal, posicion))
        {
            if(tablero.verificadorDeMolinos() == jugador1.getBando())
            {
                notificarObservador(Eventos.JUGADOR1MOLINO);
            }
            return true;
        }
        return false;
    }

    public boolean jugador2VerificarPosicion(Posicion posicion) {
        return tablero.verificarFichaDelTablero(posicion).getBando() == jugador2.getBando();
    }

    public boolean jugador2MueveFicha(Posicion posicionOriginal, Posicion posicion) {
        if (tablero.moverFicha(posicionOriginal, posicion))
        {
            if(tablero.verificadorDeMolinos() == jugador2.getBando())
            {
                notificarObservador(Eventos.JUGADOR2MOLINO);
            }
            return true;
        }
        return false;
    }

}
