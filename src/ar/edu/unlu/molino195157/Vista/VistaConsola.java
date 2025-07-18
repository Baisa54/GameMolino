package ar.edu.unlu.molino195157.Vista;

import ar.edu.unlu.molino195157.Controlador.Controlador;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import java.awt.*;

public class VistaConsola extends JFrame implements IVista {
    private JPanel panelPrincipal;
    private JPanel panelTablero;
    private JTextArea txtSalida;
    private JTextField txtEntrada;
    private JButton btnEnter;
    private Controlador controlador;
    private Map<String, JButton> botonesPorPosicion = new HashMap<>();

    public VistaConsola() {
        setTitle("Molino - Consola Visual");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // PANEL PRINCIPAL HORIZONTAL: tablero izquierda, consola derecha
        panelPrincipal = new JPanel(new BorderLayout());

        // PANEL IZQUIERDO: tablero
        panelTablero = new JPanel(new GridLayout(7, 7));
        panelTablero.setPreferredSize(new Dimension(450, 450)); // Tamaño fijo tablero
        inicializarTablero();
        panelTablero.setVisible(false);  // Oculta el tablero al inicio
        panelPrincipal.add(panelTablero, BorderLayout.WEST);

        // PANEL DERECHO: consola y entrada abajo
        JPanel panelDerecho = new JPanel(new BorderLayout());

        // CONSOLA DE TEXTO (CENTRO)
        txtSalida = new JTextArea();
        txtSalida.setEditable(false);
        txtSalida.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(txtSalida);
        panelDerecho.add(scrollPane, BorderLayout.CENTER);

        // PANEL INFERIOR: entrada de texto + botón
        JPanel panelEntrada = new JPanel(new BorderLayout());
        txtEntrada = new JTextField();
        txtEntrada.setFont(new Font("Arial", Font.PLAIN, 16));
        btnEnter = new JButton("Enviar");
        btnEnter.setFont(new Font("Arial", Font.BOLD, 16));

        panelEntrada.add(txtEntrada, BorderLayout.CENTER);
        panelEntrada.add(btnEnter, BorderLayout.EAST);
        panelEntrada.setPreferredSize(new Dimension(400, 50));

        panelDerecho.add(panelEntrada, BorderLayout.SOUTH);

        // Añadir panel derecho al principal
        panelPrincipal.add(panelDerecho, BorderLayout.CENTER);

        setContentPane(panelPrincipal);

        // Acción del botón
        btnEnter.addActionListener(e -> {
            procesarEntrada(txtEntrada.getText());
            txtEntrada.setText("");
        });
    }

