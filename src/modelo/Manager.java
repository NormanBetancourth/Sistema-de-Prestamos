package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

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

    //String id, String nombre, String provincia, String distrito, String canton
    public void registrarCliente(String id, String nombre, String provincia, String distrito, String canton){
        getListaDeClientes().add(new Cliente(id, nombre, provincia, distrito, canton));
    }

    public boolean clienteEstaRegistrado(String idCliente){
        for(Cliente cliente : getListaDeClientes()) {
            if (cliente.getId().equals(idCliente)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> mostrarCantones(String s){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<String> cantones = new ArrayList<>();
        try {
            archivo = new File ("src/vista/images/db.csv");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            String aux;//para guardar el canton
            linea=br.readLine();
            while (linea!=null){
                if (linea.equals(s)) {
                    int limit = Integer.parseInt(s) + 1;
                    while ((linea) != null && !linea.equals(String.valueOf(limit))) {
                        if (linea.equals(linea.toUpperCase()) && linea.length() > 2) {
                            cantones.add(linea);
                        }
                        linea = br.readLine();
                    }
                    break;
                }
                linea = br.readLine();
            }



        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return cantones;

    }

    public ArrayList<String> mostrarDistritos(String s){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;


        ArrayList<String> distritos = new ArrayList<>();


        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("src/vista/images/db.csv");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            linea=br.readLine();
            while (linea!= null){
                if (linea.equals(s)){
                    linea=br.readLine();
                    while (!linea.equals(linea.toUpperCase())){
                        distritos.add(linea);
                        linea=br.readLine();
                    }
                    break;
                }
                linea=br.readLine();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return distritos;
    }

    public Cliente getAlgunCliente(String idCliente){
        for(Cliente cliente : getListaDeClientes()) {
            if (cliente.getId().equals(idCliente)) {
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

    public String getPrestamosDeAlgunCliente(String idCliente){
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
