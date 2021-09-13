package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class homeFrame extends JFrame {
    private JPanel northPane;
    private JPanel centralPane;
    private JPanel leftPane;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;

    public homeFrame(JPanel northPane, JPanel centralPanet, JPanel southPane, JPanel leftPane) throws HeadlessException {
        this.northPane = northPane;
        this.centralPane = centralPanet;
        this.leftPane = leftPane;
    }

    public homeFrame() throws HeadlessException {
        northPane = new JPanel();
        centralPane = new JPanel();
        leftPane = new JPanel();
        btn1 = new JButton("Busqueda y Registro");
        btn2 = new JButton("Consulta y Registros");
        btn3 = new JButton("Listado de Pagos");
        btn4 = new JButton("Reportes");

    }

    public void addComponents(){
        this.setSize(new Dimension(800,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("\nSistema de Prestamos");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
        northPane.add(label);
        northPane.setPreferredSize(new Dimension(700,50));
        northPane.setBackground(Color.decode("#081F62"));
        centralPane.setBackground(Color.decode("#FFFFFF"));
        centralPane.setLayout(new GridLayout(1,2));
        centralPane.add(new JLabel(new ImageIcon("src/vista/p08fr5bx.jpg")));

        leftPane.setLayout(new FlowLayout(FlowLayout.CENTER, 80,40));
        leftPane.setBackground(Color.decode("#E7EAF0"));
        leftPane.setPreferredSize(new Dimension(300,430));
        btn1.setActionCommand("1");//Todo Agregar Hoovers
        btn1.setPreferredSize(new Dimension(140,30));
        leftPane.add(btn1);
        btn2.setActionCommand("2");
        btn2.setPreferredSize(new Dimension(140,30));
        leftPane.add(btn2);
        btn3.setActionCommand("3");
        btn3.setPreferredSize(new Dimension(140,30));
        leftPane.add(btn3);
        btn4.setActionCommand("4");
        btn4.setPreferredSize(new Dimension(140,30));
        leftPane.add(btn4);

        JPanel jj = new JPanel();
        jj.setPreferredSize(new Dimension(100,20));
        jj.setBackground(Color.decode("#081F62"));//03318C
        this.add(northPane, BorderLayout.NORTH);
        this.add(centralPane, BorderLayout.CENTER);
        this.add(leftPane, BorderLayout.WEST);
        this.add(jj, BorderLayout.SOUTH);
        this.setVisible(true);

    }

    public void addListener(ActionListener e){
        btn1.addActionListener(e);
        btn2.addActionListener(e);
        btn3.addActionListener(e);
        btn4.addActionListener(e);
    }
}
