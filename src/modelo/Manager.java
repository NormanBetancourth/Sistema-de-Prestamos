package modelo;

import java.util.ArrayList;
import java.util.Iterator;

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

    public void agregarPrestamo(String id, Prestamo prestamo){
        //Iterator<Cliente> iterator = listaDeClientes.listIterator();
       for(Cliente cliente : listaDeClientes){
           if(cliente.getId().equals(id)){
               cliente.getListaDePrestamos().add(prestamo);
               break;
           }
       }
    }

}
