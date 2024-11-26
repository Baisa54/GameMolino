package ar.edu.unlu.molino195157.Vista;

import ar.edu.unlu.molino195157.Controlador.Controlador;
import ar.edu.unlu.molino195157.Modelo.Enums.Posicion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista extends JFrame {
    private Controlador controlador;
    private JButton btnA1, btnA4, btnA7;
    private JButton btnB2, btnB4, btnB6;
    private JButton btnC3, btnC4, btnC5;
    private JButton btnD1, btnD2, btnD3, btnD5, btnD6, btnD7;
    private JButton btnE3, btnE4, btnE5;
    private JButton btnF2, btnF4, btnF6;
    private JButton btnG1, btnG4, btnG7;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Juego de Molino");
        setLayout(new GridLayout(7, 7));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        // Añadir botones a la ventana (tablero)
        add(btnA1);
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío
        add(btnA4);
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío
        add(btnA7);

        add(new JLabel()); // Espacio vacío
        add(btnB2);
        add(btnB4);
        add(btnB6);
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío

        add(new JLabel()); // Espacio vacío
        add(btnC3);
        add(btnC4);
        add(btnC5);
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío

        add(new JLabel()); // Espacio vacío
        add(btnD1);
        add(btnD2);
        add(btnD3);
        add(btnD5);
        add(btnD6);
        add(btnD7);

        add(new JLabel()); // Espacio vacío
        add(btnE3);
        add(btnE4);
        add(btnE5);
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío

        add(new JLabel()); // Espacio vacío
        add(btnF2);
        add(btnF4);
        add(btnF6);
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío

        add(new JLabel()); // Espacio vacío
        add(btnG1);
        add(new JLabel()); // Espacio vacío
        add(btnG4);
        add(new JLabel()); // Espacio vacío
        add(new JLabel()); // Espacio vacío
        add(btnG7);

        // Mostrar la ventana
        setVisible(true);
    }

    public void setControlador(Controlador controlador) {
    }
}

