import java.time.LocalDateTime;

public class Prestamo {
    private int id;
    private int idUsuario;
    private int idLibro;
    private int tiempo;
    private String estatus;
    private double penalizacion;
    private LocalDateTime fechaPrestamo;
    private LocalDateTime fechaDevolucion;

    public Prestamo() {}

    // getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public int getIdLibro() { return idLibro; }
    public void setIdLibro(int idLibro) { this.idLibro = idLibro; }
    public int getTiempo() { return tiempo; }
    public void setTiempo(int tiempo) { this.tiempo = tiempo; }
    public String getEstatus() { return estatus; }
    public void setEstatus(String estatus) { this.estatus = estatus; }
    public double getPenalizacion() { return penalizacion; }
    public void setPenalizacion(double penalizacion) { this.penalizacion = penalizacion; }
    public LocalDateTime getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDateTime fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }
    public LocalDateTime getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDateTime fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }


}