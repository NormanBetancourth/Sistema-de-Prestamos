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

public class ModelHandler {

    //Clientes
    private ListaClientes clientes;
    private ListaPagos pagos;
    private ListaPrestamos prestamos;

    private ModeloTablaPrestamos modeloTablaPrestamos;
    private ModeloTablaPagos modeloTablaPagos;
    private ModeloTablaCliente modeloTablaCliente;

    //Parser
    JAXBParser parser;
    public void agregarCliente(Cliente c){
        clientes.add(c);
    }
    public void agregarPago(Pago c){
        pagos.add(c);
    }
    public void agregarPrestamo(Prestamo c){
        prestamos.add(c);
    }

    public void addListeners(TableModelListener e){
        if (modeloTablaCliente!=null)
            modeloTablaCliente.addTableModelListener(e);
        if (modeloTablaPagos!=null)
            modeloTablaPagos.addTableModelListener(e);
        if (modeloTablaPrestamos!=null)
            modeloTablaPrestamos.addTableModelListener(e);
    }


    public ModelHandler() {
        clientes = new ListaClientes();
        pagos = new ListaPagos();
        prestamos = new ListaPrestamos();
        cargarClientes();
    }

    private void cargarClientes() {
    }


    //cambiar ->

    public ListaClientes getListaDeClientes() {
        return clientes;
    }

    //String id, String nombre, String provincia, String distrito, String canton
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

    public Cliente getAlgunCliente(int idCliente){
        for(Cliente cliente : getListaDeClientes().getLista()) {
            if (cliente.getId() == idCliente) {
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
       return getAlgunCliente(idCliente).getListaDePrestamos();
    }

    public String retornaNombrePorId(int idCliente){
        return getAlgunCliente(idCliente).getNombre();
    }

    public void registrarPrestamoAUnCliente(Cliente cliente, double monto, double tasaDeInteres, int plazo){
        //double monto, double tasaDeInteres, int plazo
        Prestamo prestamo = new Prestamo(monto, tasaDeInteres, plazo);
        asignarCodigoDelPrestamo(prestamo, cliente);
        getAlgunCliente(cliente.getId()).getListaDePrestamos().add(prestamo);
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

    public String getListaDePagosPorPrestamo(String idPrestamo) {
        return getAlgunPrestamo(idPrestamo).registroDePagos();
    }

}
