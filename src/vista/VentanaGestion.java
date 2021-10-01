package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaGestion extends JFrame {
    JPanel botonera = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel mainConten = new JPanel();
    JButton inicioBoton = new JButton();
    JButton agregarBoton = new JButton();
    JButton buscarBoton = new JButton();
    JButton listarBoton = new JButton();

    public VentanaGestion() {
        setSize(new Dimension(900,790));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }

    public JButton getInicioBoton() {
        return inicioBoton;
    }
    public JButton getAgregarBoton() {
        return agregarBoton;
    }
    public JButton getBuscarBoton() {
        return buscarBoton;
    }
    public JButton getListarBoton() {
        return listarBoton;
    }

}
