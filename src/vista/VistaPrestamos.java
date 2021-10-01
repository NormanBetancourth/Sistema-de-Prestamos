package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaPrestamos extends vistaHandler{
    private JPanel mainPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel mainConten = new JPanel();
    private JPanel botonera = new JPanel();
    private JTextField idTextField = new JTextField();
    private JTextField montoTextField = new JTextField();
    private JTextField tasaDeInteresTextField = new JTextField();
    private JTextField plazoTextField = new JTextField();
    private JButton inicioBoton;
    private JButton agregarPrestamoBoton;
    private JButton buscarPrestamoBoton;
    private JButton listadoPrestamosBoton;

    public VistaPrestamos()throws HeadlessException {
        this.setSize(new Dimension(900,790));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }

    public void addComponents(ActionListener e){
        JLabel label =  new JLabel("Gestion De Prestamos");
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
        inicioBoton = ButtonFactory("Inicio", "2-0",e);
        botonera.add(inicioBoton);
        agregarPrestamoBoton = ButtonFactory("Agregar Prestamo", "2-1",e);
        botonera.add(agregarPrestamoBoton);
        buscarPrestamoBoton =ButtonFactory("Buscar Prestamo", "2-2",e);
        botonera.add(buscarPrestamoBoton);
        listadoPrestamosBoton =ButtonFactory("Listado de Prestamos", "2-3",e);
        botonera.add(listadoPrestamosBoton);

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

    public void clearFields(){
        idTextField.setText(null);
        montoTextField.setText(null);
        tasaDeInteresTextField.setText(null);
        plazoTextField.setText(null);
    }

    public JButton getInicioBoton() {
        return inicioBoton;
    }
    public JButton getAgregarClienteBoton() {
        return agregarPrestamoBoton;
    }
    public JButton getBuscarClienteBoton() {
        return buscarPrestamoBoton;
    }
    public JButton getListadoClientesBoton() {
        return listadoPrestamosBoton;
    }

    public JTextField getIdTextField(){
        return idTextField;
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
    public String getTextoId(){
        return idTextField.getText();
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

    private void setContentAgregarPrestamo(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        mainConten.setBackground(Color.GREEN);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void setContentBuscarPrestamo(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        mainConten.setBackground(Color.GREEN);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void setConetentPaneListarPrestamos(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        mainConten.setBackground(Color.GREEN);
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
