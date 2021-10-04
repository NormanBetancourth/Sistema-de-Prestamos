package vista;

import controlador.ControladorDeClientes;
import modelo.cliente.Cliente;
import modelo.mapHandler.SubMapHandler;
import modelo.prestamo.Prestamo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VistaCliente extends VentanaGestion{
    private JTextField nameTextField = new JTextField();
    private JTextField idTextField = new JTextField();
    private JComboBox provinciaCombo;
    private JComboBox cantonCombo;
    private JComboBox distritoCombo;
    private String[] provincias;
    private ArrayList<String> cantones;
    private ArrayList<String>  distritos;
    private JButton inicioBoton;
    private JPanel mapConteiner; // label donde va el mapa
    private JScrollPane ScrollPane;
    private JPanel center;
    private JTable table;
    private JTextField prestamoTextField;
    private JButton buscarClientebtn;


    public VistaCliente() throws HeadlessException {
        provincias = new String[]{"Heredia", "Alajuela", "Guanacaste", "Puntarenas", "Cartago", "Limon", "San Jose","Puntarenas"};
        provinciaCombo = new JComboBox(provincias);
        provinciaCombo.setSelectedItem("Seleccione");
        provinciaCombo.setEnabled(false);
        provinciaCombo.setBackground(Color.white);
        provinciaCombo.setForeground(Color.BLACK);
        prestamoTextField = new JTextField();

        cantonCombo= new JComboBox();
        distritoCombo= new JComboBox();
        cantonCombo.setSelectedItem("Elige");
        distritoCombo.setSelectedItem("Elige");

        provinciaCombo.setActionCommand("Provincia");
        cantonCombo.setActionCommand("Canton");
        distritoCombo.setActionCommand("Distrito");
        table = new JTable();
        center = new JPanel();
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
        inicioBoton = VistaBuilder.ButtonFactory("Inicio", "1-0",e);
        botonera.add(inicioBoton);
        agregarBoton = VistaBuilder.ButtonFactory("Agregar Cliente", "1-1",e);
        botonera.add(agregarBoton);
//        buscarBoton = VistaBuilder.ButtonFactory("Buscar Cliente", "1-2",e);
//        botonera.add(buscarBoton);
        listarBoton = VistaBuilder.ButtonFactory("Listado y busqueda de Clientes", "1-3",e);
        botonera.add(listarBoton);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(botonera, BorderLayout.NORTH);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        mainPanel.setBackground(Color.white);
        this.add(mainPanel, BorderLayout.CENTER);
        southPanel.setPreferredSize(new Dimension(100,20));
        southPanel.setBackground(Color.decode("#081F62"));
        provinciaCombo.addActionListener(e);
        cantonCombo.addActionListener(e);
        distritoCombo.addActionListener(e);

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
    public ArrayList<String> getCantones() {
        return cantones;
    }
    public ArrayList<String> getDistritos() {
        return distritos;
    }
    public void setProvincias(String[] provincias) {
        this.provincias = provincias;
    }
    public void setCantones(ArrayList<String> cantones) {
        this.cantones = cantones;
        validate();

    }
    public void setDistritos(ArrayList<String> distritos) {
        this.distritos = distritos;
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
        JButton btnAux = VistaBuilder.ButtonFactory("Guardar", "1-9", e);
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

        JButton btnGuardar= VistaBuilder.ButtonFactory("", "guardar-btn",e);

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
        JButton prestamo = VistaBuilder.ButtonFactory("","pretamo-btn",e);
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

    public void setprovinciaMapa(int i){
        if (i == -1){
            provinciaCombo.setSelectedItem("Seleccione");
        }else {
            provinciaCombo.setSelectedItem(provincias[i]);
        }

    }

    private void setContentBuscarCliente(ActionListener e){

        //mainContent.setContentPane(new clase x);
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        mainConten.setBackground(Color.GREEN);





        mainPanel.add(mainConten, BorderLayout.CENTER);
        validate();
    }

    private void setContentListarClientes(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        mainConten.setBackground(Color.RED);
        mainPanel.add(mainConten, BorderLayout.CENTER);

        JPanel panelInfor = new JPanel(new GridLayout(3, 2, 50, 20));
        panelInfor.setBackground(Color.WHITE);
        panelInfor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 200));

        JLabel idLabel = new JLabel("Ingrese la cedula del Cliente: ");
        idLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        prestamoTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(idLabel);
        panelInfor.add(prestamoTextField);

        JLabel enviarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        buscarClientebtn = VistaBuilder.ButtonFactory("Buscar", "buscarCliente", e);
        buscarClientebtn.setPreferredSize(new Dimension(100, 20));
        buscarClientebtn.setBackground(Color.decode("#DAF7A6"));
        buscarClientebtn.setBorder(null);
        panelInfor.add(enviarLabel);
        panelInfor.add(buscarClientebtn);
        JLabel a = new JLabel("Doble click sobre el cliente para ver mas detalles");
        a.setHorizontalAlignment(JLabel.RIGHT);
        panelInfor.add(a);


        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,100,100,100));

        center.setSize(new Dimension(getWidth()-20, 500));
        update();
        panel.add(center, BorderLayout.NORTH);


        mainConten.setBackground(Color.WHITE);
        mainConten.add(panelInfor, BorderLayout.NORTH);
        mainConten.add(panel, BorderLayout.CENTER);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        mainConten.setBackground(Color.decode("#E7EAF0"));



        validate();
    }

    public String getPrestamoTextField() {
        return prestamoTextField.getText();
    }

    public void mainContentHandler(int code, ActionListener e, JPanel mapa){
        switch (code) {
            case 1 -> setContentAgregarCliente(e, mapa);
            case 2 -> setContentBuscarCliente(e);
            case 3 -> setContentListarClientes(e);
        }
    }

    public void cargarCantones(int i) {
        String ss;
        switch (i){
            case 0 -> {
                ss = "3";
                break;
            }
            case 1 -> {
                ss = "1";
                break;
            }
            case 2 -> {
                ss = "4";
                break;
            }
            case 3,7 -> {
                ss = "5";
                break;
            }
            case 4 -> {
                ss = "2";
                break;
            }
            case 5 -> {
                ss = "6";
                break;
            }
            case 6 -> {
                ss = "0";
                break;
            }
            default -> throw new IllegalStateException("Unexpected value: " + i);
        }

        setCantones(SubMapHandler.mostrarCantones(ss));
        if (distritoCombo.getItemCount() > 0)
            distritoCombo.removeAllItems();
        if (cantonCombo.getItemCount() > 0)
            cantonCombo.removeAllItems();
        for (String s:cantones){
            cantonCombo.addItem(s);
        }

        repaint();
    }

    public void cargarDistritos(String ss){

        setDistritos(SubMapHandler.mostrarDistritos(ss));
        distritoCombo.removeAllItems();
        for (String s:distritos){
            distritoCombo.addItem(s);
        }

        repaint();
    }

    public String getSelectedCanton(){
        return (String) cantonCombo.getSelectedItem();
    }
    public String getSelectedDistrito(){
        return (String) distritoCombo.getSelectedItem();
    }

    public String getId() {
        return idTextField.getText();
    }

    public String getComboProvincia() {
        return (String) provinciaCombo.getSelectedItem();
    }

    public String getComboCanton() {
        return (String) cantonCombo.getSelectedItem();

    }

    public String getComboDistrito() {
        return (String) distritoCombo.getSelectedItem();
    }

    public void setTable(JTable table) {
        this.table = table;
        update();
    }

    private void update() {
        ScrollPane = new JScrollPane(table);
        if (center.getComponentCount() > 0){
            center.remove(0);//en centro va la tabla
        }
        center.add(ScrollPane);
        center.validate();
    }

    public void addListeners(ControladorDeClientes.ListenerHandler listenerHandler) {
        table.addMouseListener(listenerHandler);

    }

    public JTable getTable() {
        return table;
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public void mostrarVentantaInfoCLiente(Cliente cliente) {

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


        auxLabel = new JLabel(cliente.getNombre());
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

        panelPrestamos.setPreferredSize(new Dimension(400,300));
        panelPrestamos.setLayout(new GridLayout(14,2));
        panelPrestamos.add(new JLabel("Prestamos"));
        panelPrestamos.add(new JLabel(" "));
        if (cliente.tienePrestamos()){
            for (Prestamo p: cliente.getListaDePrestamosRaw()){
                panelPrestamos.add(new JLabel("ID: "));
                panelPrestamos.add(new JLabel(p.getId()));

                panelPrestamos.add(new JLabel("Monto: "));
                panelPrestamos.add(new JLabel(String.valueOf(p.getMonto())));

                panelPrestamos.add(new JLabel("Fecha: "));
                panelPrestamos.add(new JLabel(p.getFecha()));

                panelPrestamos.add(new JLabel(" "));
                panelPrestamos.add(new JLabel(" "));

            }

        }

        JFrame frame = new JFrame("Informacion detallada de un cliente");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,500);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(panelPrestamos);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


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
