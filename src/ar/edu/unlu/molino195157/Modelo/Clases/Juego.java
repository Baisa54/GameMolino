package ar.edu.unlu.molino195157.Modelo.Clases;
import ar.edu.unlu.molino195157.Modelo.Enums.Bando;
import ar.edu.unlu.molino195157.Modelo.Enums.Fase;
import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;
import ar.edu.unlu.molino195157.Modelo.Interfaces.IJuego;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

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

    public boolean ingresarFicha (Bando bando, Posicion posicion)
    {
        if(this.turnoDelBando == bando && this.fase == Fase.PRIMERFASE)
        {
            boolean variableLocal = this.tablero.ingresarFicha(bando, posicion);
            if (variableLocal)
            {
                if(!this.tablero.verificarSiHayMolino())
                {
                    this.cambiarTurno();
                }
                else
                {
                    this.permitidoEliminarFicha = true;
                }
                this.turnoDeLaPartida++;
                if(this.turnoDeLaPartida >= 18)
                {
                    this.fase = Fase.SEGUNDAFASE;
                }
            }
            return variableLocal;
        }
        else
        {
            return false;
        }
    }

    public boolean moverFicha (Bando bando, Posicion posicionA, Posicion posicionB)
    {
        if(this.turnoDelBando == bando && this.fase == Fase.PRIMERFASE)
        {
            return this.tablero.moverFicha(bando, posicionA, posicionB);
        }
        else
        {
            return false;
        }
    }

    public boolean eliminarFichaRival (Bando bando, Posicion posicion)
    {
        if(this.turnoDelBando == bando && this.permitidoEliminarFicha)
        {
            boolean variableLocal1 = this.tablero.eliminarFichaRival(bando, posicion);
            if (variableLocal1)
            {
                this.permitidoEliminarFicha = false;
                if(bando == Bando.BLANCAS)
                {
                    this.fichasQueEliminaronLasBlancas++;
                }
                else if (bando == Bando.NEGRAS)
                {
                    this.fichasQueEliminaronLasNegras++;
                }
                if (fichasQueEliminaronLasNegras >= 7 || fichasQueEliminaronLasBlancas >= 7)
                {
                    // Hacer algo con el ganador
                    this.fase = Fase.GANADOR;
                }
            }
            return variableLocal1;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean ingresarFicha(String posicionA, String alias)
    {
        Bando jugador =
        if(jugador == bando && this.fase == Fase.PRIMERFASE)
        {
            boolean variableLocal = this.tablero.ingresarFicha(bando, posicion);
            if (variableLocal)
            {
                if(!this.tablero.verificarSiHayMolino())
                {
                    this.cambiarTurno();
                }
                else
                {
                    this.permitidoEliminarFicha = true;
                }
                this.turnoDeLaPartida++;
                if(this.turnoDeLaPartida >= 18)
                {
                    this.fase = Fase.SEGUNDAFASE;
                }
            }
            return variableLocal;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean moverFicha(String posicionA, String posicionB, String alias) {
        return false;
    }

    @Override
    public boolean eliminarFicha(String posicion, String alias) {
        return false;
    }
}

