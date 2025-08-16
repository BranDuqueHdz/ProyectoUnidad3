import javax.swing.*;
        import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Biblioteca Digital");
            frame.setSize(400, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel p = new JPanel();
            JButton bCatalogo = new JButton("Catálogo de Libros");
            bCatalogo.addActionListener(e -> new CatalogoLibros().setVisible(true));
            JButton bArea = new JButton("Área de Usuario");
            bArea.addActionListener(e -> new AreaUsuario().setVisible(true));
            JButton bAdmin = new JButton("Panel Administración");
            bAdmin.addActionListener(e -> new PanelAdmin().setVisible(true));

            p.add(bCatalogo);
            p.add(bArea);
            p.add(bAdmin);

            frame.add(p, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
