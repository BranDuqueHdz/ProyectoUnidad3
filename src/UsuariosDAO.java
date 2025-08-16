import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    public List<Usuario> getAll() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );
                lista.add(u);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}