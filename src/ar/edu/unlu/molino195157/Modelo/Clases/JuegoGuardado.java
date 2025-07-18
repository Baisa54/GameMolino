package ar.edu.unlu.molino195157.Modelo.Clases;

import ar.edu.unlu.molino195157.Modelo.Enums.Fase;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class JuegoGuardado implements Serializable {

    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    //atributos administrativos
    private List<Jugador> jugadores;

    //Atributos "Clase" de Juego
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private Fase fase;
    private String aliasGanador;

    //Atributos Propios
    private int fichasColocadasJugador1;
    private int fichasColocadasJugador2;
    private String turnoDeJugador;
    private boolean eliminarFichas;
    private int fichasEliminadasJugador1;
    private int fichasEliminadasJugador2;

    //Atributos para control de juego
    private String[] ultimoMovimientoPosiciones;
    private String ultimoJugador;
    private String ultimaCasilla;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public JuegoGuardado() throws RemoteException {
        Juego juego = Juego.getInstancia();

        this.jugadores = new ArrayList<>(juego.getJugadores());
        this.jugador1 = juego.getJugador1();
        this.jugador2 = juego.getJugador2();
        this.tablero = juego.getTablero();
        this.fase = juego.getFase();
        this.aliasGanador = juego.getAliasGanador();

        this.fichasColocadasJugador1 = juego.getFichasColocadasJugador1();
        this.fichasColocadasJugador2 = juego.getFichasColocadasJugador2();
        this.turnoDeJugador = juego.getTurnoDeJugador();
        this.eliminarFichas = juego.isEliminarFichas();
        this.fichasEliminadasJugador1 = juego.getFichasEliminadasJugador1();
        this.fichasEliminadasJugador2 = juego.getFichasEliminadasJugador2();

        this.ultimoMovimientoPosiciones = juego.getUltimoMovimientoPosiciones();
        this.ultimoJugador = juego.getUltimoJugador();
        this.ultimaCasilla = juego.getUltimaCasilla();
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public void restaurarEstado() {
        Juego juego = Juego.getInstancia();

        juego.setJugadores(new ArrayList<>(this.jugadores));
        juego.setJugador1(this.jugador1);
        juego.setJugador2(this.jugador2);
        juego.setTablero(this.tablero);
        juego.setFase(this.fase);
        juego.setAliasGanador(this.aliasGanador);
        juego.setFichasColocadasJugador1(this.fichasColocadasJugador1);
        juego.setFichasColocadasJugador2(this.fichasColocadasJugador2);
        juego.setTurnoDeJugador(this.turnoDeJugador);
        juego.setEliminarFichas(this.eliminarFichas);
        juego.setFichasEliminadasJugador1(this.fichasEliminadasJugador1);
        juego.setFichasEliminadasJugador2(this.fichasEliminadasJugador2);
        juego.setUltimoMovimientoPosiciones(this.ultimoMovimientoPosiciones);
        juego.setUltimoJugador(this.ultimoJugador);
        juego.setUltimaCasilla(this.ultimaCasilla);
    }
}
