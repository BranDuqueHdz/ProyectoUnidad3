import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelAdmin extends JFrame {
    private JTable table;
    private PrestamosDAO prestamosDAO = new PrestamosDAO();

    public PanelAdmin() {
        setTitle("Panel de Administración");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
        loadData();
    }

    private void initUI() {
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel top = new JPanel();
        JButton bRefrescar = new JButton("Refrescar");
        bRefrescar.addActionListener(e -> loadData());
        top.add(bRefrescar);
        add(top, BorderLayout.NORTH);
    }

    private void loadData() {
        List<Prestamo> lista = prestamosDAO.getTodos();
        String[] cols = {"ID", "Usuario ID", "Libro ID", "Tiempo (días)", "Estatus", "Penalización"};
        DefaultTableModel m = new DefaultTableModel(cols, 0);
        for (Prestamo p : lista) {
            m.addRow(new Object[]{p.getId(), p.getIdUsuario(), p.getIdLibro(), p.getTiempo(), p.getEstatus(), p.getPenalizacion()});
        }
        table.setModel(m);
    }


}