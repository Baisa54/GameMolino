package ar.edu.unlu.molino195157.Controlador;

import ar.edu.unlu.molino195157.Modelo.Clases.Juego;
import ar.edu.unlu.molino195157.Modelo.Enums.Bando;
import ar.edu.unlu.molino195157.Modelo.Enums.Eventos;
import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;
import ar.edu.unlu.molino195157.Observer.Observador;
import ar.edu.unlu.molino195157.Vista.TableroVista;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

public class Controlador implements Observador{

    private Juego juego; // conexion con modelo
    private TableroVista vista;
    private Posicion posicionSeleccionada;


    public Controlador(String jugador1, String jugador2) {
        this.juego = new Juego(jugador1, jugador2, this);
    }

    public void setVista(TableroVista vista) {
        this.vista = vista;
    }

    @Override
    public void notificar(Eventos evento) {
        switch(evento)
        {
            case COMIENZO -> {
                vista.mostrarMensaje("Comienzo del juego");
            }
            case INGRESOFICHAJUGADOR1 -> {
                vista.mostrarMensaje("Jugador " + juego.getJugador1().getNombre() +" ingrese la posicion de la ficha");

                vista.iniciarAccionDelJugador();
                if(juego.ingresoFichaJugador1(this.posicionSeleccionada) && posicionSeleccionada != null)
                {
                    vista.actualizarCasilla(this.posicionSeleccionada, juego.getJugador1().getBando());
                    vista.deshabilitarBotones();
                }
                else
                {
                    vista.mostrarMensaje("Por favor, ingrese una posicion valida");
                    notificar(Eventos.INGRESOFICHAJUGADOR1);
                }
            }
            case JUGADOR1MOLINO -> {
                vista.mostrarMensaje("Jugador " + juego.getJugador1().getNombre() +" ingrese la ficha rival a eliminar");

                vista.iniciarAccionDelJugador();
                if(juego.jugador1EliminaFichaJugador2(posicionSeleccionada))
                {
                    vista.actualizarCasilla(this.posicionSeleccionada, Bando.NINGUNO);
                    vista.deshabilitarBotones();
                }
                else
                {
                    vista.mostrarMensaje("Por favor, ingrese una posicion valida");
                    notificar(Eventos.JUGADOR1MOLINO);
                }
            }
            case INGRESOFICHAJUGADOR2 -> {
                vista.mostrarMensaje("Jugador " + juego.getJugador2().getNombre() +" ingrese la posicion de la ficha");

                vista.iniciarAccionDelJugador();
                if(juego.ingresoFichaJugador1(this.posicionSeleccionada) && posicionSeleccionada != null)
                {
                    System.out.println(posicionSeleccionada.name());
                    vista.actualizarCasilla(this.posicionSeleccionada, juego.getJugador2().getBando());
                    vista.deshabilitarBotones();
                }
                else
                {
                    vista.mostrarMensaje("Por favor, ingrese una posicion valida");
                    notificar(Eventos.INGRESOFICHAJUGADOR2);
                }

            }
            case JUGADOR2MOLINO -> {
                vista.mostrarMensaje("Jugador " + juego.getJugador2().getNombre() +" ingrese la ficha rival a eliminar");

                vista.iniciarAccionDelJugador();
                if(juego.jugador2EliminaFichaJugador1(posicionSeleccionada))
                {
                    vista.actualizarCasilla(this.posicionSeleccionada, Bando.NINGUNO);
                    vista.deshabilitarBotones();
                }
                else
                {
                    vista.mostrarMensaje("Por favor, ingrese una posicion valida");
                    notificar(Eventos.JUGADOR1MOLINO);
                }
            }
            case CANTIDADFICHASRESTANTES -> {
                vista.mostrarMensaje("los jugadores tienen " + juego.getJugador1().getCantidadDeFichas() + " fichas restantes");
            }
            case EMPIEZASEGUNDAFASE -> {
                vista.mostrarMensaje("Empieza la fase de mover fichas, arranca jugador" + juego.getJugador1().getNombre());
            }
            case MUEVEJUGADOR1SELECCION -> {
                vista.mostrarMensaje("Jugador " + juego.getJugador1().getNombre() +" Seleccione la ficha a mover");

                vista.iniciarAccionDelJugador();
                if(juego.jugador1VerificarPosicion(this.posicionSeleccionada))
                {
                    vista.deshabilitarBotones();
                }
                else
                {
                    vista.mostrarMensaje("Por favor, ingrese una posicion valida");
                    notificar(Eventos.MUEVEJUGADOR1SELECCION);
                }
            }
            case MUEVEJUGADOR2SELECCION -> {
                vista.mostrarMensaje("Jugador " + juego.getJugador2().getNombre() +" Seleccione la ficha a mover");

                vista.iniciarAccionDelJugador();
                if(juego.jugador2VerificarPosicion(this.posicionSeleccionada))
                {
                    vista.deshabilitarBotones();
                }
                else
                {
                    vista.mostrarMensaje("Por favor, ingrese una posicion valida");
                    notificar(Eventos.MUEVEJUGADOR2SELECCION);
                }
            }
            case MUEVEJUGADOR1CASILLA -> {
                Posicion posicionAnterior = this.posicionSeleccionada;
                vista.mostrarMensaje("Jugador " + juego.getJugador1().getNombre() +" Seleccione la posicion a mover");

                vista.iniciarAccionDelJugador();
                if(juego.jugador1MueveFicha(posicionAnterior, posicionSeleccionada))
                {
                    vista.actualizarCasilla(posicionAnterior, Bando.NINGUNO);
                    vista.actualizarCasilla(posicionSeleccionada, juego.getJugador1().getBando());
                    vista.deshabilitarBotones();
                }
                else
                {
                    vista.mostrarMensaje("Por favor, ingrese una posicion valida");
                    notificar(Eventos.MUEVEJUGADOR1SELECCION);
                }
            }
            case MUEVEJUGADOR2CASILLA -> {
                Posicion posicionAnterior = this.posicionSeleccionada;
                vista.mostrarMensaje("Jugador " + juego.getJugador2().getNombre() +" Seleccione la posicion a mover");

                vista.iniciarAccionDelJugador();
                if(juego.jugador2MueveFicha(posicionAnterior, posicionSeleccionada))
                {
                    vista.actualizarCasilla(posicionAnterior, Bando.NINGUNO);
                    vista.actualizarCasilla(posicionSeleccionada, juego.getJugador2().getBando());
                    vista.deshabilitarBotones();
                }
                else
                {
                    vista.mostrarMensaje("Por favor, ingrese una posicion valida");
                    notificar(Eventos.MUEVEJUGADOR2SELECCION);
                }
            }
            case GANADORJUGADOR2 -> {
                vista.mostrarMensaje(juego.getJugador2().getNombre() + " Ha ganado la partida");
            }
            case GANADORJUGADOR1 -> {
                vista.mostrarMensaje(juego.getJugador1().getNombre() + " Ha ganado la partida");
            }
        }
    }

    public void seleccionarPosicion(Posicion posicion) {
        this.posicionSeleccionada = posicion; // Almacenar la posición
    }

    public void arranque() {

        juego.InicioJuegoSinVuelo();
    }

}


