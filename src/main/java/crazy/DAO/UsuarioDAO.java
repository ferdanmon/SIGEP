package crazy.DAO;

import crazy.modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class UsuarioDAO {
    
    public Usuario validacionLogin(String nombre, String clave) throws SQLException{
    String sql = "SELECT * FROM usuario WHERE Nombre = ? AND Contraseña = ?";
        try (Connection conn = ConexionBD.abrir();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario us = new Usuario();
                us.setIdUsuario(rs.getInt("idUsuario"));
                us.setNombre(rs.getString("Nombre"));
                us.setContraseña(rs.getString("Contraseña"));
                us.setRol(rs.getString("ROL"));
                return us;
            }   
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al validar el login");
        }
        return null;

    }
    
}
