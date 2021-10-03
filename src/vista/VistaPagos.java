package vista;

import com.sun.tools.xjc.model.Model;
import modelo.pago.ModeloTablaPagos;
import modelo.prestamo.ModeloTablaPrestamos;
import org.glassfish.jaxb.runtime.v2.model.impl.ModelBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

// int numeroDePago, double montoPagado, double interes, double amortizacion

public class VistaPagos extends VentanaGestion{
    private JTextField idTextField = new JTextField();
    private JTextField numeroTextField = new JTextField();
    private JTextField montoPagadoTextField = new JTextField();
    private JTextField tasaDeInteresTextField = new JTextField();
    private JTextField amortizacionTextField = new JTextField();
    private JTextField prestamoTextField = new JTextField();
    private JTextField pagoTextField = new JTextField();
    JButton boton;
    private JTable tabla;
    private JScrollPane scrollPane;

    public VistaPagos()throws HeadlessException {
        super();
    }

    public void addComponents(ActionListener e){
        JLabel label =  new JLabel("Gestion De Pagos");
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
        inicioBoton = VistaBuilder.ButtonFactory("Inicio", "3-0",e);
        botonera.add(inicioBoton);
        agregarBoton = VistaBuilder.ButtonFactory("Agregar Pago", "3-1",e);
        botonera.add(agregarBoton);
        buscarBoton = VistaBuilder.ButtonFactory("Buscar Pago", "3-2",e);
        botonera.add(buscarBoton);
        listarBoton = VistaBuilder.ButtonFactory("Listado de Pagos", "3-3",e);
        botonera.add(listarBoton);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(botonera, BorderLayout.NORTH);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        mainPanel.setBackground(Color.white);
        this.add(mainPanel, BorderLayout.CENTER);
        southPanel.setPreferredSize(new Dimension(100,20));
        southPanel.setBackground(Color.decode("#081F62"));

        mainContentHandler(1,e);

        this.add(southPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void setEditableButtons(){
        idTextField.setEditable(true);
        prestamoTextField.setEditable(false);
        numeroTextField.setEditable(false);
        montoPagadoTextField.setEditable(false);
        tasaDeInteresTextField.setEditable(false);
        amortizacionTextField.setEditable(false);
    }

    public void clearFields(){
        idTextField.setText(null);
        prestamoTextField.setText(null);
        pagoTextField.setText(null);
        numeroTextField.setText(null);
        montoPagadoTextField.setText(null);
        tasaDeInteresTextField.setText(null);
        amortizacionTextField.setText(null);
        tabla.setModel(new DefaultTableModel());
    }

    public JTextField getIdTextField() {
        return idTextField;
    }

    public JTextField getNumeroTextField() {
        return numeroTextField;
    }

    public JTextField getMontoPagadoTextField() {
        return montoPagadoTextField;
    }

    public JTextField getTasaDeInteresTextField() {
        return tasaDeInteresTextField;
    }

    public JTextField getAmortizacionTextField() {
        return amortizacionTextField;
    }

    public JTextField getPrestamoTextField() {
        return prestamoTextField;
    }

    public JTextField getPagoTextField() {
        return pagoTextField;
    }

    public String getTextoId(){
        return idTextField.getText();
    }

    public String getTextoNumero(){
        return numeroTextField.getText();
    }

    public String getTextoMontoPagado(){
        return montoPagadoTextField.getText();
    }
    public String getTextoTasaDeInteres(){
        return tasaDeInteresTextField.getText();
    }
    public String getTextoAmortizacion(){
        return amortizacionTextField.getText();
    }

    public String getTextoPrestamo(){
        return prestamoTextField.getText();
    }

    public String getTextoPago(){
        return getPagoTextField().getText();
    }

    public void setTextoId(String id){
        idTextField.setText(id);
    }

    public void setTextoNumero(String numero){
        numeroTextField.setText(numero);
    }

    public void setTextoMonto(String monto){
        montoPagadoTextField.setText(monto);
    }

    public void setTextoInteres(String interes){
        tasaDeInteresTextField.setText(interes);
    }

    public void setTextoAmortizacion(String amortizacion){
        amortizacionTextField.setText(amortizacion);
    }

    public void setTextoPrestamo(String id){
        prestamoTextField.setText(id);
    }

    public void setPagoTextField(String id){
        pagoTextField.setText(id);
    }

    public JButton getBoton() {
        return boton;
    }

    public void setBoton(JButton boton) {
        this.boton = boton;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public void setModeloTablaPrestamos(ModeloTablaPrestamos modeloTablaPrestamos){
        tabla.setModel(modeloTablaPrestamos);
    }

    public void setModeloTabla(ModeloTablaPagos modeloTablaPagos){
        tabla.setModel(modeloTablaPagos);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    private void setContentAgregarPago(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());

        JPanel panelInfor = new JPanel(new GridLayout(7, 2, 50, 20));
        panelInfor.setBackground(Color.WHITE);
        panelInfor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 200));

        JLabel idLabel = new JLabel("Cedula: ");
        idLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(idLabel);
        panelInfor.add(idTextField);

        JLabel prestamoLabel = new JLabel("Prestamo: ");
        prestamoLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        prestamoTextField.setPreferredSize(new Dimension(150, 20));
        prestamoTextField.setEditable(false);
        panelInfor.add(prestamoLabel);
        panelInfor.add(prestamoTextField);

        JLabel numeroLabel = new JLabel("Numero de pago: ");
        numeroLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        numeroTextField.setPreferredSize(new Dimension(150, 20));
        numeroTextField.setEditable(false);
        panelInfor.add(numeroLabel);
        panelInfor.add(numeroTextField);


        JLabel tasaDeInteresLabel = new JLabel("Tasa de interes: ");
        tasaDeInteresLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        tasaDeInteresTextField.setPreferredSize(new Dimension(150, 20));
        tasaDeInteresTextField.setEditable(false);
        panelInfor.add(tasaDeInteresLabel);
        panelInfor.add(tasaDeInteresTextField);

        JLabel amortizacionLabel = new JLabel("Amortizacion: ");
        amortizacionLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        amortizacionTextField.setPreferredSize(new Dimension(150, 20));
        amortizacionTextField.setEditable(false);
        panelInfor.add(amortizacionLabel);
        panelInfor.add(amortizacionTextField);

        JLabel montoLabel = new JLabel("Monto: ");
        montoLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        montoPagadoTextField.setPreferredSize(new Dimension(150, 20));
        montoPagadoTextField.setEditable(false);
        panelInfor.add(montoLabel);
        panelInfor.add(montoPagadoTextField);

        JLabel enviarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        boton = VistaBuilder.ButtonFactory("Enviar", "3-1-0", e);
        boton.setPreferredSize(new Dimension(100, 20));
        boton.setBackground(Color.decode("#DAF7A6"));
        boton.setBorder(null);
        panelInfor.add(enviarLabel);
        panelInfor.add(boton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,100,100,100));
        tabla = new JTable();
        tabla.setModel(new DefaultTableModel());
        tabla.addMouseListener((MouseListener) e);
        //tabla.setBorder(BorderFactory.createEmptyBorder(50,20,50,20));
        scrollPane = new JScrollPane(tabla);
        panel.add(scrollPane, BorderLayout.NORTH);

        mainConten.setBackground(Color.WHITE);
        mainConten.add(panelInfor, BorderLayout.NORTH);
        mainConten.add(panel, BorderLayout.CENTER);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        mainConten.setBackground(Color.decode("#E7EAF0"));
        mainPanel.add(mainConten, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void setContentBuscarPago(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());

        JPanel panelInfor = new JPanel(new GridLayout(4, 2, 50, 20));
        panelInfor.setBackground(Color.WHITE);
        panelInfor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 200));

        JLabel idLabel = new JLabel("Cedula: ");
        idLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(idLabel);
        panelInfor.add(idTextField);

        JLabel prestamoLabel = new JLabel("Prestamo: ");
        prestamoLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        prestamoTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(prestamoLabel);
        panelInfor.add(prestamoTextField);

        JLabel pagoLabel = new JLabel("Pago: ");
        pagoLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        pagoTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(pagoLabel);
        panelInfor.add(pagoTextField);

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

    private void setContentListarPagos(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        mainConten.setBackground(Color.GREEN);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void mainContentHandler(int code, ActionListener e){
        switch (code) {
            case 1 -> setContentAgregarPago(e);
            case 2 -> setContentBuscarPago(e);
            case 3 -> setContentListarPagos(e);
        }
    }

}
