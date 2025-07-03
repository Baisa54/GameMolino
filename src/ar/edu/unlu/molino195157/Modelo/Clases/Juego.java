package ar.edu.unlu.molino195157.Modelo.Clases;
import ar.edu.unlu.molino195157.Modelo.Enums.Fase;
import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;
import ar.edu.unlu.molino195157.Modelo.Interfaces.IJuego;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import java.util.Objects;

public class Juego extends ObservableRemoto implements IJuego {
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    //Atributos Clase
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private Fase fase;

    //Atributos Propios
    private String nombreSala;
    private boolean privada;
    private String contrasena;
    private int turnoDeLaPartida;
    private String turnoDeJugador;
    private boolean eliminarFichas;
    private int fichasEliminadasJugador1;
    private int fichasEliminadasJugador2;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Juego(String nombreSala, String contrasena, boolean privada, Jugador jugador1)
    {
        this.contrasena = contrasena;
        this.fase = Fase.SIN_COMENZAR;
        this.jugador1 = jugador1;
        this.nombreSala = nombreSala;
        this.privada = privada;
        this.tablero = new Tablero();
        this.turnoDeLaPartida = 1;
        this.eliminarFichas = false;
        this.fichasEliminadasJugador1 = 0;
        this.fichasEliminadasJugador2 = 0;

        this.turnoDeJugador = this.jugador1.getAlias();
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    public String getNombreSala() {
        return nombreSala;
    }

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public boolean hayJugadorConAlias(String alias)
    {
        if(this.jugador1.getAlias().equals(alias)){
            return true;
        }
        else if (this.jugador2.getAlias().equals(alias)){
        return true;
        }
        else {
            return false;
        }
    }

    public Jugador buscarJugador(String alias){
        if (this.jugador1.getAlias().equals(alias)){
            return jugador1;
        }
        else{
            return jugador2;
        }
    }
    public void cambiarTurno (String alias){
        this.turnoDeLaPartida += 1;
        if (this.jugador1.getAlias().equals(alias))
        {
           this.turnoDeJugador = this.jugador2.getAlias();
        }
        else {
            this.turnoDeJugador = this.jugador1.getAlias();
        }
    }

    @Override
    public boolean ingresarFicha(String posicion, String alias)
    {
        if (!hayJugadorConAlias(alias)) return false;
        Jugador jugador = buscarJugador(alias);

        if(jugador.getAlias().equals(this.turnoDeJugador) && this.fase == Fase.PRIMERA_FASE)
        {
            boolean SePudoRealizar = this.tablero.ingresarFicha(alias, posicion);
            if (SePudoRealizar)
            {
                if(!this.tablero.verificarSiHayMolino())
                {
                    this.cambiarTurno(alias);
                }
                else
                {
                    this.eliminarFichas = true;
                }
                if(this.turnoDeLaPartida >= 18)
                {
                    this.fase = Fase.SEGUNDA_FASE;
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
    public boolean moverFicha(String posicionA, String posicionB, String alias) {
        if (!hayJugadorConAlias(alias)) return false;
        Jugador jugador = buscarJugador(alias);

        if(jugador.getAlias().equals(this.turnoDeJugador) && this.fase == Fase.SEGUNDA_FASE)
        {
            boolean SePudoRealizar = this.tablero.moverFicha(alias, posicionA, posicionB);
            if (SePudoRealizar)
            {
                if(!this.tablero.verificarSiHayMolino())
                {
                    this.cambiarTurno(alias);
                }
                else
                {
                    this.eliminarFichas = true;
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
    public boolean eliminarFicha(String posicion, String alias) {
        if (!hayJugadorConAlias(alias)) return false;
        Jugador jugador = buscarJugador(alias);

        if(jugador.getAlias().equals(this.turnoDeJugador) && this.eliminarFichas)
        {
            boolean variableLocal1 = this.tablero.eliminarFichaRival(alias, posicion);
            if (variableLocal1)
            {
                this.eliminarFichas = false;
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
                    // Hacer algo con el ganador
                    this.fase = Fase.FINALIZADO;
                }
            }
            return variableLocal1;
        }
        else
        {
            return false;
        }
    }
}

