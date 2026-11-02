package crazy.modelo;


public class DetallePedido {
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetallePedido(Producto producto, int cantidad, double precioUnitario, double subtotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
        this.subtotal = this.precioUnitario*cantidad;
    }

    public DetallePedido() {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal=this.precioUnitario * cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
   
}
    