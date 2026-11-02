package crazy.DAO;

import crazy.modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ProductoDAO {
    //metodo para insertar Pend
    public void insertar(Producto producto) throws SQLException {
        
        String sql = "INSERT INTO producto (Nombre, Descripcion, Precio, Categoria, Disponible) VALUES (?,?,?,?,?)";
        
        try (Connection cn = ConexionBD.abrir(); 
                PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getCategoria());
            ps.setBoolean(5, producto.isDisponible());
            
            int filas = ps.executeUpdate();
            
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el registro.");
            }
        }
    }
    
    //metodod para listar todos
    public List<Producto> listar() throws SQLException {
        
        List<Producto> lista = new ArrayList<>();
        
        String sql = "SELECT idProducto, Nombre, Descripcion, Precio, Categoria, Disponible FROM producto";
        
        try (Connection cn = ConexionBD.abrir(); 
                PreparedStatement ps = cn.prepareStatement(sql); 
                ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                
                Producto producto = new Producto();

                // Asignar los valores obtenidos de MySQL
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setCategoria(rs.getString("Categoria"));
                producto.setDisponible(rs.getBoolean("Disponible"));
                
                lista.add(producto);
            }
        }
        
        return lista;
    }
    
    //listar por categoria
    public List<Producto> listarPorCategoria(String categoria) throws SQLException {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT idProducto, Nombre, Descripcion, Precio, Categoria, Disponible FROM producto WHERE Categoria = ? AND Disponible = 1";

        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, categoria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();

                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setCategoria(rs.getString("Categoria"));
                producto.setDisponible(rs.getBoolean("Disponible"));

                lista.add(producto);
            }
        }

        return lista;
    }
    
    public void actualizar(Producto producto) throws SQLException {
        
        String sql = "UPDATE producto SET Nombre=?, Descripcion=?, Precio=?, Categoria=?, Disponible=? WHERE idProducto = ?";
        
        try (Connection cn = ConexionBD.abrir(); 
                PreparedStatement ps = cn.prepareStatement(sql)) {

            // Asignación de parámetros
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getCategoria());
            ps.setBoolean(5, producto.isDisponible());
            ps.setInt(6, producto.getIdProducto());
            
            int filas = ps.executeUpdate();
            
            if (filas == 0) {
                throw new SQLException("No se encontró el registro para actualizar.");
            }
        }
    }
    
    //metodo para eliminar mostrando si el producto esta disponible o no 
    public void eliminar(int id) throws SQLException {
        
        String sql = "UPDATE producto SET Disponible = 0 WHERE idProducto = ?";
        
        try (Connection cn = ConexionBD.abrir(); 
                PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();
            
            if (filas == 0) {
                throw new SQLException("No se encontró el registro para eliminar.");
            }
        }
    }
    
    //metodo para buscar algun productoo
    public Producto buscarPorId(int idProducto) throws SQLException {

        String sql = "SELECT idProducto, Nombre, Descripcion, Precio, Categoria, Disponible FROM producto WHERE idProducto = ?";

        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Producto producto = new Producto();

                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setCategoria(rs.getString("Categoria"));
                producto.setDisponible(rs.getBoolean("Disponible"));

                return producto;
            }
        }

        return null;
    }
    
}
