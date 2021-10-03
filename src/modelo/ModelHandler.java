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
import java.util.ArrayList;
import java.util.List;

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
        ListaPrestamos listaPrestamos = retornaPrestamosActivos(cliente);
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
        if (clientes.getSize()>0){//todo quitar demeter
            parser.marshall(clientes, "src/modelo/dataBase/Clientes.xml");
        }
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

    private Cliente buscarCliente(int id) {
        return clientes.buscar(id);
    }

    public ModelHandler() {
        clientes = new ListaClientes();
        pagos = new ListaPagos();
        prestamos = new ListaPrestamos();
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
        ListaPrestamos prestamos = new ListaPrestamos();
        for(Prestamo prestamo : cliente.getListaDePrestamos().getLista()){
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
        asignarCodigoDelPrestamo(prestamo, cliente);
        buscarCliente(cliente.getId()).addPrestamo(prestamo);
    }

    public void asignarCodigoDelPrestamo(Prestamo prestamo, Cliente cliente){
        // Combinación para el codigo del prestamo es igual a
        // id del cliente * 31
        prestamo.setId(String.valueOf(cliente.hashCode()));
    }

    public void cancelacionDeCuota(String idPrestamo, int numeroDePago, double montoPagado, double interes, double amortizacion){
        //int numeroDePago, double montoPagado, double interes, double amortizacion
        getAlgunPrestamo(idPrestamo).agregarPago(new Pago(numeroDePago, montoPagado, interes, amortizacion));
    }

    public ListaPagos getListaDePagosPorPrestamo(String idPrestamo) {
        return getAlgunPrestamo(idPrestamo).getListaDePagos();
    }

}
