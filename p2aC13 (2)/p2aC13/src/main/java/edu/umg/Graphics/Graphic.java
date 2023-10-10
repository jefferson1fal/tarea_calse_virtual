package edu.umg.Graphics;

import edu.umg.datos.Conexion;
import edu.umg.datos.PersonaJDBC;
import edu.umg.domain.Persona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Graphic extends JFrame {

    private JButton btnMostrarPersonas;
    private JButton btnAgregarPersona;
    private JButton btnAgregarUsuario;
    private JButton btnValidarUsuario;
    private JTextArea textArea;

    public Graphic() {
        setTitle("Gestión de Personas y Usuarios");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        btnMostrarPersonas = new JButton("Mostrar Personas");
        btnAgregarPersona = new JButton("Agregar Persona");
        btnAgregarUsuario = new JButton("Agregar Usuario");
        btnValidarUsuario = new JButton("Validar Usuario");
        textArea = new JTextArea(15, 40);

        panel.add(btnMostrarPersonas);
        panel.add(btnAgregarPersona);
        panel.add(btnAgregarUsuario);
        panel.add(btnValidarUsuario);

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        getContentPane().add(panel);

        btnMostrarPersonas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPersonas();
            }
        });

        btnAgregarPersona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPersona();
            }
        });

        btnAgregarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });

        btnValidarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarUsuario();
            }
        });
    }

    private void mostrarPersonas() {
        textArea.setText("");
        Conexion conexion = new Conexion();
        try {
            PersonaJDBC personaJDBC = new PersonaJDBC(conexion.getConnection());
            java.util.List<Persona> personas = personaJDBC.select();
            for (Persona persona : personas) {
                textArea.append(persona.toString() + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            textArea.append("Error al obtener personas: " + e.getMessage() + "\n");
        } finally {
            Conexion.close(conexion);
        }
    }

    private void agregarPersona() {
        // Implementa la lógica para agregar una persona aquí
    }

    private void agregarUsuario() {
        // Implementa la lógica para agregar un usuario aquí
    }

    private void validarUsuario() {
        // Implementa la lógica para validar un usuario aquí
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Graphic gui = new Graphic();
                gui.setVisible(true);
            }
        });
    }
}
