package controlador;

import modelo.ModelHandler;
import modelo.cliente.Cliente;
import modelo.cliente.ModeloTablaCliente;
import modelo.mapHandler.mapHandler;
import modelo.prestamo.ModeloTablaPrestamos;
import modelo.prestamo.Prestamo;
import vista.HomeFrame;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Controlador {
    HomeFrame Home;
    ModelHandler modelo;
    ControladorDeClientes controladorDeClientes;
    ControladorDePrestamos controladorDePrestamos;
    ControladorDePagos controladorDePagos;
    ControladorDeReportes controladorDeReportes;
    private mapHandler mapCreator;


    public Controlador() {
        Home = new HomeFrame();
        Home.addComponents(new ListenerHandler());
        HiloMapa h1 = new HiloMapa();
        h1.setPriority(10);
        h1.start();
        modelo = new ModelHandler();
    }

    public ModelHandler getModelo() {
        return modelo;
    }

    public void setVisible() {
        Home.setVisible(true);
    }

    public void addCliente(Cliente c) {
        modelo.agregarCliente(c);
    }

    public Cliente buscaCliente(int id) {
        return modelo.buscarCliente(id);
    }

    public ModeloTablaCliente getModeloTablaCliente() {
        return modelo.getModeloTablaCliente();
    }

    public ModeloTablaPrestamos getModeloTablaPrestamos(){
        return modelo.getModeloTablaPrestamos();
    }

    public int getIdClienteTabla(int selectedRow, int i) {
        return modelo.getValueModeloCliente(selectedRow, i);
    }

    public void guardar() {
        modelo.guardarDatos();
    }

    public ModeloTablaPrestamos getModeloTablaPrestamosRaw() {
        return modelo.getModeloTablaPrestamosRaw();
    }

    public List<Prestamo> getTodoslosPrestamos() {
        return modelo.getTodosLosPrestamos();
    }

    public List<Cliente> getListaClientesRaw() {
        return modelo.getListaDeClientesRaw();
    }

    public Cliente getCliente(int idCliente) {
        return modelo.buscarCliente(idCliente);
    }


    private  class ListenerHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String valor = e.getActionCommand();
                switch (valor) {
                    case "1" -> {
                        try {
                            if (mapCreator != null){
                                Home.dispose();
                                controladorDeClientes = new ControladorDeClientes(mapCreator, Controlador.this);
                            }else {
                                throw  new Exception("Por favor espere unos segundos mientras se carga el mapa");
                            }

                        }catch (Exception xe){
                            JOptionPane.showMessageDialog(null, xe.getMessage(),"Atencion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    case "2" -> {
                        Home.dispose();
                        controladorDePrestamos = new ControladorDePrestamos(Controlador.this);
                    }
                    case "3" -> {
                        Home.dispose();
                        controladorDePagos = new ControladorDePagos(Controlador.this);
                    }
                    case "4" -> {
                        Home.dispose();
                        controladorDeReportes = new ControladorDeReportes(Controlador.this);
                    }
                }
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    private class HiloMapa extends Thread{

        @Override
        public void run() {
            mapCreator = new mapHandler(new ControladorDeClientes.MousePositionListener(), new ControladorDeClientes.MousePositionListener());

        }

    }



}
