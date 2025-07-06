package ar.edu.unlu.molino195157.Controlador;

import ar.edu.unlu.molino195157.Modelo.Interfaces.IAdministrador;
import ar.edu.unlu.molino195157.Modelo.Interfaces.IJuego;
import ar.edu.unlu.molino195157.Vista.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;

public class Controlador implements IControladorRemoto
{
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    // Atributos propios
    private String alias;

    // interfaz Vista
    IVista vista;

    // interfaz Modelo
    IJuego juego;
    IAdministrador administrador;

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

    public void registrarse(String alias, String contrasena)
    {
        boolean variableLocal = administrador.registrarNuevoJugador(alias, contrasena);
        if(variableLocal){
            // hacer algo con vista
        }
    }

    public void iniciarSesion(String alias, String contrasena)
    {
        boolean variableLocal = administrador.iniciarSesion(alias, contrasena);
        if(variableLocal){
            // hacer algo con vista
        }
    }

    public void crearJuego(String nombreSala, String privada, String contrasena)
    {
        boolean variableLocal = administrador.iniciarSesion(nombreSala, contrasena);
        if(variableLocal){
            // hacer algo con vista
        }
    }

    public void listarJuegos(){

    }

    public void unirseAJuego(String nombreSala,String contrasena){

    }

    public void añadirseAJuego(){

    }

    public void ingresarFicha(String posicion,String alias){

    }

    public void moverFicha(String posicion,String alias){

    }

    public void eliminarFicha(String posicion,String alias){

    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T t) throws RemoteException {

    }

    @Override
    public void actualizar(IObservableRemoto iObservableRemoto, Object o) throws RemoteException {

    }
}




