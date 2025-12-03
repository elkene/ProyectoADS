package clases;

public class SesionUsuario {

    private static int idUsuario;
    private static String nombre;
    private static String correo;
    private static String rol;

    // Método para iniciar sesión guardando los datos del usuario logeado
    public static void iniciar(int id, String nom, String corr, String r) {
        idUsuario = id;
        nombre = nom;
        correo = corr;
        rol = r;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static String getNombre() {
        return nombre;
    }

    public static String getCorreo() {
        return correo;
    }

    public static String getRol() {
        return rol;
    }

    // Método para cerrar sesión
    public static void cerrarSesion() {
        idUsuario = 0;
        nombre = null;
        correo = null;
        rol = null;
    }
}
