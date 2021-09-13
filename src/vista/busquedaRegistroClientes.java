package vista;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class busquedaRegistroClientes extends JFrame {
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel southPanel;

    public busquedaRegistroClientes() throws HeadlessException {
        mainPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();
        this.setSize(new Dimension(800,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

    }
    private static JButton ButtonFactory(String text , String id, ActionListener e) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setActionCommand(id);
        button.addActionListener(e);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }

    public void addComponents(ActionListener e){
        JLabel label =  new JLabel("Búsqueda y registro de clientes");
        label.setForeground(Color.white);
        label.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        label.setBorder(new EmptyBorder(9,0,0,0));


        northPanel.setPreferredSize(new Dimension(northPanel.getWidth(),50));
        northPanel.setBackground(Color.decode("#081F62"));
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(label);


        JPanel botonera = new JPanel();
        botonera.setLayout(new FlowLayout(FlowLayout.CENTER));
        botonera.setPreferredSize(new Dimension(800,40));
        botonera.setBackground(Color.decode("#E7EAF0"));
        botonera.add(ButtonFactory("Agregar Cliente", "1-1",e));//Todo Mapa y combo  boxes
        botonera.add(ButtonFactory("Buscar Cliente", "1-2",e));//TODO 1)opcion de pagar, 2)ver prestamos, 3) ver pagos de 1 prestamo, 4) ver todos los pagos del todos los prestamos
        botonera.add(ButtonFactory("Buscar Prestamo", "1-3",e));
        botonera.add(ButtonFactory("Listado de Clientes", "1-4",e));
        botonera.add(ButtonFactory("Listado de Prestamos", "1-5",e));


        mainPanel.add(botonera, BorderLayout.NORTH);
        this.add(northPanel, BorderLayout.NORTH);
        mainPanel.setBackground(Color.white);
        this.add(mainPanel, BorderLayout.CENTER);
        southPanel.setPreferredSize(new Dimension(100,20));
        southPanel.setBackground(Color.decode("#081F62"));
        this.add(southPanel, BorderLayout.SOUTH);
        this.setVisible(true);

    }
}
