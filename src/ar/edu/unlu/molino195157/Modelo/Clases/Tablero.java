package ar.edu.unlu.molino195157.Modelo.Clases;

import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Tablero {
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    // atributos de clase
    private TransformadorDePosiciones tdp;

    // atributos propios
    private HashMap<Posicion, Casilla> Casillas;
    private List<CombinacionDeMolino> molinos;


    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Tablero()
    {
        this.Casillas = new HashMap<>();
        this.molinos = new ArrayList<>();
        this.tdp = new TransformadorDePosiciones();

        // Llenar el HashMap con objetos Casilla, su clave sera Posicion.

        for (Posicion posicion : Posicion.values())
        {
            Casillas.put(posicion, new Casilla());
        }

        //Molinos verticales
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.A1), obtenerCasilla(Posicion.A4), obtenerCasilla(Posicion.A7)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.B2), obtenerCasilla(Posicion.B4), obtenerCasilla(Posicion.B6)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.C3), obtenerCasilla(Posicion.C4), obtenerCasilla(Posicion.C5)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.D1), obtenerCasilla(Posicion.D2), obtenerCasilla(Posicion.D3)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.D5), obtenerCasilla(Posicion.D6), obtenerCasilla(Posicion.D7)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.E3), obtenerCasilla(Posicion.E4), obtenerCasilla(Posicion.E5)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.F2), obtenerCasilla(Posicion.F4), obtenerCasilla(Posicion.F6)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.G1), obtenerCasilla(Posicion.G4), obtenerCasilla(Posicion.G7)));

        //Molinos horizontales
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.A1), obtenerCasilla(Posicion.D1), obtenerCasilla(Posicion.G1)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.B2), obtenerCasilla(Posicion.D2), obtenerCasilla(Posicion.F2)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.C3), obtenerCasilla(Posicion.D3), obtenerCasilla(Posicion.E3)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.C5), obtenerCasilla(Posicion.D5), obtenerCasilla(Posicion.E5)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.A7), obtenerCasilla(Posicion.D7), obtenerCasilla(Posicion.G7)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.B6), obtenerCasilla(Posicion.D6), obtenerCasilla(Posicion.F6)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.A4), obtenerCasilla(Posicion.B4), obtenerCasilla(Posicion.C4)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.E4), obtenerCasilla(Posicion.F4), obtenerCasilla(Posicion.G4)));

        //Molinos Diagonales
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.A7), obtenerCasilla(Posicion.B6), obtenerCasilla(Posicion.C5)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.A1), obtenerCasilla(Posicion.B2), obtenerCasilla(Posicion.C3)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.E3), obtenerCasilla(Posicion.F2), obtenerCasilla(Posicion.G1)));
        molinos.add(new CombinacionDeMolino(obtenerCasilla(Posicion.E5), obtenerCasilla(Posicion.F6), obtenerCasilla(Posicion.G7)));

        // Establecer adyacentes para cada casilla
        obtenerCasilla(Posicion.A1).setListaAdyascenteAdyascente(Posicion.A4, Posicion.D1);
        obtenerCasilla(Posicion.A4).setListaAdyascenteAdyascente(Posicion.A1, Posicion.A7, Posicion.B4);
        obtenerCasilla(Posicion.A7).setListaAdyascenteAdyascente(Posicion.A4, Posicion.D7);
        obtenerCasilla(Posicion.B2).setListaAdyascenteAdyascente(Posicion.D2, Posicion.B4);
        obtenerCasilla(Posicion.B4).setListaAdyascenteAdyascente(Posicion.A4, Posicion.B2, Posicion.B6, Posicion.C4);
        obtenerCasilla(Posicion.B6).setListaAdyascenteAdyascente(Posicion.B4, Posicion.D6);
        obtenerCasilla(Posicion.C3).setListaAdyascenteAdyascente(Posicion.D3, Posicion.C4);
        obtenerCasilla(Posicion.C4).setListaAdyascenteAdyascente(Posicion.B4, Posicion.C3, Posicion.C5);
        obtenerCasilla(Posicion.C5).setListaAdyascenteAdyascente(Posicion.C4, Posicion.D5);
        obtenerCasilla(Posicion.D1).setListaAdyascenteAdyascente(Posicion.A1, Posicion.D2, Posicion.G1);
        obtenerCasilla(Posicion.D2).setListaAdyascenteAdyascente(Posicion.B2, Posicion.D1, Posicion.F2, Posicion.D3);
        obtenerCasilla(Posicion.D3).setListaAdyascenteAdyascente(Posicion.D2, Posicion.C3, Posicion.E3);
        obtenerCasilla(Posicion.D5).setListaAdyascenteAdyascente(Posicion.C5, Posicion.D6, Posicion.E5);
        obtenerCasilla(Posicion.D6).setListaAdyascenteAdyascente(Posicion.B6, Posicion.D5, Posicion.D7, Posicion.F6);
        obtenerCasilla(Posicion.D7).setListaAdyascenteAdyascente(Posicion.A7, Posicion.G7, Posicion.D6);
        obtenerCasilla(Posicion.E3).setListaAdyascenteAdyascente(Posicion.D3, Posicion.E4);
        obtenerCasilla(Posicion.E4).setListaAdyascenteAdyascente(Posicion.E3, Posicion.E5, Posicion.F4);
        obtenerCasilla(Posicion.E5).setListaAdyascenteAdyascente(Posicion.E4, Posicion.D5);
        obtenerCasilla(Posicion.F2).setListaAdyascenteAdyascente(Posicion.D2, Posicion.F4);
        obtenerCasilla(Posicion.F4).setListaAdyascenteAdyascente(Posicion.E4, Posicion.G4, Posicion.F2, Posicion.F6);
        obtenerCasilla(Posicion.F6).setListaAdyascenteAdyascente(Posicion.D6, Posicion.F4);
        obtenerCasilla(Posicion.G1).setListaAdyascenteAdyascente(Posicion.D1, Posicion.G4);
        obtenerCasilla(Posicion.G4).setListaAdyascenteAdyascente(Posicion.G1, Posicion.F4, Posicion.G7);
        obtenerCasilla(Posicion.G7).setListaAdyascenteAdyascente(Posicion.D7, Posicion.G4);
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public Casilla obtenerCasilla(Posicion posicion)
    {
        return Casillas.get(posicion);
    }

    public Boolean ingresarFicha (String alias, String posicion)
    {
        Posicion posicionVariableLocal = this.tdp.stringToPosicion(posicion);
        if (posicionVariableLocal == Posicion.POSICION_NULA) return false;

        if (!obtenerCasilla(posicionVariableLocal).isOcupado())
        {
            if(!obtenerCasilla(posicionVariableLocal).isOcupado()) {
                obtenerCasilla(posicionVariableLocal).asignarCasillaConFicha(alias);
                return true;
            }
            else {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public Boolean moverFicha (String alias, String posicionA, String posicionB)
    {
        Posicion posicionVariableLocalA = this.tdp.stringToPosicion(posicionA);
        Posicion posicionVariableLocalB = this.tdp.stringToPosicion(posicionB);

        if (posicionVariableLocalA == Posicion.POSICION_NULA) return false;
        if (posicionVariableLocalB == Posicion.POSICION_NULA) return false;

        if (obtenerCasilla(posicionVariableLocalA).isOcupado() && !obtenerCasilla(posicionVariableLocalB).isOcupado() && obtenerCasilla(posicionVariableLocalA).esPosicionAdyacente(posicionVariableLocalB))
        {
            if (Objects.equals(obtenerCasilla(posicionVariableLocalA).getBando(), alias))
            {
                obtenerCasilla(posicionVariableLocalB).asignarCasillaConFicha(obtenerCasilla(posicionVariableLocalA).getBando());
                obtenerCasilla(posicionVariableLocalA).eliminarFichaDeCasilla();
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public Boolean eliminarFichaRival (String alias, String posicion)
    {
        Posicion posicionVariableLocal = this.tdp.stringToPosicion(posicion);
        if (posicionVariableLocal == Posicion.POSICION_NULA) return false;

        if (obtenerCasilla(posicionVariableLocal).isOcupado() && !Objects.equals(obtenerCasilla(posicionVariableLocal).getBando(), alias))
        {
            obtenerCasilla(posicionVariableLocal).eliminarFichaDeCasilla();
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean verificarSiHayMolino()
    {
        for (CombinacionDeMolino molino : molinos)
        {
            if (!molino.isCombinacionMolinoOcupada() && molino.isMolino())
            {
                return true;
            }
        }
        return false;
    }
}
