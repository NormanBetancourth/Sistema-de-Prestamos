package modelo;

import modelo.cliente.Cliente;
import modelo.cliente.ListaClientesHandler;
import modelo.cliente.ModeloTablaCliente;
import modelo.pago.Pago;
import modelo.prestamo.Prestamo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ModelHandler {

    //Clientes
    private ListaClientesHandler clientes;
    private ModeloTablaCliente modeloTablaCliente;

    //Parser
    JAXBParser parser;





    //cambiar ->
    private ArrayList<Cliente> listaDeClientes;

    public ModelHandler() {
        listaDeClientes = new ArrayList<>();
    }

    public ModelHandler(ArrayList<Cliente> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    public ArrayList<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    public void setListaDeClientes(ArrayList<Cliente> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    //String id, String nombre, String provincia, String distrito, String canton
    public void registrarCliente(int id, String nombre, String provincia, String distrito, String canton){
        getListaDeClientes().add(new Cliente(id, nombre, provincia, distrito, canton));
    }

    public boolean clienteEstaRegistrado(int idCliente){
        for(Cliente cliente : getListaDeClientes()) {
            if (cliente.getId() == (idCliente)) {
                return true;
            }
        }
        return false;
    }



    public Cliente getAlgunCliente(int idCliente){
        for(Cliente cliente : getListaDeClientes()) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    public Prestamo getAlgunPrestamo(String idPrestamo){
        for(Cliente cliente : getListaDeClientes()) {
            if (cliente.getAlgunPrestamo(idPrestamo) != null) {
                return cliente.getAlgunPrestamo(idPrestamo);
            }
        }
        return null;
    }

    public String getPrestamosDeAlgunCliente(int idCliente){
       return getAlgunCliente(idCliente).getListaDePrestamos().toString();
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
