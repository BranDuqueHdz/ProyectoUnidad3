import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CatalogoLibros extends JFrame {
    private JTable table;
    private LibrosDAO dao = new LibrosDAO();

    public CatalogoLibros() {
        setTitle("Catálogo de Libros");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        loadData();
    }

    private void initUI() {
        table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        JPanel top = new JPanel();
        JTextField tSearch = new JTextField(20);
        JButton bSearch = new JButton("Buscar");
        bSearch.addActionListener(e -> filter(tSearch.getText()));
        top.add(new JLabel("Buscar:"));
        top.add(tSearch);
        top.add(bSearch);
        add(top, BorderLayout.NORTH);
    }

    private void loadData() {
        List<Libro> lista = dao.getAll();
        String[] cols = {"ID", "Título", "Autor", "Categoría", "No. Hojas", "Stock"};
        DefaultTableModel m = new DefaultTableModel(cols, 0);
        for (Libro l : lista) {
            m.addRow(new Object[]{l.getId(), l.getTitulo(), l.getAutor(), l.getCategoria(), l.getNumHojas(), l.getStock()});
        }
        table.setModel(m);
    }

    private void filter(String q) {
        // filtro simple en memoria
        List<Libro> lista = dao.getAll();
        String[] cols = {"ID", "Título", "Autor", "Categoría", "No. Hojas", "Stock"};
        DefaultTableModel m = new DefaultTableModel(cols, 0);
        for (Libro l : lista) {
            if (q == null || q.trim().isEmpty() || l.getTitulo().toLowerCase().contains(q.toLowerCase())
                    || l.getAutor().toLowerCase().contains(q.toLowerCase())
                    || (l.getCategoria() != null && l.getCategoria().toLowerCase().contains(q.toLowerCase()))) {
                m.addRow(new Object[]{l.getId(), l.getTitulo(), l.getAutor(), l.getCategoria(), l.getNumHojas(), l.getStock()});
            }
        }
        table.setModel(m);
    }
}