package ar.edu.unlu.molino195157.Vista;

import ar.edu.unlu.molino195157.Controlador.Controlador;

import java.util.Queue;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class VistaGrafica extends JFrame implements IVista {
    private JPanel panelPrincipal;
    private JPanel panelTablero;
    private JPanel panelAcciones;
    private JPanel panelMensajes;
    private String accionActual = "";
    private Map<String, JButton> botonesPorPosicion = new HashMap<>();
    private JButton btnIngresar;
    private JButton btnEliminar;
    private JButton btnMover;
    private String posicionOrigen = null;
    private final Queue<String> colaMensajes = new LinkedList<>();
    private boolean dialogoAbierto = false;
    private CardLayout layoutMensajes;
    private JPanel contenedorVistas;
    private JTextArea areaChat;
    private CardLayout layoutAcciones;
    private JPanel contenedorAcciones; // Contenedor con CardLayout
    private JPanel panelInicio; // botones de registrarse, etc.
    private JPanel panelJuego;  // botones de jugar (ingresar, mover, eliminar)
    private Controlador controlador;

    public VistaGrafica() {
        setTitle("Molino - Vista Gráfica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel(new BorderLayout());

        construirPanelTablero();
        construirPanelAcciones();
        construirPanelMensajes();

        panelPrincipal.add(panelTablero, BorderLayout.CENTER);
        panelPrincipal.add(panelAcciones, BorderLayout.WEST);
        panelPrincipal.add(panelMensajes, BorderLayout.EAST);


        setContentPane(panelPrincipal);
    }

    private void construirPanelTablero() {
        panelTablero = new JPanel(new GridLayout(7, 7));
        panelTablero.setPreferredSize(new Dimension(450, 450));
        String[][] diseño = {
                {"A1", "—", "—", "D1", "—", "—", "G1"},
                {"|", "B2", "—", "D2", "—", "F2", "|"},
                {"|", "|", "C3", "D3", "E3", "|", "|"},
                {"A4", "B4", "C4", "", "E4", "F4", "G4"},
                {"|", "|", "C5", "D5", "E5", "|", "|"},
                {"|", "B6", "—", "D6", "—", "F6", "|"},
                {"A7", "—", "—", "D7", "—", "—", "G7"}
        };

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                String celda = diseño[i][j];
                switch (celda) {
                    case "—" -> panelTablero.add(new PanelLinea(true, false));
                    case "|" -> panelTablero.add(new PanelLinea(false, true));
                    case "" -> panelTablero.add(new JPanel());
                    default -> {
                        JButton boton = new JButton(celda);
                        boton.setFocusable(false);
                        boton.setContentAreaFilled(false);
                        boton.setOpaque(true);
                        boton.setBackground(Color.GRAY);
                        boton.setForeground(new Color(0x0077FF));
                        boton.setBorderPainted(false);
                        boton.setFocusPainted(false);

                        boton.addActionListener(e -> manejarClick(celda));

                        botonesPorPosicion.put(celda, boton);
                        panelTablero.add(boton);
                    }
                }
            }
        }
    }

    private JButton crearBotonAccion(String nombre) {
        JButton boton = new JButton(nombre);
        boton.setFont(new Font("Arial", Font.BOLD, 18));
        boton.setBackground(new Color(128, 0, 0)); // Bordó
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setOpaque(true);
        boton.setBorderPainted(false);

        boton.addActionListener(e -> {
            accionActual = nombre.toLowerCase();
            actualizarBotonesAccion();
        });

        return boton;
    }



    private void construirPanelAcciones() {
        layoutAcciones = new CardLayout();
        contenedorAcciones = new JPanel(layoutAcciones);
        contenedorAcciones.setPreferredSize(new Dimension(200, 600));

        panelInicio = new JPanel();
        panelInicio.setLayout(new BoxLayout(panelInicio, BoxLayout.Y_AXIS));
        JButton btnRegistrarse = new JButton("Registrarse");
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnElegirBlancas = new JButton("Elegir Blancas");
        JButton btnElegirNegras = new JButton("Elegir Negras");
        JButton btnTop5 = new JButton("Top 5");
        JButton btnContinuar = new JButton("Continuar Partida");

        Dimension tamanoBoton = new Dimension(180, 40);
        JButton[] botones = {
                btnRegistrarse, btnIniciarSesion, btnElegirBlancas,
                btnElegirNegras, btnTop5, btnContinuar
        };

        for (JButton boton : botones) {
            boton.setMaximumSize(tamanoBoton);
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        btnContinuar.addActionListener(e -> controlador.cargarPartida());
        btnRegistrarse.addActionListener(e -> mostrarVentanaRegistro());
        btnIniciarSesion.addActionListener(e -> mostrarVentanaInicioSesion());
        btnElegirBlancas.addActionListener(e -> controlador.unirseAJuegoBlancas());
        btnElegirNegras.addActionListener(e -> controlador.unirseAJuegoNegras());
        btnTop5.addActionListener(e -> controlador.top5());

        int espaciado = 10;

        panelInicio.add(Box.createVerticalStrut(espaciado));
        panelInicio.add(btnRegistrarse);
        panelInicio.add(Box.createVerticalStrut(espaciado));
        panelInicio.add(btnIniciarSesion);
        panelInicio.add(Box.createVerticalStrut(espaciado));
        panelInicio.add(btnElegirBlancas);
        panelInicio.add(Box.createVerticalStrut(espaciado));
        panelInicio.add(btnElegirNegras);
        panelInicio.add(Box.createVerticalStrut(espaciado));
        panelInicio.add(btnTop5);
        panelInicio.add(Box.createVerticalStrut(espaciado));
        panelInicio.add(btnContinuar);
        panelInicio.add(Box.createVerticalGlue());


        panelJuego = new JPanel(new GridLayout(4, 1, 10, 10));
        btnIngresar = crearBotonAccion("Ingresar");
        btnEliminar = crearBotonAccion("Eliminar");
        btnMover = crearBotonAccion("Mover");

        JButton btnRendirse = new JButton("Rendirse");
        btnRendirse.setFont(new Font("Arial", Font.BOLD, 16));
        btnRendirse.setBackground(new Color(128, 0, 0)); // Bordó oscuro
        btnRendirse.setForeground(Color.WHITE);
        btnRendirse.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que querés rendirte?", "Confirmar Rendición", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                controlador.rendirse();
            }
        });

        panelJuego.add(btnIngresar);
        panelJuego.add(btnEliminar);
        panelJuego.add(btnMover);
        panelJuego.add(btnRendirse);

        contenedorAcciones.add(panelInicio, "INICIO");
        contenedorAcciones.add(panelJuego, "JUEGO");

        panelAcciones = contenedorAcciones;
    }


    private void actualizarBotonesAccion() {
        Color activo = new Color(0, 128, 0);     // Verde oscuro
        Color inactivo = new Color(128, 0, 0);   // Bordó

        btnIngresar.setBackground(accionActual.equals("ingresar") ? activo : inactivo);
        btnEliminar.setBackground(accionActual.equals("eliminar") ? activo : inactivo);
        btnMover.setBackground(accionActual.equals("mover") ? activo : inactivo);
    }

    private void actualizarBotonesAccion(JToggleButton seleccionado, JToggleButton[] todos, Color verdeOscuro, Color bordo) {
        for (JToggleButton boton : todos) {
            if (boton == seleccionado) {
                boton.setBackground(verdeOscuro);
            } else {
                boton.setBackground(bordo);
            }
        }
    }

    private void construirPanelMensajes() {
        panelMensajes = new JPanel(new BorderLayout());
        panelMensajes.setPreferredSize(new Dimension(200, 600));

        areaChat = new JTextArea();
        areaChat.setEditable(false);
        areaChat.setLineWrap(true);
        areaChat.setWrapStyleWord(true);

        JScrollPane scrollChat = new JScrollPane(areaChat);

        panelMensajes.add(scrollChat, BorderLayout.CENTER);
    }


    private void manejarClick(String posicion) {
        switch (accionActual) {
            case "ingresar" -> controlador.ingresarFicha(posicion);
            case "eliminar" -> controlador.eliminarFicha(posicion);
            case "mover" -> {
                if (posicionOrigen == null) {
                    posicionOrigen = posicion;
                    mostrarMensaje("Posición origen seleccionada: " + posicionOrigen + ". Ahora seleccioná la posición destino.");
                } else {
                    controlador.moverFicha(posicionOrigen, posicion);
                    posicionOrigen = null; // reset para la próxima movida
                }
            }
            default -> mostrarMensaje("Seleccioná una acción primero.");
        }
    }

    @Override
    public void mostrarFichaIngresada(String posicion, Color color) {
        JButton boton = botonesPorPosicion.get(posicion);
        if (boton != null) boton.setBackground(color);
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
    public void mostrarFichaMovida(String desde, String hasta, Color color) {
        JButton bDesde = botonesPorPosicion.get(desde);
        JButton bHasta = botonesPorPosicion.get(hasta);
        if (bDesde != null && bHasta != null) {
            bDesde.setBackground(Color.GRAY);
            bDesde.setText(desde);
            bHasta.setBackground(color);
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            areaChat.append("• " + mensaje + "\n");
            areaChat.setCaretPosition(areaChat.getDocument().getLength()); // auto-scroll al final
        });
    }

    private void mostrarSiguienteMensaje() {
        SwingUtilities.invokeLater(() -> {
            String siguienteMensaje;
            synchronized (colaMensajes) {
                siguienteMensaje = colaMensajes.poll();
                if (siguienteMensaje == null) {
                    dialogoAbierto = false;
                    return;
                }
                dialogoAbierto = true;
            }
            JOptionPane.showMessageDialog(this, siguienteMensaje);
            synchronized (colaMensajes) {
                dialogoAbierto = false;
            }
            // Mostrar el próximo mensaje si hay más
            mostrarSiguienteMensaje();
        });
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
    public void reiniciarTablero() {
        for (Map.Entry<String, JButton> entry : botonesPorPosicion.entrySet()) {
            entry.getValue().setText(entry.getKey());
            entry.getValue().setBackground(Color.GRAY);
        }
        mostrarMensaje("El tablero fue reiniciado.");
    }

    private void mostrarVentanaRegistro() {
        JTextField alias = new JTextField();
        JPasswordField contrasena = new JPasswordField();

        Object[] mensaje = {
                "Alias:", alias,
                "Contraseña:", contrasena
        };

        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Registro", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            controlador.registrarse(alias.getText(), new String(contrasena.getPassword()));
        }
    }

    private void mostrarVentanaInicioSesion() {
        JTextField alias = new JTextField();
        JPasswordField contrasena = new JPasswordField();

        Object[] mensaje = {
                "Alias:", alias,
                "Contraseña:", contrasena
        };

        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Iniciar Sesión", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            controlador.iniciarSesion(alias.getText(), new String(contrasena.getPassword()));
        }
    }

    @Override
    public void mostrarPanelJuego() {
        layoutAcciones.show(contenedorAcciones, "JUEGO");
    }

    @Override
    public void mostrarPanelInicio() {
        layoutAcciones.show(contenedorAcciones, "INICIO");
    }
}
