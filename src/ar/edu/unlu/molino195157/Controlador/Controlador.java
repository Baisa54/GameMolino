package ar.edu.unlu.molino195157.Controlador;

import ar.edu.unlu.molino195157.Modelo.Interfaces.IJuego;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;

public class Controlador implements IControladorRemoto
{
    private IJuego jugador;
    private Bando bando;

    public Controlador()
    {

    }


    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T t) throws RemoteException {

    }

    @Override
    public void actualizar(IObservableRemoto iObservableRemoto, Object o) throws RemoteException {

    }
}




