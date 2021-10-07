package vista;

import controlador.ControladorDeClientes;
import modelo.cliente.Cliente;
import modelo.mapHandler.SubMapHandler;
import modelo.prestamo.Prestamo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
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
    private JPanel mapConteiner; // panel donde va el mapa
    private JScrollPane ScrollPane;
    private JPanel center;
    private JTable table;
    private JButton buscarClientebtn;
    private JButton boton1;
    private JButton boton2;


    public VistaCliente() throws HeadlessException {
        provincias = new String[]{"Heredia", "Alajuela", "Guanacaste", "Puntarenas", "Cartago", "Limon", "San Jose","Puntarenas"};
        provinciaCombo = new JComboBox(provincias);
        provinciaCombo.setSelectedItem("Seleccione");
        provinciaCombo.setEnabled(false);
        provinciaCombo.setBackground(Color.white);
        provinciaCombo.setForeground(Color.BLACK);

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
        listarBoton = VistaBuilder.ButtonFactory("Busqueda de Clientes", "1-2",e);
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
        provinciaCombo.setSelectedItem(null);
        cantonCombo.setSelectedItem(null);
        distritoCombo.setSelectedItem(null);
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

        JPanel auxPanel = new JPanel(new GridLayout(2, 3, 50, 20));
        auxPanel.setBorder(new EmptyBorder(20,100,10,220));

        JLabel idLabel = new JLabel("Cedula: ", JLabel.RIGHT);
        //idLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idTextField.setPreferredSize(new Dimension(100, 20));
        idTextField.setEditable(true);
        auxPanel.add(idLabel);
        auxPanel.add(idTextField);

        boton1 = VistaBuilder.ButtonFactory("Guardar", "guardar-btn", e);
        boton1.setPreferredSize(new Dimension(100, 20));
        boton1.setBackground(Color.decode("#DAF7A6"));
        boton1.setBorder(null);
        auxPanel.add(boton1);

        JLabel nombreLabel = new JLabel("Nombre: ", JLabel.RIGHT);
        //nombreLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        nameTextField.setPreferredSize(new Dimension(100, 20));
        nameTextField.setEditable(true);
        auxPanel.add(nombreLabel);
        auxPanel.add(nameTextField);

        boton2 = VistaBuilder.ButtonFactory("Cancelar", "1-10", e);
        boton2.setPreferredSize(new Dimension(100, 20));
        boton2.setBackground(Color.decode("#C27A8F"));
        boton2.setBorder(null);
        //boton2.setEnabled(false);
        auxPanel.add(boton2);

        mainConten.add(auxPanel, BorderLayout.NORTH);
        mainConten.setBackground(Color.WHITE);
        auxPanel.setBackground(Color.WHITE);

        //combo boxes y otros botones
        auxPanel = new JPanel();
        auxPanel.setLayout(new BorderLayout());

        JPanel comboButtonPanel = new JPanel();
        comboButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel label = new JLabel("Provincia: ", JLabel.RIGHT);
        label.setBorder(BorderFactory.createEmptyBorder(0,20,0,10));
        comboButtonPanel.add(label);
        provinciaCombo.setPreferredSize(new Dimension(130,20));
        comboButtonPanel.add(provinciaCombo);

        label = new JLabel("Canton: ", JLabel.RIGHT);
        label.setBorder(BorderFactory.createEmptyBorder(0,20,0,10));
        comboButtonPanel.add(label);
        cantonCombo.setPreferredSize(new Dimension(130,20));
        comboButtonPanel.add(cantonCombo);

        label = new JLabel("Distrito: ", JLabel.RIGHT);
        label.setBorder(BorderFactory.createEmptyBorder(0,20,0,10));
        comboButtonPanel.add(label);
        distritoCombo.setPreferredSize(new Dimension(130,20));
        comboButtonPanel.add(distritoCombo);

        comboButtonPanel.setBorder(new EmptyBorder(10,0,20,0));
        comboButtonPanel.setBackground(Color.decode("#E7EAF0"));
        auxPanel.add(comboButtonPanel, BorderLayout.NORTH);
        mainConten.add(auxPanel,BorderLayout.CENTER);

        //mapa
        mapConteiner = mapa;
        mapConteiner.setBorder(BorderFactory.createEmptyBorder(0,0,130,0));
        mapConteiner.setLayout(new FlowLayout(FlowLayout.CENTER));
        mapConteiner.setBackground(Color.white);
        auxPanel.add(mapConteiner, BorderLayout.CENTER);

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

    private void setContentListarClientes(ActionListener e){
        mainPanel.remove(mainConten);
        mainConten = new JPanel();
        mainConten.setLayout(new BorderLayout());
        mainConten.setBackground(Color.RED);
        mainPanel.add(mainConten, BorderLayout.CENTER);

        JPanel panelInfor = new JPanel(new GridLayout(2, 2, 50, 20));
        panelInfor.setBackground(Color.WHITE);
        panelInfor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 200));

        JLabel idLabel = new JLabel("Cedula: ");
        idLabel.setBorder(new EmptyBorder(0, 190, 0, 0));
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        idTextField.setPreferredSize(new Dimension(150, 20));
        panelInfor.add(idLabel);
        panelInfor.add(idTextField);

        JLabel enviarLabel = new JLabel(" ");
        enviarLabel.setBorder(new EmptyBorder(0, 190, 0, 0));

        buscarClientebtn = VistaBuilder.ButtonFactory("Buscar", "buscarCliente", e);
        buscarClientebtn.setPreferredSize(new Dimension(100, 20));
        buscarClientebtn.setBackground(Color.decode("#DAF7A6"));
        buscarClientebtn.setBorder(null);
        panelInfor.add(enviarLabel);
        panelInfor.add(buscarClientebtn);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,80,20,80));

        center = new JPanel(new BorderLayout());
        //center.setBorder(BorderFactory.createEmptyBorder(20,80,20,80));
        //center.setSize(new Dimension(getWidth()-20, 500));
        update();

        JLabel a = new JLabel("Doble click sobre el cliente para ver mas detalles", JLabel.CENTER);
        a.setForeground(Color.GRAY);
        a.setBorder(new EmptyBorder(5, 0, 5, 0));
        panel.add(a, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);

        mainConten.setBackground(Color.WHITE);
        mainConten.add(panelInfor, BorderLayout.NORTH);
        mainConten.add(panel, BorderLayout.CENTER);
        mainPanel.add(mainConten, BorderLayout.CENTER);
        mainConten.setBackground(Color.decode("#E7EAF0"));

        validate();
    }


    public void mainContentHandler(int code, ActionListener e, JPanel mapa){
        switch (code) {
            case 1 -> setContentAgregarCliente(e, mapa);
            case 2 -> setContentListarClientes(e);
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
        return cantonCombo.getSelectedItem() == "Seleccione" ? null : String.valueOf(cantonCombo.getSelectedItem());
    }
    public String getSelectedDistrito(){
        return distritoCombo.getSelectedItem() == "Seleccione" ? null : String.valueOf(distritoCombo.getSelectedItem());
    }

    public String getId() {
        return idTextField.getText();
    }

    public String getComboProvincia() {
        return provinciaCombo.getSelectedItem() == "Seleccione" ? null : String.valueOf(provinciaCombo.getSelectedItem());
    }

    public String getComboCanton() {
        return cantonCombo.getSelectedItem() == "Seleccione" ? null : String.valueOf(cantonCombo.getSelectedItem());
    }

    public String getComboDistrito() {
        return distritoCombo.getSelectedItem() == "Seleccione" ? null : String.valueOf(distritoCombo.getSelectedItem());
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

        auxLabel = new JLabel(String.valueOf(cliente.getNombre()));
        auxLabel.setForeground(Color.WHITE);
        auxLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        panel.add(auxLabel);

        auxLabel = new JLabel("Codigo: ");
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
                panelPrestamos.add(new JLabel("ID: "));
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

    public void cancelar() {
        idTextField.setText(null);
        nameTextField.setText(null);
        cantonCombo.setSelectedItem(null);
        distritoCombo.setSelectedItem(null);
        provinciaCombo.setSelectedItem(null);
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
