package bd;

import clases.Solicitud;
import java.sql.*;
import java.util.ArrayList;

public class SolicitudDAO {

    public static boolean crearSolicitud(Solicitud sol) {
        Connection conn = Conexion.conectar();
        if (conn == null) return false;

        try {
            String sql = "INSERT INTO solicitudes (titulo, descripcion, fecha, categoria, estado, usuario_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sol.getTitulo());
            ps.setString(2, sol.getDescripcion());
            ps.setString(3, sol.getFecha());
            ps.setString(4, sol.getCategoria());
            ps.setString(5, sol.getEstado());
            ps.setInt(6, sol.getUsuarioId());

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al guardar solicitud: " + e.getMessage());
            return false;
        }
    }

    // Obtener solicitudes del usuario (Maestro)
    public static ArrayList<Solicitud> obtenerSolicitudesDeUsuario(int idUsuario) {
        ArrayList<Solicitud> lista = new ArrayList<>();
        Connection conn = Conexion.conectar();
        if (conn == null) return lista;

        try {
            String sql = "SELECT * FROM solicitudes WHERE usuario_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Solicitud(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("fecha"),
                        rs.getString("categoria"),
                        rs.getString("estado"),
                        rs.getInt("usuario_id")
                ));
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("❌ Error al cargar solicitudes: " + e.getMessage());
        }

        return lista;
    }

    // Obtener TODAS las solicitudes (Soporte)
    public static ArrayList<Solicitud> obtenerTodasSolicitudes() {
        ArrayList<Solicitud> lista = new ArrayList<>();
        Connection conn = Conexion.conectar();
        if (conn == null) return lista;

        try {
            String sql = "SELECT * FROM solicitudes";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Solicitud(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("fecha"),
                        rs.getString("categoria"),
                        rs.getString("estado"),
                        rs.getInt("usuario_id")
                ));
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener todas solicitudes: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar estado (Soporte)
    public static boolean actualizarEstado(int id, String nuevoEstado) {
        Connection conn = Conexion.conectar();
        if (conn == null) return false;

        try {
            String sql = "UPDATE solicitudes SET estado=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setInt(2, id);
            ps.executeUpdate();

            conn.close();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }

    public static boolean asignarSolicitudASoporte(int idSolicitud, int idSoporte) {
        String sql = "UPDATE solicitudes SET id_soporte_asignado = ? WHERE id = ? AND (id_soporte_asignado IS NULL OR id_soporte_asignado = 0)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idSoporte);
            pst.setInt(2, idSolicitud);

            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
