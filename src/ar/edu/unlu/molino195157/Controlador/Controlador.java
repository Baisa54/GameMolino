package ar.edu.unlu.molino195157.Controlador;

import ar.edu.unlu.molino195157.Modelo.Enums.Eventos;
import ar.edu.unlu.molino195157.Modelo.Interfaces.IJuego;
import ar.edu.unlu.molino195157.Vista.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.awt.*;
import java.rmi.RemoteException;

public class Controlador implements IControladorRemoto
{
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    // Atributos propios
    private String miAlias;
    private String rivalAlias;
    // interfaz Vista
    IVista vista;

    // interfaz Modelo
    IJuego juego;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    // default

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    public void setVista(IVista vista) {
        this.vista = vista;
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public void unirseAJuegoBlancas(){
        boolean variableLocal = false;
        try {
            variableLocal = juego.jugadorBlancas(this.miAlias);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        if(variableLocal){
            vista.mostrarMensaje("Se ha entrado correctamente al juego con Blancas");}
        else {vista.mostrarMensaje("no se pudo unir");}
    }

    public void unirseAJuegoNegras(){
        boolean variableLocal = false;
        try {
            variableLocal = juego.jugadorNegras(this.miAlias);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        if(variableLocal){
            vista.mostrarMensaje("Se ha entrado correctamente al juego con Negras");}
        else {vista.mostrarMensaje("no se pudo unir");}
    }

    public void registrarse(String alias, String contrasena)
    {
        boolean variableLocal = false;
        try {
            variableLocal = juego.registrarNuevoJugador(alias, contrasena);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        if(variableLocal){
            this.miAlias = alias;
            vista.mostrarMensaje("se ha registrado correctamente el usuario " + alias);}
        else {vista.mostrarMensaje("no se pudo registrar correctamente, pruebe cambiando miAlias");}
    }

    public void iniciarSesion(String alias, String contrasena)
    {
        boolean variableLocal = false;
        try {
            variableLocal = juego.iniciarSesion(alias, contrasena);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        if(variableLocal){
            this.miAlias = alias;
            vista.mostrarMensaje("se ha iniciado sesion correctamente, usuario " + alias);
        }
        else {
            vista.mostrarMensaje("no se pudo iniciar sesion correctamente, pruebe cambiando miAlias o contrasena");
        }
    }

    public void ingresarFicha(String posicion){

        boolean variableLocal = false;
        try {
            variableLocal = juego.ingresarFicha(posicion,this.miAlias);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        if(!variableLocal){
            vista.mostrarMensaje("no se pudo ingresar la ficha en la posicion "+ posicion);
        }
    }

    public void moverFicha(String posicion1, String posicion2){
        boolean variableLocal = false;
        try {
            variableLocal = juego.moverFicha(posicion1, posicion2, this.miAlias);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        if(!variableLocal){
                vista.mostrarMensaje("no se pudo mover la ficha de "+ posicion1 +" a "+ posicion2);
            }
    }

    public void eliminarFicha(String posicion){
        boolean variableLocal = false;
        try {
            variableLocal = juego.eliminarFicha(posicion, this.miAlias);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        if(!variableLocal){
                vista.mostrarMensaje("no se pudo eliminar la ficha de la posicion "+ posicion);
            }
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T t) throws RemoteException {
        this.juego = (IJuego) t;
    }

    @Override
    public void actualizar(IObservableRemoto iObservableRemoto, Object o) throws RemoteException {
        var evento = (Eventos) o;

        switch (evento) {
            case COMIENZO -> {
                vista.mostrarMensaje("¡El juego ha comenzado!");
            }

            case INGRESOFICHA -> {
                String posicion = juego.consultarUltimaCasilla();
                Color color = juego.consultarColor(juego.consultarUltimoJugador());
                vista.mostrarFichaIngresada(posicion, color);
                vista.mostrarMensaje("Ficha colocada en " + posicion);
            }

            case MOLINO -> {
                vista.mostrarMensaje("¡Molino formado! se puede eliminar una ficha del oponente.");
            }

            case FICHAELIMINADA -> {
                String posicion = juego.consultarUltimaCasilla();
                String jugador = juego.consultarUltimoJugador();
                vista.mostrarFichaEliminada(posicion);
                vista.mostrarMensaje(jugador +" ha eliminado una ficha en "+ posicion);
            }

            case CAMBIOTURNO -> {
                String jugador = juego.getTurnoDeJugador();
                vista.mostrarMensaje("Turno de " + jugador);
            }

            case SEGUNDAFASE -> {
                vista.mostrarMensaje("¡Comienza la segunda fase! Ahora solo se pueden mover fichas.");
            }

            case MUEVEFICHA -> {
                String[] posiciones = juego.consultarUltimoMovimiento();
                String desde = posiciones[0];
                String hasta = posiciones[1];
                Color color = juego.consultarColor(juego.consultarUltimoJugador());
                vista.mostrarFichaMovida(desde, hasta, color);
                vista.mostrarMensaje("Ficha movida de " + desde + " a " + hasta);
            }

            case GANADOR -> {
                String aliasGanador = juego.consultarGanador();
                vista.mostrarMensaje("¡El juego ha terminado! Ganador: " + aliasGanador);
            }

            default -> {
                vista.mostrarMensaje("Evento no reconocido.");
            }
        }

    }

}




