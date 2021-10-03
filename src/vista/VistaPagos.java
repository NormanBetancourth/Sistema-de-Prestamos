package vista;

import modelo.prestamo.ListaPrestamos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// int numeroDePago, double montoPagado, double interes, double amortizacion

public class VistaPagos extends VentanaGestion{
    private  JComboBox prestamosButton;
    private JTextField idTextField = new JTextField();
    private JTextField numeroTextField = new JTextField();
    private JTextField montoPagadoTextField = new JTextField();
    private JTextField tasaDeInteresTextField = new JTextField();
    private JTextField amortizacionTextField = new JTextField();

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

    public void clearFields(){
        idTextField.setText(null);
        numeroTextField.setText(null);
        prestamosButton.removeAllItems();
        montoPagadoTextField.setText(null);
        tasaDeInteresTextField.setText(null);
        amortizacionTextField.setText(null);
    }

    public JTextField getIdTextField(){
        return idTextField;
    }

    public JTextField getNumeroTextField() {
        return numeroTextField;
    }

    public JComboBox getPrestamosButton() {
        return prestamosButton;
    }

    public JTextField getMontoPagadoTextField(){
        return montoPagadoTextField;
    }
    public JTextField getTasaDeInteresTextField(){
        return tasaDeInteresTextField;
    }
    public JTextField getAmortizacionTextField(){
        return amortizacionTextField;
    }
    public String getTextoId(){
        return idTextField.getText();
    }

    public String getTextoNumero(){
        return numeroTextField.getText();
    }

    public String getTextoPrestamo(){
        return prestamosButton.getSelectedItem().toString();
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

    public void configuraPrestamosBoton(ListaPrestamos listaPrestamos){
        prestamosButton = new JComboBox(listaPrestamos.getLista().toArray());
        prestamosButton.setFocusable(false);
    }

    private void setContentAgregarPago(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());

        JPanel panelInfor = new JPanel(new GridLayout(7, 2, 50, 20));
        panelInfor.setBackground(Color.WHITE);
        panelInfor.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 200));

        JLabel idLabel = new JLabel("Cedula: ");
        idLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idTextField.setPreferredSize(new Dimension(150, 20));
        idTextField.setActionCommand("3-1-0");
        idTextField.addActionListener(e);
        panelInfor.add(idLabel);
        panelInfor.add(idTextField);

        JLabel prestamosLabel = new JLabel("Prestamo: ");
        prestamosLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        prestamosButton = new JComboBox();
        prestamosButton.setFocusable(false);
        prestamosButton.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(prestamosLabel);
        panelInfor.add(prestamosButton);

        JLabel numeroLabel = new JLabel("Numero de pago: ");
        numeroLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        numeroTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(numeroLabel);
        panelInfor.add(numeroTextField);

        //JLabel nombreLabel = new JLabel("Nombre: ");
        //nombreLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        //nombreClienteTextField.setPreferredSize(new Dimension(150, 20));
        //nombreClienteTextField.setEditable(false);
        //panelInfor.add(nombreLabel);
        //panelInfor.add(nombreClienteTextField);

        JLabel montoLabel = new JLabel("Monto: ");
        montoLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        montoPagadoTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(montoLabel);
        panelInfor.add(montoPagadoTextField);

        JLabel tasaDeInteresLabel = new JLabel("Tasa de interes: ");
        tasaDeInteresLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        tasaDeInteresTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(tasaDeInteresLabel);
        panelInfor.add(tasaDeInteresTextField);

        JLabel amortizacionLabel = new JLabel("Amortizacion: ");
        amortizacionLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        amortizacionTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(amortizacionLabel);
        panelInfor.add(amortizacionTextField);

        JLabel enviarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        JButton pagarButton = VistaBuilder.ButtonFactory("Enviar", "3-1-1", e);
        pagarButton.setPreferredSize(new Dimension(100, 20));
        pagarButton.setBackground(Color.decode("#DAF7A6"));
        pagarButton.setBorder(null);
        panelInfor.add(enviarLabel);
        panelInfor.add(pagarButton);

        mainConten.add(panelInfor, BorderLayout.NORTH);
        mainConten.setBackground(Color.WHITE);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        mainConten.setBackground(Color.decode("#E7EAF0"));
        mainPanel.add(mainConten, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void setContentBuscarPago(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        mainConten.setBackground(Color.GREEN);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.setVisible(true);
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
