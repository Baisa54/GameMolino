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

    public Controlador(IVista vista) {
        this.vista = vista;
    }

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
            System.out.println(this.miAlias);
            variableLocal = juego.jugadorBlancas(this.miAlias);
            if(variableLocal){
                vista.mostrarMensaje("Se ha entrado correctamente al juego con Blancas");}
            else {vista.mostrarMensaje("no se pudo unir");}
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void unirseAJuegoNegras(){
        boolean variableLocal = false;
        try {
            variableLocal = juego.jugadorNegras(this.miAlias);
            if(variableLocal){
                vista.mostrarMensaje("Se ha entrado correctamente al juego con Negras");}
            else {vista.mostrarMensaje("no se pudo unir");}
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void registrarse(String alias, String contrasena)
    {
        boolean variableLocal = false;
        try {
            variableLocal = juego.registrarNuevoJugador(alias, contrasena);
            if(variableLocal){
                this.miAlias = alias;
                vista.mostrarMensaje("se ha registrado correctamente el usuario " + alias);}
            else {vista.mostrarMensaje("no se pudo registrar correctamente, pruebe cambiando miAlias");}
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void iniciarSesion(String alias, String contrasena)
    {
        boolean variableLocal = false;
        try {
            variableLocal = juego.iniciarSesion(alias, contrasena);
            if(variableLocal){
                this.miAlias = alias;
                vista.mostrarMensaje("se ha iniciado sesion correctamente, usuario " + alias);
            }
            else {
                vista.mostrarMensaje("no se pudo iniciar sesion correctamente, pruebe cambiando miAlias o contrasena");
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void ingresarFicha(String posicion){

        boolean variableLocal = false;
        try {
            variableLocal = juego.ingresarFicha(posicion,this.miAlias);
            if(!variableLocal){
                vista.mostrarMensaje("no se pudo ingresar la ficha en la posicion "+ posicion);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void moverFicha(String posicion1, String posicion2){
        boolean variableLocal = false;
        try {
            variableLocal = juego.moverFicha(posicion1, posicion2, this.miAlias);
            if(!variableLocal){
                vista.mostrarMensaje("no se pudo mover la ficha de "+ posicion1 +" a "+ posicion2);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarFicha(String posicion){
        boolean variableLocal = false;
        try {
            variableLocal = juego.eliminarFicha(posicion, this.miAlias);
            if(!variableLocal){
                vista.mostrarMensaje("no se pudo eliminar la ficha de la posicion "+ posicion);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void top5(){
        try {
            String[] top = juego.listarTop5();
            if (top.length == 0) {
                vista.mostrarMensaje("No hay jugadores con partidas ganadas aún.");
            } else {
                vista.mostrarMensaje("Top 5 jugadores:");
                for (int i = 0; i < top.length; i++) {
                    vista.mostrarMensaje((i + 1) + ". " + top[i]);
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
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
                vista.reiniciarTablero();
                juego.reiniciar();
            }

            default -> {
                vista.mostrarMensaje("Evento no reconocido.");
            }
        }

    }

    public void rendirse() {
        try {
            juego.rendirse(this.miAlias);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}




