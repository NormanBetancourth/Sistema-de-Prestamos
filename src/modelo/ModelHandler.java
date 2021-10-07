package modelo;

import modelo.cliente.Cliente;
import modelo.cliente.ListaClientes;
import modelo.cliente.ModeloTablaCliente;
import modelo.pago.ListaPagos;
import modelo.pago.ModeloTablaPagos;
import modelo.pago.Pago;
import modelo.prestamo.ListaPrestamos;
import modelo.prestamo.ModeloTablaPrestamos;
import modelo.prestamo.Prestamo;

import javax.swing.event.TableModelListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelHandler {

    //Clientes
    private ListaClientes clientes;
    private ListaPagos pagos;
    private ListaPrestamos prestamos;

    private ModeloTablaPrestamos modeloTablaPrestamos;
    private ModeloTablaPagos modeloTablaPagos;
    private ModeloTablaCliente modeloTablaCliente;

    //Parser
    JAXBParser parser = new JAXBParser();

    public void agregarCliente(Cliente c){
        clientes.add(c);
    }
    public void agregarPago(Pago c){
        pagos.add(c);
    }
    public void agregarPrestamo(Prestamo c){
        prestamos.add(c);
    }

    public ModeloTablaPrestamos configuraModelo(Cliente cliente){
        //ListaPrestamos listaPrestamos = retornaPrestamosActivos(cliente);
        ListaPrestamos listaPrestamos = cliente.getListaDePrestamos();
        modeloTablaPrestamos = new ModeloTablaPrestamos(listaPrestamos.getLista());
        return modeloTablaPrestamos;
    }

    public ModeloTablaPagos configuraModelo(Prestamo prestamo){
        ListaPagos listaPagos = prestamo.getListaDePagos();
        modeloTablaPagos = new ModeloTablaPagos(listaPagos.getLista());
        return modeloTablaPagos;
    }

    public void addListeners(TableModelListener e){
        if (modeloTablaCliente!=null)
            modeloTablaCliente.addTableModelListener(e);
        if (modeloTablaPagos!=null)
            modeloTablaPagos.addTableModelListener(e);
        if (modeloTablaPrestamos!=null)
            modeloTablaPrestamos.addTableModelListener(e);
    }

    public void cargarModeloTablaClientes(){
        modeloTablaCliente = new ModeloTablaCliente(clientes.getLista());
    }
    public void cargarModeloTablaPrestamos(){
        modeloTablaPrestamos = new ModeloTablaPrestamos(prestamos.getLista());
    }

    public void cargarDatos(){
        try {
            clientes = (ListaClientes) parser.unmarshall(new ListaClientes(), "src/modelo/dataBase/Clientes.xml");
            if (clientes== null){
                clientes = new ListaClientes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardarDatos(){
        parser.marshall(clientes, "src/modelo/dataBase/Clientes.xml");
    }

    public void cargarPrestamos(){
        if (clientes.getSize()>0){
            for (Cliente cliente:clientes.getLista()){
                if (buscarCliente(cliente.getId()) == null){
                    for (Prestamo prestamo: cliente.getListaDePrestamosRaw()){
                        if (buscarPrestamo(prestamo.getId()) == null ){
                            prestamos.add(prestamo);
                        }
                    }
                }
            }
        }
    }

    private Prestamo buscarPrestamo(String id) {
        return prestamos.buscar(id);
    }

    public Cliente buscarCliente(int id) {
        return clientes.buscar(id);
    }

    public ModeloTablaCliente getModeloTablaCliente() {
        cargarModeloTablaClientes();
        return modeloTablaCliente;
    }

    public ModeloTablaPrestamos getModeloTablaPrestamos(){
        return modeloTablaPrestamos;
    }

    public Integer getIndexTablaCliente(String id){
        return modeloTablaCliente.getIndex(id);
    }


    public ModelHandler() {
        clientes = new ListaClientes();
        pagos = new ListaPagos();
        prestamos = new ListaPrestamos();
        parser = new JAXBParser();
        cargarDatos();
    }

    private void cargarClientes() {
    }

    public ListaClientes getListaDeClientes() {
        return clientes;
    }


    public void registrarCliente(int id, String nombre, String provincia, String distrito, String canton){
        getListaDeClientes().add(new Cliente(id, nombre, provincia, distrito, canton));
    }

    public boolean clienteEstaRegistrado(int idCliente){
        for(Cliente cliente : getListaDeClientes().getLista()) {
            if (cliente.getId() == (idCliente)) {
                return true;
            }
        }
        return false;
    }

    public ListaPrestamos retornaPrestamosActivos(Cliente cliente){
        ListaPrestamos listaPrestamos = cliente.getListaDePrestamos();
        ListaPrestamos prestamos = new ListaPrestamos();
        for(Prestamo prestamo : listaPrestamos.getLista()){
            if(prestamo.isEstado()){
                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }

    public Cliente getClientePorID(int id){
        for(Cliente cliente: getListaDeClientes().getLista()){
            if(cliente.getId() == id){
                return cliente;
            }
        }
        return null;
    }

    public Prestamo getAlgunPrestamo(String idPrestamo){
        for(Cliente cliente : getListaDeClientes().getLista()) {
            if (cliente.getAlgunPrestamo(idPrestamo) != null) {
                return cliente.getAlgunPrestamo(idPrestamo);
            }
        }
        return null;
    }

    public ListaPrestamos getPrestamosDeAlgunCliente(int idCliente){
       return buscarCliente(idCliente).getListaDePrestamos();
    }


    public String retornaNombrePorId(int idCliente){
        return buscarCliente(idCliente).getNombre();
    }

    public void registrarPrestamoAUnCliente(Cliente cliente, double monto, double tasaDeInteres, int plazo){
        //double monto, double tasaDeInteres, int plazo
        Prestamo prestamo = new Prestamo(monto, tasaDeInteres, plazo);
        prestamos.add(prestamo);
        asignarCodigoDelPrestamo(prestamo, cliente);
        buscarCliente(cliente.getId()).addPrestamo(prestamo);
    }

    public void asignarCodigoDelPrestamo(Prestamo prestamo, Cliente cliente){
        //TODO combinación para el codigo del prestamo es igual a
        // id del cliente * 31 + 3 caracteres random
        SecureRandom random = new SecureRandom();
        char caracter1 = (char) (random.nextInt(26) + 'A');
        char caracter2 = (char) (random.nextInt(26) + 'A');
        char caracter3 = (char) (random.nextInt(26) + 'A');
        prestamo.setId(String.valueOf(cliente.hashCode() + "-" + caracter1 + caracter2 + caracter3));
    }

    public boolean isPrestamoActivo(String idPrestamo){
        Prestamo prestamo = getAlgunPrestamo(idPrestamo);
        return prestamo.isEstado();
    }

    public void cancelacionDeCuota(String idPrestamo, double montoPagado) {
        //int numeroDePago, double montoPagado, double interes, double amortizacion
        Prestamo prestamo = getAlgunPrestamo(idPrestamo);
        if(prestamo.isEstado()){
            prestamo.agregarPago(new Pago(montoPagado));
            if(prestamo.getMonto() <= 0){
                prestamo.setEstado(false);
            }
        }
    }

    public ListaPagos getListaDePagosPorPrestamo(String idPrestamo) {
        return getAlgunPrestamo(idPrestamo).getListaDePagos();
    }

    public int getValueModeloCliente(int selectedRow, int i) {
       return (Integer) modeloTablaCliente.getValueAt(selectedRow,i);
    }
}
