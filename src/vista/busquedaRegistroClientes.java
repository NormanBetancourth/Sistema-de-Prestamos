package vista;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

public class busquedaRegistroClientes extends JFrame {
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel mainConten;
    private JPanel botonera ;

    private String clienteNombre;
    private String clienteId;
    private String clienteProvincia;
    private String clienteDistrito;
    private String clienteCanton;

    private JTextField nameTextField;
    private JTextField idTextField;

    private JComboBox provinciaCombo;
    private JComboBox cantonCombo;
    private JComboBox distritoCombo;

    public void setClienteNombre() {
        this.clienteNombre = nameTextField.getText();
    }

    public void setClienteId() {
        this.clienteId = idTextField.getText();
    }

    public void setClienteProvincia() {
        this.clienteProvincia = (String) provinciaCombo.getSelectedItem();
    }

    public void setClienteDistrito() {
        this.clienteDistrito = (String) distritoCombo.getSelectedItem();
    }

    public void setClienteCanton() {
        this.clienteCanton = (String) cantonCombo.getSelectedItem();
    }

    public JPanel getMainConten() {
        return mainConten;
    }

    public String getClienteNombre() {
        setClienteNombre();
        return clienteNombre;
    }

    public String getClienteId() {
        setClienteId();
        return clienteId;
    }

    public String getClienteProvincia() {
        setClienteProvincia();
        return clienteProvincia;
    }

    public String getClienteDistrito() {
        setClienteDistrito();
        return clienteDistrito;
    }

    public String getClienteCanton() {
        setClienteCanton();
        return clienteCanton;
    }

    public busquedaRegistroClientes() throws HeadlessException {
        mainPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();
        mainConten = new JPanel();
        botonera = new JPanel();
        this.setSize(new Dimension(800,700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        clienteNombre = "";
        clienteId= "";
        clienteProvincia="";
        clienteDistrito="";
        clienteCanton="";

        nameTextField = new JTextField();
        idTextField = new JTextField();

        provinciaCombo = new JComboBox();
        cantonCombo= new JComboBox();
        distritoCombo= new JComboBox();


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
        label.setBorder(new EmptyBorder(4,0,0,0));


        northPanel.setPreferredSize(new Dimension(northPanel.getWidth(),40));
        northPanel.setBackground(Color.decode("#081F62"));
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(label);


        botonera = new JPanel();
        botonera.setLayout(new FlowLayout(FlowLayout.CENTER));
        botonera.setPreferredSize(new Dimension(800,40));
        botonera.setBackground(Color.decode("#E7EAF0"));
        botonera.add(ButtonFactory("Inicio", "0",e));
        botonera.add(ButtonFactory("Agregar Cliente", "1-1",e));//Todo Mapa y combo  boxes
        botonera.add(ButtonFactory("Buscar Cliente", "1-2",e));//TODO 1)opcion de pagar, 2)ver prestamos, 3) ver pagos de 1 prestamo, 4) ver todos los pagos del todos los prestamos
        botonera.add(ButtonFactory("Buscar Prestamo", "1-3",e));
        botonera.add(ButtonFactory("Listado de Clientes", "1-4",e));
        botonera.add(ButtonFactory("Listado de Prestamos", "1-5",e));


        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(botonera, BorderLayout.NORTH);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        mainPanel.setBackground(Color.white);
        this.add(mainPanel, BorderLayout.CENTER);
        southPanel.setPreferredSize(new Dimension(100,20));
        southPanel.setBackground(Color.decode("#081F62"));

        setContentAgregarCliente(e);

        this.add(southPanel, BorderLayout.SOUTH);
        this.setVisible(true);

    }
    private void setContentAgregarCliente(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        //aux
        JPanel auxPanel = new JPanel();
        JLabel labelAux = new JLabel("Nombre: ");

        //formulario
        labelAux.setBorder(new EmptyBorder(0,190,0,0));
        JButton btnAux = ButtonFactory("Guardar", "guardar", e);
        auxPanel.setLayout(new GridLayout(2,2, 50,10));
        auxPanel.add(labelAux);
        auxPanel.add(nameTextField);
        labelAux = new JLabel("Cedula: ");
        labelAux.setBorder(new EmptyBorder(0,190,0,0));
        auxPanel.add(labelAux);
        idTextField.setPreferredSize(new Dimension(150,20));
        auxPanel.add(idTextField);

        auxPanel.setBorder(new EmptyBorder(20,20,20,200));

        mainConten.add(auxPanel, BorderLayout.NORTH);
        mainConten.setBackground(Color.WHITE);
        auxPanel.setBackground(Color.WHITE);

        //mapa, combo boxes y otros botones
        auxPanel = new JPanel();
        auxPanel.setLayout(new BorderLayout());

        JPanel comboButtonPanel = new JPanel();
        comboButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        comboButtonPanel.add(new JLabel("Provincia: "));
        comboButtonPanel.add(provinciaCombo);
        comboButtonPanel.add(new JLabel("Canton: "));
        comboButtonPanel.add(cantonCombo);
        comboButtonPanel.add(new JLabel("Distrito"));
        comboButtonPanel.add(distritoCombo);

        ImageIcon saveImg = new ImageIcon("src/vista/images/floppy-disk_1f4be.png");

        JButton btnGuardar= ButtonFactory("", "guardar-btn",e);

        btnGuardar.setIcon(saveImg);
        btnGuardar.setPreferredSize(new Dimension(60,39));
        btnGuardar.setBackground(Color.decode("#E7EAF0"));
        btnGuardar.setBorder(null);
        comboButtonPanel.add(btnGuardar);
        comboButtonPanel.setBorder(new EmptyBorder(10,0,10,0));
        comboButtonPanel.setBackground(Color.decode("#E7EAF0"));

        auxPanel.add(comboButtonPanel, BorderLayout.NORTH);
        mainConten.add(auxPanel,BorderLayout.CENTER);

        //mapa
        JLabel mapConteiner = new JLabel();
        mapConteiner.setLayout(new BorderLayout());
        //Todo hacer  el mapa
        mapConteiner.setBackground(Color.GREEN);
        mapConteiner.setOpaque(true);

        //rigth column
        //TODO Agregar ToolTipText
        JButton prestamo = ButtonFactory("","pretamo-btn",e);
        saveImg = new ImageIcon("src/vista/images/icons8-money-48.png");

        prestamo.setIcon(saveImg);

        JPanel panelPrestamo = new JPanel();
        panelPrestamo.setBackground(Color.white);
        panelPrestamo.setPreferredSize(new Dimension(100,30));
        panelPrestamo.setLayout(null);
        JLabel prestamoLabel = new JLabel("Nuevo prestamo");
        panelPrestamo.add(prestamoLabel);
        prestamoLabel.setBounds(5,15, 100,30);

        panelPrestamo.add(prestamo);
        prestamo.setBounds(17,50,60,40);
        prestamo.setBorder(null);
        auxPanel.add(panelPrestamo, BorderLayout.EAST);

        mapConteiner.add(new JLabel("Mapa en proceso"));
        auxPanel.add(mapConteiner, BorderLayout.CENTER);




        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.setVisible(true);


    }
    private void setContentBuscarCliente(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        mainConten.setBackground(Color.GREEN);
        mainPanel.add(mainConten);
        this.setVisible(true);
    }

    public void mainContentHandler(int code, ActionListener e){
        switch (code){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:

                break;

        }
    }


}
