package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame implements VistaBuilder {
    private JPanel northPane = new JPanel();
    private JPanel centralPane = new JPanel();
    private JPanel leftPane = new JPanel();
    private JButton btn1 = VistaBuilder.ButtonFactory("Clientes", "1", null);
    private JButton btn2 = VistaBuilder.ButtonFactory("Prestamos", "2", null);
    private JButton btn3 = VistaBuilder.ButtonFactory("Pagos", "3",null);
    private JButton  btn4 = VistaBuilder.ButtonFactory("Reportes", "4",null);

    public HomeFrame(JPanel northPane, JPanel centralPanet, JPanel southPane, JPanel leftPane) throws HeadlessException {
        this.northPane = northPane;
        this.centralPane = centralPanet;
        this.leftPane = leftPane;
    }

    public HomeFrame() throws HeadlessException {

    }

    public void addComponents(ActionListener e){
        ImageIcon img = new ImageIcon("src/vista/images/logo.png");
        this.setIconImage(img.getImage());
        this.setSize(new Dimension(900,600));
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("\nSistema de Prestamos");//titulo de ventana estilizado
        label.setBorder(new EmptyBorder(7,0,0,0));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("TimesRoman", Font.PLAIN , 20));

        northPane.add(label);
        northPane.setPreferredSize(new Dimension(700,50));
        northPane.setBackground(Color.decode("#081F62"));
        centralPane.setBackground(Color.decode("#FFFFFF"));
        centralPane.setLayout(new GridLayout(1,2));
        centralPane.add(new JLabel(new ImageIcon("src/vista/images/p08fr5bx.jpg")));

        leftPane.setLayout(new FlowLayout(FlowLayout.CENTER, 15,40));
        leftPane.setBackground(Color.decode("#E7EAF0"));
        leftPane.setPreferredSize(new Dimension(300,430));
        btn1.setPreferredSize(new Dimension(140,30));
        btn1.setToolTipText("Búsqueda y registro de clientes");
        leftPane.add(btn1);
        btn2.setPreferredSize(new Dimension(140,30));
        btn2.setToolTipText("Listado, consulta y registros de los préstamos");
        leftPane.add(btn2);
        btn3.setPreferredSize(new Dimension(140,30));
        btn3.setToolTipText("Listado y registro de pagos");
        leftPane.add(btn3);
        btn4.setPreferredSize(new Dimension(140,30));
        btn4.setToolTipText("Generar reportes");
        leftPane.add(btn4);

        JPanel jj = new JPanel();
        jj.setPreferredSize(new Dimension(100,20));
        jj.setBackground(Color.decode("#081F62"));//03318C
        this.add(northPane, BorderLayout.NORTH);
        this.add(centralPane, BorderLayout.CENTER);
        this.add(leftPane, BorderLayout.WEST);
        this.add(jj, BorderLayout.SOUTH);

        addListener(e);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addListener(ActionListener e){
        btn1.addActionListener(e);
        btn2.addActionListener(e);
        btn3.addActionListener(e);
        btn4.addActionListener(e);
    }
}
