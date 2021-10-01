package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaCliente extends vistaHandler{
    private JPanel mainPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel mainConten = new JPanel();
    private JPanel botonera = new JPanel();
    private JTextField nameTextField = new JTextField();
    private JTextField idTextField = new JTextField();
    private JComboBox provinciaCombo;
    private JComboBox cantonCombo;
    private JComboBox distritoCombo;
    private String[] provincias;
    private String[] cantones;
    private String[] distritos;
    private JButton inicioBoton;
    private JButton agregarClienteBoton;
    private JButton buscarClienteBoton;
    private JButton listadoClientesBoton;
    private JPanel mapConteiner; // label donde va el mapa

    public VistaCliente() throws HeadlessException {
        this.setSize(new Dimension(900,790));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // TODO ajustar
        provincias = new String[]{"SAN JOSE", "HEREDIA", "ALAJUELA", "CARTAGO", "LIMON", "PUNTARENAS", "GUANACASTE"};
        provinciaCombo = new JComboBox(provincias);
        cantonCombo= new JComboBox(provincias);
        distritoCombo= new JComboBox(provincias);
    }

    /*
    public void configuraComboBoxes(HashMap<String, HashMap<String, Object>> cantonesDistritos){
        provinciaCombo = new JComboBox(provincias);
        // Obteniendo las llaves del mapa (cantones) y pasando todos estos valores
        // a que sean del tipo array
        cantonCombo = new JComboBox(cantonesDistritos.keySet().toArray());
        distritoCombo = new JComboBox(cantonesDistritos.keySet().toArray());
    }
     */

    public void addComponents(ActionListener e , JPanel mapa){
        ImageIcon imageIcon = new ImageIcon("src/vista/images/icons8-client-64.png");
        this.setIconImage(imageIcon.getImage());

        JLabel label =  new JLabel("Gestion De Clientes");
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
        inicioBoton = ButtonFactory("Inicio", "1-0",e);
        botonera.add(inicioBoton);
        agregarClienteBoton = ButtonFactory("Agregar Cliente", "1-1",e);
        botonera.add(agregarClienteBoton);
        buscarClienteBoton =ButtonFactory("Buscar Cliente", "1-2",e);
        botonera.add(buscarClienteBoton);
        listadoClientesBoton =ButtonFactory("Listado de Clientes", "1-3",e);
        botonera.add(listadoClientesBoton);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(botonera, BorderLayout.NORTH);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        mainPanel.setBackground(Color.white);
        this.add(mainPanel, BorderLayout.CENTER);
        southPanel.setPreferredSize(new Dimension(100,20));
        southPanel.setBackground(Color.decode("#081F62"));

        mainContentHandler(1,e, mapa);

        this.add(southPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void clearFields(){
        nameTextField.setText(null);
        idTextField.setText(null);
        provinciaCombo.setSelectedItem(0);
        cantonCombo.setSelectedItem(0);
        distritoCombo.setSelectedItem(0);
    }

    public String[] getProvinciaSelected() {
        return provincias;
    }
    public String[] getCantones() {
        return cantones;
    }
    public String[] getDistritos() {
        return distritos;
    }
    public void setProvincias(String[] provincias) {
        this.provincias = provincias;
    }
    public void setCantones(String[] cantones) {
        this.cantones = cantones;
    }
    public void setDistritos(String[] distritos) {
        this.distritos = distritos;
    }

    public JButton getInicioBoton() {
        return inicioBoton;
    }
    public JButton getAgregarClienteBoton() {
        return agregarClienteBoton;
    }
    public JButton getBuscarClienteBoton() {
        return buscarClienteBoton;
    }
    public JButton getListadoClientesBoton() {
        return listadoClientesBoton;
    }

    public JTextField getNameTextField(){
        return nameTextField;
    }
    public JTextField getIdTextField(){
        return idTextField;
    }
    public String getTextoName(){
        return nameTextField.getText();
    }
    public String getTextoId(){
        return idTextField.getText();
    }

    private void setContentAgregarCliente(ActionListener e, JPanel mapa){
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

        //combo boxes y otros botones
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
        mapConteiner = mapa;
        mapConteiner.setLayout(new FlowLayout(FlowLayout.CENTER));
        mapConteiner.setBackground(Color.white);
        auxPanel.add(mapConteiner, BorderLayout.CENTER);

        //rigth column
        //TODO Agregar ToolTipText
        JButton prestamo = ButtonFactory("","pretamo-btn",e);
        saveImg = new ImageIcon("src/vista/images/icons8-money-48.png");

        prestamo.setIcon(saveImg);

        JPanel panelPrestamo = new JPanel();
        panelPrestamo.setBackground(Color.decode("#E7EAF0"));
        panelPrestamo.setPreferredSize(new Dimension(100,30));
        panelPrestamo.setLayout(null);
        JLabel prestamoLabel = new JLabel("     Prestamos");
        panelPrestamo.add(prestamoLabel);
        prestamoLabel.setBounds(5,15, 100,30);

        panelPrestamo.add(prestamo);
        prestamo.setBounds(17,50,60,40);
        prestamo.setBorder(null);
        prestamo.setBackground(Color.decode("#E7EAF0"));
        auxPanel.add(panelPrestamo, BorderLayout.EAST);

        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void setContentBuscarCliente(ActionListener e){

        //mainContent.setContentPane(new clase x);
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        mainConten.setBackground(Color.GREEN);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void mainContentHandler(int code, ActionListener e,JPanel mapa){
        switch (code){
            case 1:
                setContentAgregarCliente(e, mapa);
                break;
            case 2:
                setContentBuscarCliente(e);
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

/*

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


 */
