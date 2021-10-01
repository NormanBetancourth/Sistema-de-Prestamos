package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaPagos extends VentanaGestion{
    private JTextField idTextField = new JTextField();
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
        inicioBoton = VistaHandler.ButtonFactory("Inicio", "3-0",e);
        botonera.add(inicioBoton);
        agregarBoton = VistaHandler.ButtonFactory("Agregar Pago", "3-1",e);
        botonera.add(agregarBoton);
        buscarBoton = VistaHandler.ButtonFactory("Buscar Pago", "3-2",e);
        botonera.add(buscarBoton);
        listarBoton = VistaHandler.ButtonFactory("Listado de Pagos", "3-3",e);
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
        montoPagadoTextField.setText(null);
        tasaDeInteresTextField.setText(null);
        amortizacionTextField.setText(null);
    }

    public JTextField getIdTextField(){
        return idTextField;
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
    public String getTextoMontoPagado(){
        return montoPagadoTextField.getText();
    }
    public String getTextoTasaDeInteres(){
        return tasaDeInteresTextField.getText();
    }
    public String getTextoAmortizacion(){
        return amortizacionTextField.getText();
    }

    private void setContentAgregarPago(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        mainConten.setBackground(Color.GREEN);
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
