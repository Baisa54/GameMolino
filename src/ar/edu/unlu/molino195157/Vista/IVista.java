package ar.edu.unlu.molino195157.Vista;

import ar.edu.unlu.molino195157.Controlador.Controlador;

import java.awt.*;
import java.util.List;

public interface IVista {

    void setControlador(Controlador controlador);

    void iniciar();

    // Nuevos métodos para actualizaciones visuales:
    public void mostrarFichaIngresada(String posicion, Color color);
    public void mostrarFichaMovida(String desde, String hasta, Color color);
    public void mostrarFichaEliminada(String posicion);
    public void mostrarMensaje(String mensaje);
}
