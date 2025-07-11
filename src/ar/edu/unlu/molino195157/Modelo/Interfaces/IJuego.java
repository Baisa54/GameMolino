package ar.edu.unlu.molino195157.Modelo.Interfaces;

import ar.edu.unlu.molino195157.Modelo.Clases.Juego;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.awt.*;
import java.rmi.RemoteException;

public interface IJuego extends IObservableRemoto {

    //-------------------------------------------------------------------------------------
    // Administrativos
    //-------------------------------------------------------------------------------------

    public boolean jugadorBlancas(String alias) throws RemoteException;

    public boolean jugadorNegras(String alias) throws RemoteException;

    public boolean registrarNuevoJugador(String alias, String contrasena) throws RemoteException;

    public boolean iniciarSesion(String alias, String contrasena) throws RemoteException;

    //-------------------------------------------------------------------------------------
    // Jugabilidad
    //-------------------------------------------------------------------------------------

    public boolean ingresarFicha (String posicion, String alias) throws RemoteException;

    public boolean moverFicha (String posicionA, String posicionB, String alias) throws RemoteException;

    public boolean eliminarFicha (String posicion, String alias) throws RemoteException;

    //-------------------------------------------------------------------------------------
    // Consultas controlador
    //-------------------------------------------------------------------------------------

    public String consultarUltimaCasilla() throws RemoteException;

    public String consultarUltimoJugador() throws RemoteException;

    public String[] consultarUltimoMovimiento() throws RemoteException;

    Color consultarColor(String alias) throws RemoteException;

    public String consultarGanador() throws RemoteException;

    public String getTurnoDeJugador() throws RemoteException;

    public void reiniciar() throws RemoteException;

    public String[] listarTop5() throws RemoteException;

    public void rendirse (String alias) throws  RemoteException;
}
