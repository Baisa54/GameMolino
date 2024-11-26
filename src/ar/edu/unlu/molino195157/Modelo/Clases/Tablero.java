package ar.edu.unlu.molino195157.Modelo.Clases;

import ar.edu.unlu.molino195157.Modelo.Enums.Bando;
import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tablero
{
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    private HashMap<Posicion, Casilla> posiciones;
    private List<CombinacionDeMolino> molinos;
    private HashMap<Posicion, Adyascente> adyascentes;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Tablero()
    {
        this.posiciones = new HashMap<>();
        this.molinos = new ArrayList<>();
        this.adyascentes = new HashMap<>();

        // Llenar el HashMap con objetos Casilla, su clave sera Posicion.

        for (Posicion posicion : Posicion.values())
        {
            posiciones.put(posicion, new Casilla());
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

        //casillas Adyascentes

        adyascentes.put(Posicion.A1, new Adyascente(Posicion.A4, Posicion.D1));
        adyascentes.put(Posicion.A4, new Adyascente(Posicion.A1, Posicion.A7, Posicion.B4));
        adyascentes.put(Posicion.A7, new Adyascente(Posicion.A4, Posicion.D7));
        adyascentes.put(Posicion.B2, new Adyascente(Posicion.D2, Posicion.B4));
        adyascentes.put(Posicion.B4, new Adyascente(Posicion.A4, Posicion.B2, Posicion.B6, Posicion.C4));
        adyascentes.put(Posicion.B6, new Adyascente(Posicion.B4, Posicion.D6));
        adyascentes.put(Posicion.C3, new Adyascente(Posicion.D3, Posicion.C4));
        adyascentes.put(Posicion.C4, new Adyascente(Posicion.B4, Posicion.C3, Posicion.C5));
        adyascentes.put(Posicion.C5, new Adyascente(Posicion.C4, Posicion.D6));
        adyascentes.put(Posicion.D1, new Adyascente(Posicion.A1, Posicion.D2, Posicion.G1));
        adyascentes.put(Posicion.D2, new Adyascente(Posicion.B2, Posicion.D1, Posicion.F2, Posicion.D3));
        adyascentes.put(Posicion.D3, new Adyascente(Posicion.D2, Posicion.C3, Posicion.E3));
        adyascentes.put(Posicion.D5, new Adyascente(Posicion.C5, Posicion.D6, Posicion.E5));
        adyascentes.put(Posicion.D6, new Adyascente(Posicion.B6, Posicion.D5, Posicion.D7, Posicion.F6));
        adyascentes.put(Posicion.D7, new Adyascente(Posicion.A7, Posicion.G7, Posicion.D6));
        adyascentes.put(Posicion.E3, new Adyascente(Posicion.D3, Posicion.E4));
        adyascentes.put(Posicion.E4, new Adyascente(Posicion.E3, Posicion.E5, Posicion.F4));
        adyascentes.put(Posicion.E5, new Adyascente(Posicion.E4, Posicion.D5));
        adyascentes.put(Posicion.F2, new Adyascente(Posicion.D2, Posicion.F4));
        adyascentes.put(Posicion.F4, new Adyascente(Posicion.E4, Posicion.G4, Posicion.F2, Posicion.F6));
        adyascentes.put(Posicion.F6, new Adyascente(Posicion.D6, Posicion.F4));
        adyascentes.put(Posicion.G1, new Adyascente(Posicion.D1, Posicion.G4));
        adyascentes.put(Posicion.G4, new Adyascente(Posicion.G1, Posicion.F4, Posicion.G7));
        adyascentes.put(Posicion.G7, new Adyascente(Posicion.D7, Posicion.G4));
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public boolean ocuparPosicion(Posicion posi, Ficha ficha)
    {
        Casilla casilla = posiciones.get(posi);
        if (casilla == null) {
            System.out.println("Error: La posición " + posi + " no está mapeada.");
            return false;
        }
        if(!casilla.isOcupado())
        {
            casilla.asignarFicha(ficha);
            return true;
        }
        else
        {
            return false;
        }
    }


    public Casilla obtenerCasilla(Posicion posicion)
    {
        return posiciones.get(posicion);
    }

    public Bando verificadorDeMolinos()
    {
        for (CombinacionDeMolino molino : molinos)
        {
            if (!molino.isCombinacionMolinoOcupada() && molino.isMolino())
            {
                // Devuelve el bando que formó el molino
                return molino.isMolinoBando();
            }
        }
        return Bando.NINGUNO;
    }

    public Ficha eliminarFichaDelTablero(Posicion posicion)
    {
        Casilla casilla = posiciones.get(posicion);
        return casilla.eliminarFicha();
    }

    public Ficha verificarFichaDelTablero(Posicion posicion)
    {
        return posiciones.get(posicion).getFicha();
    }

    public boolean moverFicha(Posicion posicionOrigen, Posicion posicionAColocar)
    {
        if(adyascentes.get(posicionOrigen).esPosicionAdyacente(posicionAColocar))
        {
            if(!obtenerCasilla(posicionAColocar).isOcupado())
            {
                ocuparPosicion(posicionAColocar, this.posiciones.get(posicionOrigen).getFicha());
                this.posiciones.get(posicionOrigen).sacarFicha();
                return true;
            }
        }
        return false;
    }
}
