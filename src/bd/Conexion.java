package bd;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_tickets";
    private static final String USER = "elkene";
    private static final String PASS = "Kenny989807"; // si pusiste contraseña en MySQL, colócala aquí

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✔ Conexión exitosa a MySQL");
        } catch (Exception e) {
            System.out.println("❌ Error al conectar MySQL: " + e.getMessage());
        }
        return conexion;
    }
}
