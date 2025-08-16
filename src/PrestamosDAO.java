import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PrestamosDAO {
    public int crearPrestamo(int idUsuario, int idLibro, int tiempoDias) {
        String sql = "INSERT INTO prestamo (id_usuario, id_libro, tiempo, estatus) VALUES (?, ?, ?, 'activo')";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idLibro);
            ps.setInt(3, tiempoDias);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public boolean marcarDevuelto(int idPrestamo, int diasAtraso) {
        double penalizacion = 0;
        if (diasAtraso > 0) {
            penalizacion = diasAtraso * 5.0;
        }

        String sql = "UPDATE prestamo SET estatus = 'devuelto', penalizacion = ?, fecha_devolucion = NOW() WHERE id_prestamo = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDouble(1, penalizacion);
            ps.setInt(2, idPrestamo);
            int r = ps.executeUpdate();
            return r > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public List<Prestamo> getTodos() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamo ORDER BY id_prestamo ASC"; // orden ascendente
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Prestamo p = new Prestamo();
                p.setId(rs.getInt("id_prestamo"));
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setIdLibro(rs.getInt("id_libro"));
                p.setTiempo(rs.getInt("tiempo"));
                p.setEstatus(rs.getString("estatus"));
                p.setPenalizacion(rs.getDouble("penalizacion"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    // Metodo simple para calcular penalizacion si esta atrasado(ejemplo)
    public void aplicarPenalizacion(int idPrestamo, double monto) {
        String sql = "UPDATE prestamo SET penalizacion = penalizacion + ? WHERE id_prestamo = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDouble(1, monto);
            ps.setInt(2, idPrestamo);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}

