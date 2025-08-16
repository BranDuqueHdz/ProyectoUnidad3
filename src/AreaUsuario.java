
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AreaUsuario extends JFrame {
    private JComboBox<Usuario> cbUsuarios;
    private JTextField tfIdLibro;
    private LibrosDAO librosDAO = new LibrosDAO();
    private PrestamosDAO prestamosDAO = new PrestamosDAO();

    public AreaUsuario() {
        setTitle("Área de Usuario");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        JPanel p = new JPanel(new GridLayout(4,2,5,5));

        p.add(new JLabel("Usuario:"));
        cbUsuarios = new JComboBox<>();
        loadUsuarios();
        p.add(cbUsuarios);

        p.add(new JLabel("ID Libro:"));
        tfIdLibro = new JTextField();
        p.add(tfIdLibro);

        p.add(new JLabel("Tiempo (días):"));
        JTextField tfTiempo = new JTextField("5");
        p.add(tfTiempo);

        JButton bSolicitar = new JButton("Solicitar Préstamo");
        bSolicitar.addActionListener(e -> {
            Usuario u = (Usuario) cbUsuarios.getSelectedItem();
            if (u == null) { JOptionPane.showMessageDialog(this, "Selecciona un usuario"); return; }
            int idLibro = Integer.parseInt(tfIdLibro.getText());
            int tiempo = Integer.parseInt(tfTiempo.getText());
            new Thread(new LoanWorker(u.getId(), idLibro, tiempo)).start();
        });

        JButton bDevolver = new JButton("Devolver (ID Préstamo)");
        bDevolver.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "ID de préstamo a devolver:");
            if (id != null) {
                try {
                    int idPrestamo = Integer.parseInt(id);

                    String atrasoStr = JOptionPane.showInputDialog(this, "¿Cuántos días de atraso tiene el usuario?\n(0 si devolvió a tiempo)");
                    if (atrasoStr != null) {
                        int diasAtraso = Integer.parseInt(atrasoStr);
                        if (diasAtraso < 0) diasAtraso = 0;

                        boolean ok = prestamosDAO.marcarDevuelto(idPrestamo, diasAtraso);
                        if (ok) {
                            JOptionPane.showMessageDialog(this, "Préstamo marcado como devuelto. Penalización: $" + (diasAtraso * 5));
                            // Aquí podrías aumentar stock si quieres
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al devolver.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor ingresa un número válido.");
                }
            }
        });

        add(p, BorderLayout.CENTER);
        JPanel bottom = new JPanel();
        bottom.add(bSolicitar);
        bottom.add(bDevolver);
        add(bottom, BorderLayout.SOUTH);
    }


    private void loadUsuarios() {
        UsuariosDAO udao = new UsuariosDAO();
        List<Usuario> lista = udao.getAll();
        DefaultComboBoxModel<Usuario> m = new DefaultComboBoxModel<>();
        for (Usuario u : lista) m.addElement(u);
        cbUsuarios.setModel(m);
        cbUsuarios.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Usuario) {
                    Usuario uu = (Usuario) value;
                    setText(uu.getId() + " - " + uu.getNombre() + " " + uu.getApellido());
                }
                return this;
            }
        });
    }

    // Worker que crea un préstamo y reduce stock en forma sincronizada
    private class LoanWorker implements Runnable {
        private int idUsuario, idLibro, tiempo;
        public LoanWorker(int idUsuario, int idLibro, int tiempo) { this.idUsuario = idUsuario; this.idLibro = idLibro; this.tiempo = tiempo; }

        public void run() {
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(AreaUsuario.this, "Solicitando préstamo..."));
            boolean reservado = librosDAO.reducirStock(idLibro);
            if (!reservado) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(AreaUsuario.this, "No hay stock disponible para ese libro."));
                return;
            }
            int idPrestamo = prestamosDAO.crearPrestamo(idUsuario, idLibro, tiempo);
            if (idPrestamo < 0) {
                // revertir stock
                librosDAO.aumentarStock(idLibro);
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(AreaUsuario.this, "Error al crear préstamo."));
                return;
            }

            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(AreaUsuario.this, "Préstamo creado. ID: " + idPrestamo));


        }
    }
}
