package ar.edu.unlu.molino195157.Vista;

import ar.edu.unlu.molino195157.Controlador.Controlador;
import ar.edu.unlu.molino195157.Modelo.Enums.Bando;
import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;

import javax.swing.*;
import java.awt.*;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class TableroVista extends JFrame {

    private JButton[][] botones;
    private JPanel panelTablero;
    private Controlador controlador;

    // Conjunto de posiciones válidas
    private final Set<Posicion> posicionesValidas;

    // Latch para bloquear el flujo hasta que el jugador seleccione una posición
    private CountDownLatch latch;

    public TableroVista(Controlador controlador) {
        this.controlador = controlador;
        this.setTitle("Juego de Molinos");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(7, 7)); // Tablero 7x7
        botones = new JButton[7][7];

        // Conjunto de posiciones válidas basado en el enum
        posicionesValidas = EnumSet.allOf(Posicion.class);

        // Crear botones para las posiciones
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                String posicion = String.format("%s%d", (char) ('A' + j), i + 1);

                try {
                    Posicion posEnum = Posicion.valueOf(posicion); // Convertir texto a enum

                    botones[i][j] = new JButton(posicion);
                    botones[i][j].setPreferredSize(new Dimension(60, 60));
                    botones[i][j].setBackground(Color.DARK_GRAY);

                    // Asignar acción al botón
                    botones[i][j].addActionListener(e -> {
                        if (controlador != null) {
                            controlador.seleccionarPosicion(posEnum); // Selección de posición
                            desbloquearAccion(); // Desbloqueamos la acción después de la selección
                        }
                    });
                } catch (IllegalArgumentException e) {
                    // No es una posición válida, crear un botón deshabilitado
                    botones[i][j] = new JButton();
                    botones[i][j].setEnabled(false);
                }
                panelTablero.add(botones[i][j]);
            }
        }

        this.add(panelTablero);
        this.setVisible(true);
    }

    public void actualizarCasilla(Posicion posicion, Bando bando) {
        // Definir el mapeo de posiciones a coordenadas fila/columna
        Map<Posicion, int[]> coordenadas = new HashMap<>();
        coordenadas.put(Posicion.A1, new int[]{0, 0});
        coordenadas.put(Posicion.A4, new int[]{3, 0});
        coordenadas.put(Posicion.A7, new int[]{6, 0});
        coordenadas.put(Posicion.B2, new int[]{1, 1});
        coordenadas.put(Posicion.B4, new int[]{3, 1});
        coordenadas.put(Posicion.B6, new int[]{5, 1});
        coordenadas.put(Posicion.C3, new int[]{2, 2});
        coordenadas.put(Posicion.C4, new int[]{3, 2});
        coordenadas.put(Posicion.C5, new int[]{4, 2});
        coordenadas.put(Posicion.D1, new int[]{0, 3});
        coordenadas.put(Posicion.D2, new int[]{1, 3});
        coordenadas.put(Posicion.D3, new int[]{2, 3});
        coordenadas.put(Posicion.D5, new int[]{4, 3});
        coordenadas.put(Posicion.D6, new int[]{5, 3});
        coordenadas.put(Posicion.D7, new int[]{6, 3});
        coordenadas.put(Posicion.E3, new int[]{2, 4});
        coordenadas.put(Posicion.E4, new int[]{3, 4});
        coordenadas.put(Posicion.E5, new int[]{4, 4});
        coordenadas.put(Posicion.F2, new int[]{1, 5});
        coordenadas.put(Posicion.F4, new int[]{3, 5});
        coordenadas.put(Posicion.F6, new int[]{5, 5});
        coordenadas.put(Posicion.G1, new int[]{0, 6});
        coordenadas.put(Posicion.G4, new int[]{3, 6});
        coordenadas.put(Posicion.G7, new int[]{6, 6});

        // Obtener la fila y la columna usando las coordenadas
        int[] coords = coordenadas.get(posicion);
        int fila = coords[0];
        int columna = coords[1];

        // Actualizar la apariencia del botón basado en el bando
        switch (bando) {
            case BLANCAS -> {
                botones[fila][columna].setBackground(Color.LIGHT_GRAY); // Ficha blanca
                botones[fila][columna].setText("B");
            }
            case NEGRAS -> {
                botones[fila][columna].setBackground(Color.BLACK); // Ficha negra
                botones[fila][columna].setText("N");
            }
            case ELIMINADO -> {
                botones[fila][columna].setBackground(Color.DARK_GRAY);
                botones[fila][columna].setText("S/O");
                botones[fila][columna].setEnabled(true);
            }
        }
    }

    // Mostrar mensaje al usuario
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }

    // Habilitar todos los botones válidos
    public void habilitarBotones() {
        for (Posicion posicion : posicionesValidas) {
            String texto = posicion.name();
            habilitarBoton(texto);
        }
    }

    private void habilitarBoton(String texto) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (botones[i][j].getText().equals(texto) || "B".equals(botones[i][j].getText()) || "N".equals(botones[i][j].getText())) {
                    botones[i][j].setEnabled(true);
                }
            }
        }
    }


    // Deshabilitar todos los botones
    public void deshabilitarBotones() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                botones[i][j].setEnabled(false);
            }
        }
    }

    // Método bloqueante para esperar que el jugador haga su acción
    public void AccionDelJugador() {
        latch = new CountDownLatch(1); // Reinicia el CountDownLatch cada vez que comienza una nueva acción

        // Habilitar los botones para que el jugador pueda hacer su selección
        habilitarBotones();

        try {
            // Bloquear hasta que el jugador seleccione una posición
            latch.await();  // Espera a que el latch sea contado
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Después de que el jugador hizo la acción, podemos continuar con el flujo
        System.out.println("Jugador ha hecho su acción, continuamos.");
    }

    // Método que es llamado por el controlador para iniciar la espera
    public void iniciarAccionDelJugador() {
        // Este método es llamado cuando el controlador requiere que el jugador haga una acción
        AccionDelJugador();
    }

    // Método para que el controlador desbloquee la ejecución una vez el jugador haya seleccionado una posición
    public void desbloquearAccion() {
        if (latch != null) {
            latch.countDown(); // Liberar el latch y continuar
        }
    }
}
