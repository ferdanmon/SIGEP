package crazy.modelo;


public class Usuario {       
    private int idUsuario;
    private String nombre;
    private String contraseña;
    private String rol;
    

    public Usuario(int idUsuario, String nombre, String contraseña, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public Usuario() {
    }
                                                                                       
    public int getIdUsuario() {                                                                      
        


        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
