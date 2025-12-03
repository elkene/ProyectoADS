package clases;

public class Solicitud {

    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String categoria;
    private String estado;
    private int usuarioId;

    // Constructor al cargar desde BD
    public Solicitud(int id, String titulo, String descripcion, String fecha,
                     String categoria, String estado, int usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.categoria = categoria;
        this.estado = estado;
        this.usuarioId = usuarioId;
    }

    // Constructor para NUEVA solicitud
    public Solicitud(String titulo, String descripcion, String fecha,
                     String categoria, int usuarioId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.categoria = categoria;
        this.estado = "Pendiente";
        this.usuarioId = usuarioId;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getFecha() { return fecha; }
    public String getCategoria() { return categoria; }
    public String getEstado() { return estado; }
    public int getUsuarioId() { return usuarioId; }

    public void setEstado(String estado) { this.estado = estado; }
}
