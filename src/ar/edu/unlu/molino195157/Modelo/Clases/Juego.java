package ar.edu.unlu.molino195157.Modelo.Clases;
import ar.edu.unlu.molino195157.Modelo.Enums.Eventos;
import ar.edu.unlu.molino195157.Modelo.Enums.Fase;
import ar.edu.unlu.molino195157.Modelo.Interfaces.IJuego;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import java.awt.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Juego extends ObservableRemoto implements IJuego, Serializable {
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------
    private static Juego instancia;

    //atributos administrativos
    private List<Jugador> jugadores;

    //Atributos Clase Juego
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private Fase fase;
    private String aliasGanador;

    //Atributos Propios
    private int turnoDeLaPartida;
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
    // getInstancia
    //-------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    private Juego() {
        this.fase = Fase.SIN_COMENZAR;
        this.tablero = new Tablero();
        this.turnoDeLaPartida = 1;
        this.eliminarFichas = false;
        this.fichasEliminadasJugador1 = 0;
        this.fichasEliminadasJugador2 = 0;
        this.jugadores = new ArrayList<>();
        this.fichasColocadasJugador1 = 0;
        this.fichasColocadasJugador2 = 0;
    }

    //-------------------------------------------------------------------------------------
    // Metodo Singleton
    //-------------------------------------------------------------------------------------
    public static Juego getInstancia() {
        if (instancia == null) {
            instancia = new Juego();
        }
        return instancia;
    }

    //-------------------------------------------------------------------------------------
    // instancia para serializar
    //-------------------------------------------------------------------------------------

    public static void setInstancia(Juego nuevaInstancia) {
        instancia = nuevaInstancia;
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    // no tiene

    //-------------------------------------------------------------------------------------
    // Metodos de clase
    //-------------------------------------------------------------------------------------

    public Jugador buscarJugador(String alias){
        for (Jugador jugador : jugadores) {
            if (jugador.getAlias().equalsIgnoreCase(alias)) {
                return jugador;
            }
        }
        return null;
    }

    public void cambiarTurno (String alias) throws RemoteException {
        this.turnoDeLaPartida += 1;
        if (this.jugador1.getAlias().equals(alias))
        {
           this.turnoDeJugador = this.jugador2.getAlias();
            notificarObservadores(Eventos.CAMBIOTURNO);
        }
        else {
            this.turnoDeJugador = this.jugador1.getAlias();
            notificarObservadores(Eventos.CAMBIOTURNO);
        }
    }

    private boolean aliasEsValido(String alias) {
        return (jugador1 != null && jugador1.getAlias().equalsIgnoreCase(alias)) ||
                (jugador2 != null && jugador2.getAlias().equalsIgnoreCase(alias));
    }

    //-------------------------------------------------------------------------------------
    // Metodos Administrativos
    //-------------------------------------------------------------------------------------

    @Override
    public boolean jugadorBlancas(String alias) throws RemoteException {
        if (jugador1 == null) {
            jugador1 = buscarJugador(alias);
            if (jugador1 != null) {
                arrancaJuego();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean jugadorNegras(String alias) throws RemoteException {
        if (jugador2 == null) {
            jugador2 = buscarJugador(alias);
            if (jugador2 != null) {
                arrancaJuego();
                return true;
            }
        }
        return false;
    }

    private void arrancaJuego() throws RemoteException {
        if (jugador1 != null && jugador2 != null) {
            this.fase = Fase.PRIMERA_FASE;
            this.turnoDeJugador = jugador1.getAlias();
            notificarObservadores(Eventos.COMIENZO);
        }
    }

    @Override
    public boolean registrarNuevoJugador(String alias, String contrasena) throws RemoteException {
        for (Jugador jugador : jugadores) {
            if (jugador.getAlias().equalsIgnoreCase(alias)) {
                return false;
            }
        }
        jugadores.add(new Jugador(alias, contrasena));
        return true;
    }

    @Override
    public boolean iniciarSesion(String alias, String contrasena) throws RemoteException {
        for (Jugador jugador : jugadores) {
            if (jugador.iniciarSesion(alias, contrasena)) {
                return true;
            }
        }
        return false;
    }

    //-------------------------------------------------------------------------------------
    // Metodos de Juego
    //-------------------------------------------------------------------------------------

    @Override
    public boolean ingresarFicha(String posicion, String alias) throws RemoteException {
        if (!aliasEsValido(alias)) return false;
        Jugador jugador = buscarJugador(alias);

        if(jugador.getAlias().equals(this.turnoDeJugador) && this.fase == Fase.PRIMERA_FASE)
        {
            boolean SePudoRealizar = this.tablero.ingresarFicha(alias, posicion);
            if (SePudoRealizar)
            {
                if (jugador.getAlias().equals(jugador1.getAlias())) {
                    this.fichasColocadasJugador1++;
                } else if (jugador.getAlias().equals(jugador2.getAlias())) {
                    this.fichasColocadasJugador2++;
                }
                this.ultimaCasilla = posicion;
                this.ultimoJugador = alias;
                notificarObservadores(Eventos.INGRESOFICHA);
                if(!this.tablero.verificarSiHayMolino())
                {
                    this.cambiarTurno(alias);
                }
                else
                {
                    this.eliminarFichas = true;
                    notificarObservadores(Eventos.MOLINO);
                }
                if (this.fichasColocadasJugador1 == 9 && this.fichasColocadasJugador2 == 9) {
                    this.fase = Fase.SEGUNDA_FASE;
                    notificarObservadores(Eventos.SEGUNDAFASE);
                }
            }
            return SePudoRealizar;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean moverFicha(String posicionA, String posicionB, String alias) throws RemoteException {
        if (!aliasEsValido(alias)) return false;
        Jugador jugador = buscarJugador(alias);

        if(jugador.getAlias().equals(this.turnoDeJugador) && this.fase == Fase.SEGUNDA_FASE)
        {
            boolean SePudoRealizar = this.tablero.moverFicha(alias, posicionA, posicionB);
            if (SePudoRealizar)
            {
                this.ultimoMovimientoPosiciones = new String[] {posicionA, posicionB};
                this.ultimoJugador = alias;
                notificarObservadores(Eventos.MUEVEFICHA);
                if(!this.tablero.verificarSiHayMolino())
                {
                    this.cambiarTurno(alias);
                }
                else
                {
                    this.eliminarFichas = true;
                    notificarObservadores(Eventos.MOLINO);
                }
            }
            return SePudoRealizar;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean eliminarFicha(String posicion, String alias) throws RemoteException {
        if (!aliasEsValido(alias)) return false;
        Jugador jugador = buscarJugador(alias);

        if(jugador.getAlias().equals(this.turnoDeJugador) && this.eliminarFichas)
        {
            boolean variableLocal1 = this.tablero.eliminarFichaRival(alias, posicion);
            if (variableLocal1)
            {
                this.eliminarFichas = false;
                this.ultimaCasilla = posicion;
                this.ultimoJugador = alias;
                notificarObservadores(Eventos.FICHAELIMINADA);
                if(Objects.equals(this.jugador1.getAlias(), alias))
                {
                    this.fichasEliminadasJugador1 += 1;
                }
                else if (Objects.equals(this.jugador2.getAlias(), alias))
                {
                    this.fichasEliminadasJugador2 += 1;
                }
                if (this.fichasEliminadasJugador1 >= 7 || this.fichasEliminadasJugador2 >= 7)
                {
                    this.aliasGanador = alias;
                    notificarObservadores(Eventos.GANADOR);
                    this.fase = Fase.FINALIZADO;
                    buscarJugador(alias).sumarVictoria();
                    return true;
                }
                this.cambiarTurno(alias);
            }
            return variableLocal1;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String consultarUltimaCasilla() throws RemoteException {
        return this.ultimaCasilla;
    }

    @Override
    public String consultarUltimoJugador() throws RemoteException {
        return this.ultimoJugador;
    }

    @Override
    public String[] consultarUltimoMovimiento() throws RemoteException {
        return this.ultimoMovimientoPosiciones;
    }

    @Override
    public Color consultarColor(String alias) throws RemoteException{
        if (jugador1 != null && alias.equals(jugador1.getAlias())) {
            return Color.WHITE;
        } else if (jugador2 != null && alias.equals(jugador2.getAlias())) {
            return Color.BLACK;
        }
        return null;
    }

    @Override
    public String consultarGanador() throws RemoteException {return this.aliasGanador;}

    @Override
    public String getTurnoDeJugador() throws RemoteException {
        return this.turnoDeJugador;
    }

    @Override
    public void reiniciar() throws RemoteException {
        // Reiniciar jugadores
        this.jugador1 = null;
        this.jugador2 = null;

        // Reiniciar tablero
        this.tablero = new Tablero();

        // Resetear estado del juego
        this.fase = Fase.SIN_COMENZAR;
        this.aliasGanador = null;
        this.turnoDeLaPartida = 1;
        this.turnoDeJugador = null;
        this.eliminarFichas = false;
        this.fichasEliminadasJugador1 = 0;
        this.fichasEliminadasJugador2 = 0;

        this.ultimoMovimientoPosiciones = null;
        this.ultimoJugador = null;
        this.ultimaCasilla = null;
    }

    @Override
    public String[] listarTop5() throws RemoteException {
        // Crear una copia para no modificar la lista original
        List<Jugador> copia = new ArrayList<>(jugadores);

        // Ordenar de mayor a menor según partidas ganadas
        copia.sort((j1, j2) -> Integer.compare(j2.getPartidasGanadas(), j1.getPartidasGanadas()));

        // Determinar el tamaño del top (máximo 5)
        int limite = Math.min(5, copia.size());
        String[] top5 = new String[limite];

        // Armar el array con los resultados
        for (int i = 0; i < limite; i++) {
            Jugador jugador = copia.get(i);
            top5[i] = jugador.getAlias() + " " + jugador.getPartidasGanadas();
        }

        return top5;
    }

    @Override
    public void rendirse(String alias) throws RemoteException {
        if (jugador1.getAlias().equals(alias))
        {
            this.aliasGanador = jugador2.getAlias();
            jugador2.sumarVictoria();
            notificarObservadores(Eventos.GANADOR);
            this.fase = Fase.FINALIZADO;
        }
        else {
            this.aliasGanador = jugador1.getAlias();
            notificarObservadores(Eventos.GANADOR);
            this.fase = Fase.FINALIZADO;
            jugador1.sumarVictoria();
        }
    }
}


