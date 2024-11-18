package ar.edu.unlu.molino195157;

import java.util.HashMap;

public class Tablero
{
    //-------------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------------

    private HashMap<String, Posicion> posiciones;

    //-------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------

    public Tablero()
    {
        this.posiciones = new HashMap<>();

        String[] nombresDePosiciones = {"A1", "A4", "A7", "B2", "B4", "B6", "C3", "C4", "C5",
                "D1", "D2", "D3", "D5", "D6", "D7", "E3", "E4", "E5",
                "F2", "F4", "F6", "G1", "G4", "G7"};

        // Llenar el HashMap con objetos Posicion
        for (String nombre : nombresDePosiciones)
        {
            posiciones.put(nombre, new Posicion());
        }
    }

    //-------------------------------------------------------------------------------------
    // Getters y Setters
    //-------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------------

    public void ocuparPosicion(String nombre, Ficha ficha)
    {
        Posicion posicion = posiciones.get(nombre);
        if (posicion != null) {
            posicion.asignarFicha(ficha);
        }
    }

    public void mostrarPosiciones()
    {
        for (String clave : posiciones.keySet())
        {
            System.out.println("Posición: " + clave + ", Ocupado: " + posiciones.get(clave).isOcupado());
        }
    }
}
