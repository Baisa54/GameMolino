package ar.edu.unlu.molino195157.Observer;

import ar.edu.unlu.molino195157.Modelo.Enums.Eventos;

public interface Observador {
    void notificar(Eventos evento);
}
