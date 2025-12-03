package bd;

import clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public static Usuario login(String correo, String contrasena) {
        Usuario u = null;
        Connection conn = Conexion.conectar();

        try {
            String sql = "SELECT * FROM usuarios WHERE correo=? AND contrasena=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("rol")
                );
            }

            conn.close();

        } catch (Exception ex) {
            System.out.println("Error en login: " + ex.getMessage());
        }

        return u;
    }
}
