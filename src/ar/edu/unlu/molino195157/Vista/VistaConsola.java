package ar.edu.unlu.molino195157.Vista;

import ar.edu.unlu.molino195157.Controlador.Controlador;

import javax.swing.*;
import java.awt.*;

public class VistaConsola extends JFrame {
        private JPanel panelPrincipal;
        private JPanel panelTablero;
        private JTextArea txtSalida;
        private JTextField txtEntrada;
        private JButton btnEnter;
        private Controlador controlador;

        public VistaConsola(Controlador controlador) {
            setTitle("Molino - Consola Visual");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setLocationRelativeTo(null);

            this.controlador = controlador;

            // Panel principal con GridBagLayout
            panelPrincipal = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            // Panel del tablero (izquierda arriba)
            panelTablero = new JPanel(new GridLayout(7, 7)); // por ejemplo
            inicializarTablero();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0.5;
            c.weighty = 0.7;
            c.fill = GridBagConstraints.BOTH;
            panelPrincipal.add(panelTablero, c);

            // Área de texto (derecha arriba)
            txtSalida = new JTextArea();
            txtSalida.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(txtSalida);
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0.5;
            c.weighty = 0.7;
            panelPrincipal.add(scrollPane, c);

            // Campo de entrada (izquierda abajo)
            txtEntrada = new JTextField();
            txtEntrada.setFont(new Font("Arial", Font.PLAIN, 18)); // más grande
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0.8;
            c.weighty = 0.3;
            c.fill = GridBagConstraints.BOTH;
            panelPrincipal.add(txtEntrada, c);

            // Botón enviar (derecha abajo)
            btnEnter = new JButton("Enviar");
            btnEnter.setFont(new Font("Arial", Font.BOLD, 16)); // texto más grande
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 0.2;
            c.fill = GridBagConstraints.BOTH;
            panelPrincipal.add(btnEnter, c);


            setContentPane(panelPrincipal);

            btnEnter.addActionListener(e -> {
                //procesarEntrada(txtEntrada.getText());
                txtEntrada.setText("");
            });

        }

        public void inicializarTablero() {
            for (int i = 0; i < 49; i++) { // 7x7
                JButton btn = new JButton();
                btn.setEnabled(false); // no clickeables
                panelTablero.add(btn);
                setVisible(true);
            }
        }

        // Resto del código igual que ya tenés...
    }

