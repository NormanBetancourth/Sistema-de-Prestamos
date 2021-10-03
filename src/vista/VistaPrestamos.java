package vista;

import modelo.prestamo.ModeloTablaPrestamos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaPrestamos extends VentanaGestion{
    private JTextField nombreClienteTextField = new JTextField();
    private JTextField idClienteTextField = new JTextField();
    private JTextField montoTextField = new JTextField();
    private JTextField tasaDeInteresTextField = new JTextField();
    private JTextField plazoTextField = new JTextField();
    private JTextField idPrestamoTextField = new JTextField();
    private JTable tabla = new JTable();
    private JScrollPane scrollPane = new JScrollPane();

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
        buscarBoton = VistaBuilder.ButtonFactory("Buscar Prestamo", "2-2",e);
        botonera.add(buscarBoton);
        listarBoton = VistaBuilder.ButtonFactory("Listado de Prestamos", "2-3",e);
        botonera.add(listarBoton);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(botonera, BorderLayout.NORTH);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        mainPanel.setBackground(Color.white);
        this.add(mainPanel, BorderLayout.CENTER);
        southPanel.setPreferredSize(new Dimension(100,20));
        southPanel.setBackground(Color.decode("#081F62"));

        mainContentHandler(1, e);

        this.add(southPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void clearFields(){
        nombreClienteTextField.setText(null);
        idClienteTextField.setText(null);
        montoTextField.setText(null);
        tasaDeInteresTextField.setText(null);
        plazoTextField.setText(null);
        idPrestamoTextField.setText(null);
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

    public JTextField getIdPrestamoTextField() {
        return idPrestamoTextField;
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
    public String getTextoPrestamoID(){
        return idPrestamoTextField.getText();
    }


    public void setTextoNombreCliente(String nombreCliente){
        nombreClienteTextField.setText(nombreCliente);
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public void setModeloTabla(ModeloTablaPrestamos modeloTablaPrestamos){
        tabla.setModel(modeloTablaPrestamos);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    private void setContentAgregarPrestamo(ActionListener e) {
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());

        JPanel panelInfor = new JPanel(new GridLayout(5, 2, 50, 20));
        panelInfor.setBackground(Color.WHITE);
        panelInfor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 200));

        JLabel idLabel = new JLabel("Cedula: ");
        idLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idClienteTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(idLabel);
        panelInfor.add(idClienteTextField);

        JLabel montoLabel = new JLabel("Monto: ");
        montoLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        montoTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(montoLabel);
        panelInfor.add(montoTextField);

        JLabel tasaDeInteresLabel = new JLabel("Tasa de interes: ");
        tasaDeInteresLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        tasaDeInteresTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(tasaDeInteresLabel);
        panelInfor.add(tasaDeInteresTextField);

        JLabel plazoLabel = new JLabel("Plazo: ");
        plazoLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        plazoTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(plazoLabel);
        panelInfor.add(plazoTextField);

        JLabel enviarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        JButton prestamoButton = VistaBuilder.ButtonFactory("   Enviar", "2-1-1", e);
        prestamoButton.setPreferredSize(new Dimension(100, 20));
        prestamoButton.setBackground(Color.decode("#DAF7A6"));
        prestamoButton.setBorder(null);
        panelInfor.add(enviarLabel);
        panelInfor.add(prestamoButton);

        mainConten.add(panelInfor, BorderLayout.NORTH);
        mainConten.setBackground(Color.WHITE);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        mainConten.setBackground(Color.decode("#E7EAF0"));
        mainPanel.add(mainConten, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void setContentBuscarPrestamo(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());

        //JPanel panelInfor = new JPanel(new GridLayout(3, 2, 50, 20));
        JPanel panelInfor = new JPanel(new GridLayout(3, 2, 50, 20));
        panelInfor.setBackground(Color.WHITE);
        panelInfor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 200));

        JLabel idLabel = new JLabel("Cedula: ");
        idLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idClienteTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(idLabel);
        panelInfor.add(idClienteTextField);

        JLabel prestamoIDLabel = new JLabel("Prestamo: ");
        prestamoIDLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idPrestamoTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(prestamoIDLabel);
        panelInfor.add(idPrestamoTextField);

        JLabel enviarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        JButton prestamoButton = VistaBuilder.ButtonFactory("Enviar", "2-1-1", e);
        prestamoButton.setPreferredSize(new Dimension(100, 20));
        prestamoButton.setBackground(Color.decode("#DAF7A6"));
        prestamoButton.setBorder(null);
        panelInfor.add(enviarLabel);
        panelInfor.add(prestamoButton);

        mainConten.add(panelInfor, BorderLayout.NORTH);
        mainConten.setBackground(Color.WHITE);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        mainConten.setBackground(Color.decode("#E7EAF0"));
        mainPanel.add(mainConten, BorderLayout.CENTER);

        setVisible(true);
    }

    private void setConetentPaneListarPrestamos(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        //mainConten.setBackground(Color.GREEN);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void mainContentHandler(int code, ActionListener e){
        switch (code) {
            case 1 -> setContentAgregarPrestamo(e);
            case 2 -> setContentBuscarPrestamo(e);
            case 3 -> setConetentPaneListarPrestamos(e);
        }
    }
}
