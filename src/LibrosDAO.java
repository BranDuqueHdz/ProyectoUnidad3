import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibrosDAO {
    //control simple de concurrencia a nivel de clase para producción
    private static final Object stockLock = new Object();

    public List<Libro> getAll() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libro";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Libro l = new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("categoria"),
                        rs.getInt("num_hojas"),
                        rs.getInt("stock")
                );
                lista.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int getStock(int idLibro) {
        String sql = "SELECT stock FROM libro WHERE id_libro = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idLibro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("stock");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public boolean reducirStock(int idLibro) {
        //evita que múltiples hilos reduzcan/aumenten el stock al mismo tiempo, elimina las inconsistencias
        synchronized (stockLock) {
            int stock = getStock(idLibro);
            if (stock > 0) {
                String sql = "UPDATE libro SET stock = stock - 1 WHERE id_libro = ?";
                try (Connection c = db.getConnection();
                     PreparedStatement ps = c.prepareStatement(sql)) {
                    ps.setInt(1, idLibro);
                    int r = ps.executeUpdate();
                    return r > 0;
                } catch (SQLException e) { e.printStackTrace(); }
            }
            return false;
        }
    }

    public boolean aumentarStock(int idLibro) {
        //asegura que un solo hilo pueda aumentar el stock a la vez
        synchronized (stockLock) {
            String sql = "UPDATE libro SET stock = stock + 1 WHERE id_libro = ?";
            try (Connection c = db.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, idLibro);
                int r = ps.executeUpdate();
                return r > 0;
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return false;
    }
}
