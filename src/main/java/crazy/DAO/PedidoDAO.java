package crazy.DAO;

import crazy.modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


public class PedidoDAO {
    //metodo para nuestro ticket
    public String generarNumeroTicket() throws SQLException {

        String fecha = java.time.LocalDate.now().toString().replace("-", "");
        String sql = "SELECT COUNT(*) FROM pedido WHERE DATE(fechaHora) = CURDATE()";

        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int contador = 1;
            if (rs.next()) {
                contador = rs.getInt(1) + 1;
            }
            return fecha + "-" + String.format("%03d",contador);
        }
    }
    
    public void guardarPedido(Pedido pedido) throws SQLException {

        String sqlPedido = "INSERT INTO pedido (numeroTicket, fechaHora, Total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detallePedido (idPedido, idProducto, Cantidad, precioUnitario, Subtotal) VALUES (?, ?, ?, ?, ?)";

        Connection cn = null;

        try {
            cn = ConexionBD.abrir();
            //Inicia
            cn.setAutoCommit(false); 

            // 1. Encabezad de pedido
            PreparedStatement psPedido = cn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            psPedido.setString(1, pedido.getNumeroTicket());
            psPedido.setTimestamp(2, Timestamp.valueOf(pedido.getFechaHora()));
            psPedido.setDouble(3, pedido.getTotal());
            psPedido.executeUpdate();

            // Id del ssistema
            ResultSet rs = psPedido.getGeneratedKeys();
            int idPedidoGenerado = 0;
            if (rs.next()) {
                idPedidoGenerado = rs.getInt(1);
            }
            pedido.setIdPedido(idPedidoGenerado);

            // 2. Detalles del pedido
            PreparedStatement psDetalle = cn.prepareStatement(sqlDetalle);

            for (DetallePedido d : pedido.getDetalles()) {
                psDetalle.setInt(1, idPedidoGenerado);
                psDetalle.setInt(2, d.getProducto().getIdProducto());
                psDetalle.setInt(3, d.getCantidad());
                psDetalle.setDouble(4, d.getPrecioUnitario());
                psDetalle.setDouble(5, d.getSubtotal());
                psDetalle.addBatch();
            }

            psDetalle.executeBatch();
            //confirmamos
            cn.commit(); 

        } catch (SQLException ex) {
            if (cn != null) {
                cn.rollback(); //por algun error, termina
            }
            throw ex; 
        } finally {
            if (cn != null) {
                cn.setAutoCommit(true);
                cn.close();
            }
        }
    }
}
