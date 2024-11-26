package ar.edu.unlu.molino195157.Vista;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class TableroVista extends JFrame {

    private JButton[][] botones;
    private JPanel panelTablero;

    // Definir las posiciones no utilizadas
    private Set<String> posicionesNoUsadas;

    public TableroVista() {
        this.setTitle("Juego de Molinos");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(7, 7)); // Tablero 7x7
        botones = new JButton[7][7];

        // Inicializar las posiciones no utilizadas
        posicionesNoUsadas = new HashSet<>();
        // Agregar las posiciones no usadas a la lista
        String[] noUsadas = {"A2", "A3", "A5", "A6", "B1", "B3", "B5", "B7",
                "C1", "C2", "C6", "C7", "D4", "E1", "E2", "E6", "E7",
                "F1", "F3", "F5", "F7", "G2", "G3", "G5", "G6"};
        for (String pos : noUsadas) {
            posicionesNoUsadas.add(pos);
        }

        // Crear botones para las posiciones
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                String posicion = String.format("%s%d", (char) ('A' + j), i + 1);

                // Verificar si la posición está en las posiciones no usadas
                if (posicionesNoUsadas.contains(posicion)) {
                    botones[i][j] = new JButton();  // Botón vacío
                    botones[i][j].setEnabled(false);  // Deshabilitar botón
                } else {
                    botones[i][j] = new JButton(posicion);
                    botones[i][j].setPreferredSize(new Dimension(60, 60));
                    botones[i][j].setBackground(Color.GRAY); // Fondo blanco por defecto
                    botones[i][j].addActionListener(e -> {
                        JButton fuente = (JButton) e.getSource();
                        String posicionSeleccionada = fuente.getText();
                        System.out.println("Se ha seleccionado la posición: " + posicionSeleccionada);
                    });
                }

                panelTablero.add(botones[i][j]);
            }
        }

        this.add(panelTablero);
        this.setVisible(true);
    }

    // Método para actualizar el tablero
    public void actualizarTablero(String[][] tableroEstado) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (tableroEstado[i][j] != null) {
                    if (tableroEstado[i][j].equals("BLANCA")) {
                        botones[i][j].setBackground(Color.GREEN); // Ficha blanca
                        botones[i][j].setText("B");
                    } else if (tableroEstado[i][j].equals("NEGRA")) {
                        botones[i][j].setBackground(Color.RED); // Ficha negra
                        botones[i][j].setText("N");
                    }
                } else {
                    botones[i][j].setBackground(Color.WHITE); // Casilla vacía
                    botones[i][j].setText(""); // Sin ficha
                }
            }
        }
    }

    public static void main(String[] args) {
        new TableroVista();
    }
}

