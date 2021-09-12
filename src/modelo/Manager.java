package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Manager {
    private ArrayList<Cliente> listaDeClientes;

    public Manager() {
        listaDeClientes = new ArrayList<>();
    }

    public Manager(ArrayList<Cliente> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    public ArrayList<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    public void setListaDeClientes(ArrayList<Cliente> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    public void registrarCliente(Cliente cliente){
        getListaDeClientes().add(cliente);
    }

    public Cliente getAlgunCliente(String idCliente){
        for(Cliente cliente : getListaDeClientes()) {
            if (cliente.getId().equals(idCliente)) {
                return cliente;
            }
        }
        return null;
    }

    //TODO ¿vamoh a eliminar el objeto prestamo cuando ya
    // este completado o nomás queda con valores 0?
    public Prestamo getAlgunPrestamo(String idPrestamo){
        for(Cliente cliente : getListaDeClientes()) {
            if (cliente.getAlgunPrestamo(idPrestamo) != null) {
                return cliente.getAlgunPrestamo(idPrestamo);
            }
        }
        return null;
    }

    public String getPrestamosDeAlgunCliente(String idCliente){
       return getAlgunCliente(idCliente).getListaDePrestamos().toString();
    }

    public void registrarPrestamoAUnCliente(String idCliente, Prestamo prestamo){
        getAlgunCliente(idCliente).getListaDePrestamos().add(prestamo);
    }

    public void asignarCodigoDelPrestamo(Prestamo prestamo, Cliente cliente){
        //TODO combinación para el codigo del prestamo es igual a
        // id del cliente * 31 + un caracter random
        Random random = new Random();
        char randomizedCharacter = (char) (random.nextInt(26) + 'a');
        prestamo.setId(String.valueOf(cliente.hashCode() + "-" + randomizedCharacter));
    }

    public void cancelacionDeCuota(String idPrestamo, Pago pago){
        getAlgunPrestamo(idPrestamo).agregarPago(pago);
    }

    public String getListaDePagosPorPrestamo(String idPrestamo) {
        return getAlgunPrestamo(idPrestamo).registroDePagos();
    }

}
