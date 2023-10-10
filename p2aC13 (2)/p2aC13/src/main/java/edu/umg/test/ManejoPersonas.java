package edu.umg.test;

import edu.umg.datos.Conexion;
import edu.umg.datos.PersonaJDBC;
import edu.umg.domain.Persona;
import edu.umg.domain.Usuario; // Agrega la importación de Usuario
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;

public class ManejoPersonas {
    public ManejoPersonas() {
    }

    public static void desplegar() {
        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            PersonaJDBC personaJdbc = new PersonaJDBC(conexion);
            Iterator var2 = personaJdbc.select().iterator();

            while(var2.hasNext()) {
                Persona persona = (Persona)var2.next();
                System.out.println("persona = " + persona);
            }

            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");
        } catch (SQLException var5) {
            var5.printStackTrace(System.out);
            System.out.println("Entramos al rollback");

            try {
                conexion.rollback();
            } catch (SQLException var4) {
                var4.printStackTrace(System.out);
            }
        }

    }

    public static void main(String[] args) {
        desplegar();
        Scanner scanner = new Scanner(System.in);
        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            PersonaJDBC personaJdbc = new PersonaJDBC(conexion);
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Monica");
            nuevaPersona.setApellido("Veliz");
            personaJdbc.insert(nuevaPersona);

            // Registro de un nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            System.out.print("Ingrese nombre de usuario: ");
            nuevoUsuario.setNombre_usuario(scanner.next());
            System.out.print("Ingrese contraseña: ");
            nuevoUsuario.setContraseña(scanner.next());
            personaJdbc.insertUsuario(nuevoUsuario);

            // Validación de usuario
            System.out.print("Ingrese nombre de usuario: ");
            String nombreUsuario = scanner.next();
            System.out.print("Ingrese contraseña: ");
            String contraseña = scanner.next();
            boolean isValid = personaJdbc.validarUsuario(nombreUsuario, contraseña);

            if (isValid) {
                System.out.println("Credenciales válidas. Acceso concedido.");
            } else {
                System.out.println("Credenciales inválidas. Acceso denegado.");
            }

            conexion.commit();
            System.out.println("Se ha hecho commit de la transacción");
        } catch (SQLException var6) {
            var6.printStackTrace(System.out);
            System.out.println("Entramos al rollback");

            try {
                conexion.rollback();
            } catch (SQLException var5) {
                var5.printStackTrace(System.out);
            }
        } finally {
            scanner.close(); // Cerrar el scanner al finalizar
        }
    }
}