    public void inicializarTablero() {
        panelTablero.removeAll();
        panelTablero.setLayout(new GridLayout(7, 7));
        panelTablero.setBackground(Color.LIGHT_GRAY); // o LIGHT_GRAY si querés

        String[][] diseño = {
                {"A1", "—", "—", "D1", "—", "—", "G1"},
                {"|", "B2", "—", "D2", "—", "F2", "|"},
                {"|", "|", "C3", "D3", "E3", "|", "|"},
                {"A4", "B4", "C4", "", "E4", "F4", "G4"},
                {"|", "|", "C5", "D5", "E5", "|", "|"},
                {"|", "B6", "—", "D6", "—", "F6", "|"},
                {"A7", "—", "—", "D7", "—", "—", "G7"}
        };

        Font fuente = new Font("Arial", Font.BOLD, 18);
        botonesPorPosicion.clear(); // Limpia por si se reinicia

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                String celda = diseño[i][j];

                switch (celda) {
                    case "—" -> panelTablero.add(new PanelLinea(true, false));
                    case "|" -> panelTablero.add(new PanelLinea(false, true));
                    case "" -> panelTablero.add(new JPanel()); // celda vacía
                    default -> {
                        JButton boton = new JButton(celda);
                        boton.setFocusable(false); // no recibe foco
                        boton.setRequestFocusEnabled(false); // no permite que el foco lo seleccione
                        boton.setContentAreaFilled(false); // evita el relleno celeste por defecto
                        boton.setOpaque(true); // pero seguimos permitiendo fondo sólido
                        boton.setBackground(Color.GRAY); // fondo gris
                        boton.setForeground(new Color(0x0077FF)); // texto azul
                        boton.setBorderPainted(false); // sin borde
                        boton.setFocusPainted(false); // sin borde azul de selección


                        panelTablero.add(boton);
                        botonesPorPosicion.put(celda, boton);


                        panelTablero.add(boton);
                        botonesPorPosicion.put(celda, boton);
                    }
                }
            }
        }

        panelTablero.revalidate();
        panelTablero.repaint();
    }

    private void procesarEntrada(String comando) {
        String[] partes = comando.trim().split(" ");
        if (partes.length == 0) return;

        switch (partes[0].toLowerCase()) {
            case "registrarse" -> {
                if (partes.length == 3) {
                    controlador.registrarse(partes[1], partes[2]);
                } else {
                    mostrarMensaje("Uso: registrarse <alias> <contraseña>");
                }
            }
            case "iniciarsesion" -> {
                if (partes.length == 3) {
                    controlador.iniciarSesion(partes[1], partes[2]);
                } else {
                    mostrarMensaje("Uso: iniciarSesion <alias> <contraseña>");
                }
            }
            case "ingresar" -> {
                if (partes.length == 2) {
                    controlador.ingresarFicha(partes[1]);
                } else {
                    mostrarMensaje("Uso: ingresar <posicion>");
                }
            }
            case "mover" -> {
                if (partes.length == 3) {
                    controlador.moverFicha(partes[1], partes[2]);
                } else {
                    mostrarMensaje("Uso: mover <desde> <hasta>");
                }
            }
            case "eliminar" -> {
                if (partes.length == 2) {
                    controlador.eliminarFicha(partes[1]);
                } else {
                    mostrarMensaje("Uso: eliminar <posicion>");
                }
            }
            case "unirseblancas" -> {
                controlador.unirseAJuegoBlancas();
            }
            case "unirsenegras" -> {
                controlador.unirseAJuegoNegras();
            }
            case "top5" -> {
                controlador.top5();
            }
            case "rendirse" -> {
                controlador.rendirse();
            }
            case "continuar" -> {
                controlador.cargarPartida();
            }

            case "help" -> {
                mostrarMensaje("""
                        Comandos disponibles:
                        • registrarse <alias> <contraseña>    - Crea un nuevo usuario
                        • iniciarSesion <alias> <contraseña>  - Inicia sesión con un alias
                        • ingresar <posicion>                 - Coloca una ficha en el tablero
                        • mover <desde> <hasta>               - Mueve una ficha del jugador
                        • eliminar <posicion>                 - Elimina una ficha del rival
                        • unirseBlancas                       - Se une al juego como jugador blanco
                        • unirseNegras                        - Se une al juego como jugador negro
                        • rendirse                            - te rendis y gana el rival.
                        • continuar                           - carga partida anterior.
                        • top5                                - muestra un listado del top5
                        • help                                - Muestra este mensaje.
                       \s""");
            }
            default -> mostrarMensaje("Comando no reconocido.");
        }
    }

    @Override
    public void reiniciarTablero() {
        for (Map.Entry<String, JButton> entry : botonesPorPosicion.entrySet()) {
            String posicion = entry.getKey();
            JButton boton = entry.getValue();

            boton.setText(posicion);
            boton.setBackground(Color.GRAY);
        }

        mostrarMensaje("El tablero ha sido reiniciado.");
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void iniciar() {
        setVisible(true);
    }

    @Override
    public void mostrarFichaIngresada(String posicion, Color color) {
        JButton boton = botonesPorPosicion.get(posicion);
        if (boton != null) {
            boton.setBackground(color);
        }
    }

    @Override
    public void mostrarFichaMovida(String desde, String hasta, Color color) {
        JButton botonDesde = botonesPorPosicion.get(desde);
        JButton botonHasta = botonesPorPosicion.get(hasta);

        if (botonDesde != null && botonHasta != null) {
            botonDesde.setText(desde); // Restaurar etiqueta original
            botonDesde.setBackground(Color.GRAY); // Color por defecto del tablero

            botonHasta.setBackground(color);
        } else {
            mostrarMensaje("Error al mover ficha");
        }
    }

    @Override
    public void mostrarFichaEliminada(String posicion) {
        JButton boton = botonesPorPosicion.get(posicion);
        if (boton != null) {
            boton.setText(posicion);
            boton.setBackground(Color.GRAY);
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        txtSalida.append(mensaje + "\n");
        txtSalida.setCaretPosition(txtSalida.getDocument().getLength());
    }

    @Override
    public void mostrarPanelJuego() {
        panelTablero.setVisible(true);
    }

    @Override
    public void mostrarPanelInicio() {
        panelTablero.setVisible(false);
    }
}


