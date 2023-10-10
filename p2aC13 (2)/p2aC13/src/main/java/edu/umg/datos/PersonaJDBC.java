//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.umg.datos;


import edu.umg.domain.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.umg.datos.Conexion;
import edu.umg.domain.Usuario;

public class PersonaJDBC {
    private Connection conexionTransaccional;
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=?, email=?, telefono=? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona=?";

    public PersonaJDBC() {
    }

    public PersonaJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Persona> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT id_persona, nombre, apellido, email, telefono FROM persona");
            rs = stmt.executeQuery();

            while(rs.next()) {
                int id_persona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                persona = new Persona();
                persona.setId_persona(id_persona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setEmail(email);
                persona.setTelefono(telefono);
                personas.add(persona);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }

        }

        return personas;
    }

    public int insert(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; // Cambiar false a 0

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement("INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)");
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            System.out.println("Ejecutando query: INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)");
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return rows;
    }

    public int update(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; // Cambiar false a 0

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement("UPDATE persona SET nombre=?, apellido=?, email=?, telefono=? WHERE id_persona = ?");
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getId_persona());
            System.out.println("Ejecutando query: UPDATE persona SET nombre=?, apellido=?, email=?, telefono=? WHERE id_persona = ?");
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return rows;
    }

    public int delete(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; // Cambiar false a 0

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement("DELETE FROM persona WHERE id_persona=?");
            stmt.setInt(1, persona.getId_persona());
            System.out.println("Ejecutando query: DELETE FROM persona WHERE id_persona=?");
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return rows;
    }

    // Agregar estos métodos en la clase PersonaJDBC

    public int insertUsuario(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement("INSERT INTO usuario(nombre_usuario, contraseña) VALUES(?, ?)");
            stmt.setString(1, usuario.getNombre_usuario());
            stmt.setString(2, usuario.getContraseña());
            rows = stmt.executeUpdate();
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return rows;
    }

    public boolean validarUsuario(String nombreUsuario, String contraseña) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isValid = false;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM usuario WHERE nombre_usuario = ? AND contraseña = ?");
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contraseña);
            rs = stmt.executeQuery();
            isValid = rs.next(); // Si hay al menos una fila, las credenciales son válidas
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return isValid;
    }

}
