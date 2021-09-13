package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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

    public void addComponents(){
        JLabel label =  new JLabel("Búsqueda y registro de clientes");
        label.setForeground(Color.white);
        label.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        label.setBorder(new EmptyBorder(9,0,0,0));


        northPanel.setPreferredSize(new Dimension(700,50));
        northPanel.setBackground(Color.decode("#081F62"));
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(label);


        JPanel botonera = new JPanel();
        botonera.setLayout(new FlowLayout(FlowLayout.LEFT));
        botonera.setPreferredSize(new Dimension(800,36));
        botonera.setBackground(Color.decode("#E7EAF0"));
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
