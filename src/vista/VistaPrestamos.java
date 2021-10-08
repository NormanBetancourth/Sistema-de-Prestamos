package vista;

import modelo.cliente.Cliente;
import modelo.pago.Pago;
import modelo.prestamo.Prestamo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaPrestamos extends VentanaGestion{
    private JTextField nombreClienteTextField = new JTextField();
    private JTextField idClienteTextField = new JTextField();
    private JTextField montoTextField = new JTextField();
    private JTextField tasaDeInteresTextField = new JTextField();
    private JTextField plazoTextField = new JTextField();
    private JButton boton1;
    private JButton boton2;

    public VistaPrestamos()throws HeadlessException {
       super();
    }

    public void addComponents(ActionListener e){
        ImageIcon img = new ImageIcon("src/vista/images/icons8-money-48.png");
        this.setIconImage(img.getImage());

        JLabel label =  new JLabel("Gestion De Prestamos");
        label.setForeground(Color.white);
        label.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        label.setBorder(new EmptyBorder(4,0,0,0));

        northPanel.setPreferredSize(new Dimension(northPanel.getWidth(),40));
        northPanel.setBackground(Color.decode("#081F62"));
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(label);

        botonera.setLayout(new FlowLayout(FlowLayout.CENTER));
        botonera.setPreferredSize(new Dimension(800,40));
        botonera.setBackground(Color.decode("#E7EAF0"));
        inicioBoton = VistaBuilder.ButtonFactory("Inicio", "2-0",e);
        botonera.add(inicioBoton);
        agregarBoton = VistaBuilder.ButtonFactory("Agregar Prestamo", "2-1",e);
        botonera.add(agregarBoton);
        buscarBoton = VistaBuilder.ButtonFactory("Listado de Prestamos", "2-2",e);
        botonera.add(buscarBoton);
        //listarBoton = VistaBuilder.ButtonFactory("Listado de Prestamos", "2-3",e);
        //botonera.add(listarBoton);


        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(botonera, BorderLayout.NORTH);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        mainPanel.setBackground(Color.white);
        this.add(mainPanel, BorderLayout.CENTER);
        southPanel.setPreferredSize(new Dimension(100,20));
        southPanel.setBackground(Color.decode("#081F62"));

        //mainContentHandler(1, e);

        this.add(southPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void setEditableFieldsFalse(){
        nombreClienteTextField.setEditable(false);
        montoTextField.setEditable(false);
        tasaDeInteresTextField.setEditable(false);
        plazoTextField.setEditable(false);
    }

    public void setEditableFieldsTrue(){
        nombreClienteTextField.setEditable(true);
        montoTextField.setEditable(true);
        tasaDeInteresTextField.setEditable(true);
        plazoTextField.setEditable(true);
        //idPrestamoTextField.setEditable(false);
    }

    public void clearFields(){
        nombreClienteTextField.setText(null);
        idClienteTextField.setText(null);
        montoTextField.setText(null);
        tasaDeInteresTextField.setText(null);
        plazoTextField.setText(null);
    }

    public JTextField getIdClienteTextField(){
        return idClienteTextField;
    }
    public JTextField getNombreClienteTextField(){
        return nombreClienteTextField;
    }
    public JTextField getMontoTextField(){
        return montoTextField;
    }
    public JTextField getTasaDeInteresTextField(){
        return tasaDeInteresTextField;
    }
    public JTextField getPlazoTextField(){
        return plazoTextField;
    }

    public JButton getBoton1() {
        return boton1;
    }

    public JButton getBoton2() {
        return boton2;
    }

    public String getTextoId(){
        return idClienteTextField.getText();
    }
    public String getTextoNombreCliente(){
        return nombreClienteTextField.getText();
    }
    public String getTextoMonto(){
        return montoTextField.getText();
    }
    public String getTextoTasaDeInteres(){
        return tasaDeInteresTextField.getText();
    }
    public String getTextoPlazo(){
        return plazoTextField.getText();
    }

    public void setTextoNombreCliente(String nombreCliente){
        nombreClienteTextField.setText(nombreCliente);
    }

    private void setContentAgregarPrestamo(ActionListener e) {
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());

        JPanel panelInfor = new JPanel(new GridLayout(6, 2, 50, 20));
        panelInfor.setBackground(Color.WHITE);
        panelInfor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 200));

        JLabel idLabel = new JLabel("Cedula del cliente: ");
        idLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idClienteTextField.setPreferredSize(new Dimension(150, 20));
        idClienteTextField.setEditable(true);
        panelInfor.add(idLabel);
        panelInfor.add(idClienteTextField);

        JLabel montoLabel = new JLabel("Monto: ");
        montoLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        montoTextField.setPreferredSize(new Dimension(150, 20));
        montoTextField.setEditable(false);
        panelInfor.add(montoLabel);
        panelInfor.add(montoTextField);

        JLabel tasaDeInteresLabel = new JLabel("Tasa de interes: ");
        tasaDeInteresLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        tasaDeInteresTextField.setPreferredSize(new Dimension(150, 20));
        tasaDeInteresTextField.setEditable(false);
        panelInfor.add(tasaDeInteresLabel);
        panelInfor.add(tasaDeInteresTextField);

        JLabel plazoLabel = new JLabel("Plazo: ");
        plazoLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        plazoTextField.setPreferredSize(new Dimension(150, 20));
        plazoTextField.setEditable(false);
        panelInfor.add(plazoLabel);
        panelInfor.add(plazoTextField);

        JLabel enviarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        boton1 = VistaBuilder.ButtonFactory("Enviar", "2-1-0", e);
        boton1.setPreferredSize(new Dimension(100, 20));
        boton1.setBackground(Color.decode("#DAF7A6"));
        boton1.setBorder(null);
        panelInfor.add(enviarLabel);
        panelInfor.add(boton1);

        JLabel cancelarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        boton2 = VistaBuilder.ButtonFactory("Cancelar", "2-1-1", e);
        boton2.setPreferredSize(new Dimension(100, 20));
        boton2.setBackground(Color.decode("#C27A8F"));
        boton2.setBorder(null);
        boton2.setEnabled(false);
        panelInfor.add(cancelarLabel);
        panelInfor.add(boton2);

        mainConten.add(panelInfor, BorderLayout.NORTH);
        mainConten.setBackground(Color.WHITE);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        mainConten.setBackground(Color.decode("#E7EAF0"));
        mainPanel.add(mainConten, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void setContentListarPrestamo(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());

        //JPanel panelInfor = new JPanel(new GridLayout(3, 2, 50, 20));
        JPanel panelInfor = new JPanel(new GridLayout(3, 2, 50, 20));
        panelInfor.setBackground(Color.WHITE);
        panelInfor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 200));

        JLabel idLabel = new JLabel("Cedula del cliente: ");
        idLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idClienteTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(idLabel);
        panelInfor.add(idClienteTextField);

        //JLabel prestamoIDLabel = new JLabel("Prestamo: ");
        //prestamoIDLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        //idPrestamoTextField.setPreferredSize(new Dimension(150, 20));
        //idPrestamoTextField.setEditable(false);
        //panelInfor.add(prestamoIDLabel);
        //panelInfor.add(idPrestamoTextField);

        JLabel enviarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        JButton prestamoButton = VistaBuilder.ButtonFactory("Enviar", "2-2-0", e);
        prestamoButton.setPreferredSize(new Dimension(100, 20));
        prestamoButton.setBackground(Color.decode("#DAF7A6"));
        prestamoButton.setBorder(null);
        panelInfor.add(enviarLabel);
        panelInfor.add(prestamoButton);

        JLabel cancelarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        boton2 = VistaBuilder.ButtonFactory("Cancelar", "2-2-1", e);
        boton2.setPreferredSize(new Dimension(100, 20));
        boton2.setBackground(Color.decode("#C27A8F"));
        boton2.setBorder(null);
        boton2.setEnabled(true);
        panelInfor.add(cancelarLabel);
        panelInfor.add(boton2);

        mainConten.add(panelInfor, BorderLayout.NORTH);
        mainConten.setBackground(Color.WHITE);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        mainConten.setBackground(Color.decode("#E7EAF0"));
        mainPanel.add(mainConten, BorderLayout.CENTER);

        setVisible(true);
    }

    public void mostrarVentanaInfoPrestamos(Cliente cliente){
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10,20,20,20));
        panel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        panel.setBackground(Color.decode("#081F62"));
        panel.setForeground(Color.WHITE);
        panel.setPreferredSize(new Dimension(400,140));
        panel.setLayout(new GridLayout(3,2));

        JLabel auxLabel = new JLabel("Nombre: ");
        auxLabel.setForeground(Color.WHITE);
        auxLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        panel.add(auxLabel);

        auxLabel = new JLabel(String.valueOf(cliente.getNombre()));
        auxLabel.setForeground(Color.WHITE);
        auxLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        panel.add(auxLabel);

        auxLabel = new JLabel("ID: ");
        auxLabel.setForeground(Color.WHITE);
        auxLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        panel.add(auxLabel);


        auxLabel = new JLabel(String.valueOf(cliente.getId()));
        auxLabel.setForeground(Color.WHITE);
        auxLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        panel.add(auxLabel);

        auxLabel = new JLabel("Direccion: ");
        auxLabel.setForeground(Color.WHITE);
        auxLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        panel.add(auxLabel);

        auxLabel = new JLabel(cliente.getProvincia()+", "+cliente.getCanton()+ ", "+ cliente.getProvincia());
        auxLabel.setForeground(Color.WHITE);
        auxLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        panel.add(auxLabel);

        JPanel panelPrestamos = new JPanel();
        panelPrestamos.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        panelPrestamos.setBackground(Color.WHITE);

        panelPrestamos.setBorder(new EmptyBorder(20,30,20,30));

        panelPrestamos.setLayout(new GridLayout(5,2));
        panelPrestamos.add(new JLabel("Prestamos"));
        panelPrestamos.add(new JLabel(" "));
        if (cliente.tienePrestamos()){
            panelPrestamos.setLayout(new GridLayout(5 * cliente.getNumeroDePrestamos(),2));
            for (Prestamo prestamo: cliente.getListaDePrestamosRaw()){
                panelPrestamos.add(new JLabel("Codigo: "));
                panelPrestamos.add(new JLabel(prestamo.getId()));

                panelPrestamos.add(new JLabel("Monto: "));
                panelPrestamos.add(new JLabel(String.valueOf(prestamo.getMonto())));

                panelPrestamos.add(new JLabel("Fecha: "));
                panelPrestamos.add(new JLabel(prestamo.getFecha()));

                panelPrestamos.add(new JLabel(" "));
                panelPrestamos.add(new JLabel(" "));

            }

        }

        JFrame frame = new JFrame("Informacion del cliente");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,500);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(panelPrestamos);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void mainContentHandler(int code, ActionListener e){
        switch (code) {
            case 1 -> setContentAgregarPrestamo(e);
            case 2 -> setContentListarPrestamo(e);
        }
    }
}
