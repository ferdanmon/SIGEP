package crazy.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private String numeroTicket;
    private LocalDateTime fechaHora;
    private List<DetallePedido> detalles= new ArrayList<>();
    private double total;

    public Pedido(LocalDateTime fechaHora, List<DetallePedido> detalles, double total) {
        this.fechaHora = LocalDateTime.now();
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }

    public Pedido() {
    }

    
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(String numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
        calcularTotal();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public void calcularTotal(){
        this.total= 0.0;
        for(DetallePedido det:detalles){
        this.total +=det.getSubtotal();
        }
    }
 
    public void agregarDetalle(DetallePedido detalle){
    this.detalles.add(detalle);
    calcularTotal();
    }

}
