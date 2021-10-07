package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class VistaReportes extends VentanaGestion{
    private JTextField idTextField = new JTextField();
    private JTextField prestamoTextField = new JTextField();
    private JTextField pagoTextField = new JTextField();
    private JButton boton;
    private JButton boton2;
    private JButton boton3;
    private JTable tabla;
    private JScrollPane scrollPane;

    public VistaReportes()throws HeadlessException {
        super();
    }

    public void addComponents(ActionListener e){
        JLabel label =  new JLabel("Gestion De Reportes");
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
        inicioBoton = VistaBuilder.ButtonFactory("Inicio", "4-0",e);
        botonera.add(inicioBoton);
        agregarBoton = VistaBuilder.ButtonFactory("Reporte De Clientes", "4-1",e);
        botonera.add(agregarBoton);
        buscarBoton = VistaBuilder.ButtonFactory("Reporte de Prestamo", "4-2",e);
        botonera.add(buscarBoton);
        buscarBoton = VistaBuilder.ButtonFactory("Reporte de Pagos", "4-3",e);
        botonera.add(buscarBoton);

        mainContentHandler(1,null);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(botonera, BorderLayout.NORTH);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        mainPanel.setBackground(Color.white);
        this.add(mainPanel, BorderLayout.CENTER);
        southPanel.setPreferredSize(new Dimension(100,20));
        southPanel.setBackground(Color.decode("#081F62"));

        this.add(southPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void clearFields(){
        boton.setText("Enviar");
        idTextField.setText(null);
        prestamoTextField.setText(null);
        pagoTextField.setText(null);
        quitarSeleccion();
        //tabla.setModel(new DefaultTableModel());
    }

    public void setModeloTablaPrestamos(AbstractTableModel modeloTablaPrestamos){
        tabla.setModel(modeloTablaPrestamos);
    }

    public JTextField getIdTextField() {
        return idTextField;
    }

    public void setIdTextField(JTextField idTextField) {
        this.idTextField = idTextField;
    }

    public JTextField getPrestamoTextField() {
        return prestamoTextField;
    }

    public void setPrestamoTextField(JTextField prestamoTextField) {
        this.prestamoTextField = prestamoTextField;
    }

    public JTextField getPagoTextField() {
        return pagoTextField;
    }

    public void setPagoTextField(JTextField pagoTextField) {
        this.pagoTextField = pagoTextField;
    }

    public JButton getBoton() {
        return boton;
    }

    public void setBoton(JButton boton) {
        this.boton = boton;
    }

    public JButton getBoton2() {
        return boton2;
    }

    public void setBoton2(JButton boton2) {
        this.boton2 = boton2;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public String getTextoId(){
        return idTextField.getText();
    }

    public void setTextoId(String id){
        idTextField.setText(id);
    }
    public void setTextoPrestamo(String id){
        prestamoTextField.setText(id);
    }
    public void setPagoTextField(String id){
        pagoTextField.setText(id);
    }

    public String getTextoNombre(){return idTextField.getText();}
    public String getTextoPrestamo(){
        return prestamoTextField.getText();
    }
    public String getTextoPago(){
        return getPagoTextField().getText();
    }

    public void setModeloTabla(AbstractTableModel modeloTabla){
        tabla.setModel(modeloTabla);
    }

    public void seleccionarIntervalo(int index1, int index2){
        tabla.setRowSelectionInterval(index1, index2);
    }

    public void quitarSeleccion(){
        tabla.getSelectionModel().clearSelection();
    }

    private void setContentGeneral(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());

        JPanel panelInfor = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInfor.setBackground(Color.WHITE);
        panelInfor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        boton3 = VistaBuilder.ButtonFactory("Generar reporte", "4-1-0", e);
        boton3.setPreferredSize(new Dimension(150, 25));
        boton3.setBackground(Color.decode("#DAF7A6"));
        boton3.setBorder(null);
        panelInfor.add(boton3);

        mainConten.setBackground(Color.WHITE);
        mainConten.add(panelInfor, BorderLayout.NORTH);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        mainConten.setBackground(Color.decode("#E7EAF0"));
        mainPanel.add(mainConten, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void setContentReportePrestamo(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());

        JPanel panelInfor = new JPanel(new GridLayout(3, 2, 50, 20));
        panelInfor.setBackground(Color.WHITE);
        panelInfor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 200));

        JLabel idLabel = new JLabel("Cedula: ");
        idLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idTextField.setPreferredSize(new Dimension(150, 20));
        idTextField.setActionCommand("");
        panelInfor.add(idLabel);
        panelInfor.add(idTextField);

        JLabel enviarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        boton = VistaBuilder.ButtonFactory("Enviar", "4-1-1", e);
        boton.setPreferredSize(new Dimension(100, 20));
        boton.setBackground(Color.decode("#DAF7A6"));
        boton.setBorder(null);
        panelInfor.add(enviarLabel);
        panelInfor.add(boton);

        JLabel cancelarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        boton2 = VistaBuilder.ButtonFactory("Cancelar", "Cancelacion", e);
        boton2.setPreferredSize(new Dimension(100, 20));
        boton2.setBackground(Color.decode("#C27A8F"));
        boton2.setBorder(null);
        boton2.setEnabled(true);
        panelInfor.add(cancelarLabel);
        panelInfor.add(boton2);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,80,20,80));
        tabla = new JTable();
        tabla.setModel(new DefaultTableModel());
        tabla.addMouseListener((MouseListener) e);
        scrollPane = new JScrollPane(tabla);

        //JLabel texto = new JLabel(" ");
        //enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        JLabel textoInfor = new JLabel("Tambien puede seleccionar una fila directamente", JLabel.CENTER);
        textoInfor.setBorder(new EmptyBorder(5, 0, 5, 0));
        textoInfor.setForeground(Color.GRAY);
        //panelInfor.add(texto);
        panel.add(textoInfor, BorderLayout.NORTH);

        panel.add(scrollPane, BorderLayout.CENTER);


        mainConten.setBackground(Color.WHITE);
        mainConten.add(panelInfor, BorderLayout.NORTH);
        mainConten.add(panel, BorderLayout.CENTER);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        mainConten.setBackground(Color.decode("#E7EAF0"));
        mainPanel.add(mainConten, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void setContentReportePagos(ActionListener e){

    }

    public void mainContentHandler(int code, ActionListener e){
        switch (code) {
            case 1 -> setContentGeneral(e);
            case 2 -> setContentReportePrestamo(e);
            case 3 -> setContentReportePagos(e);
        }
    }
}
