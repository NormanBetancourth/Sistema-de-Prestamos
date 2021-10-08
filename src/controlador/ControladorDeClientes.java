package controlador;
import modelo.cliente.Cliente;
import modelo.mapHandler.mapHandler;
import vista.VistaCliente;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.*;

public class ControladorDeClientes {
    private static VistaCliente vistaCliente;
    private JPanel mapa;
    private static mapHandler mapCreator;
    private Controlador ctrl;
    JTable table;

    public ControladorDeClientes(mapHandler mapC, Controlador c) {
        ctrl = c;
        mapCreator = mapC;
        mapa = (JPanel) mapCreator.getUI();
        vistaCliente = new VistaCliente();
        vistaCliente.addComponents(new ListenerHandler(), mapa);
        vistaCliente.addListeners(new ListenerHandler());
        table = new JTable();
        table.setModel(ctrl.getModeloTablaCliente());
        vistaCliente.setTable(table);
        vistaCliente.addListeners(new ListenerHandler());
    }

    public void setMapa(JPanel mapa) {
        this.mapa = mapa;
    }
    public void setProvincia(int i){
        vistaCliente.setprovinciaMapa(i);

    }

    public class ListenerHandler implements ActionListener, MouseListener {
        //Cliente: cédula, nombre y provincia, cantón y distrito de su dirección
        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            String nombre = null;
            String provincia = null;
            String canton = null;
            String distrito = null;
            int idCliente = 0;
            Cliente cliente = null;

            switch (valor) {
                case "buscarCliente" -> {
                    try {
                        if (vistaCliente.getId().isBlank()) {
                            throw new Exception("Existen campos de informacion vacios");
                        } else {
                            try {
                                idCliente = Integer.parseInt(vistaCliente.getId());
                            } catch (NumberFormatException numberFormatException) {
                                vistaCliente.leerError("Solo se aceptan numeros para determinadas variables");
                                vistaCliente.clearFields();
                                break;
                            }
                            try {
                                cliente = ctrl.buscaCliente(idCliente);
                                if (cliente == null) {
                                    throw new Exception("El usuario indicado no se encuentra registrado en el sistema");
                                } else {
                                    vistaCliente.mostrarVentantaInfoCLiente(cliente);
                                }
                            } catch (Exception exception) {
                                vistaCliente.leerError(exception.getMessage());
                                vistaCliente.clearFields();
                                break;
                            }
                        }
                    } catch (Exception exception) {
                        vistaCliente.leerError(exception.getMessage());
                    }
                }
                case "Canton" ->{
                    if (vistaCliente.getSelectedCanton() != null){
                        vistaCliente.cargarDistritos(vistaCliente.getSelectedCanton());
                    }
                }
                case "guardar-btn" -> {
                    try{
                       try{
                           idCliente = Integer.parseInt(vistaCliente.getId());
                       } catch (NumberFormatException numberFormatException) {
                           JOptionPane.showMessageDialog(null, "La cedula solo puede contener numeros","Atencion", JOptionPane.INFORMATION_MESSAGE);
                           vistaCliente.cancelar();
                           break;
                       }
                        nombre = vistaCliente.getTextoName();
                        provincia = vistaCliente.getComboProvincia();
                        canton = vistaCliente.getComboCanton();
                        distrito = vistaCliente.getComboDistrito();


                        if (String.valueOf(idCliente).isEmpty() || vistaCliente.getTextoName().isBlank() ||
                                vistaCliente.getProvinciaSelected() == null || vistaCliente.getSelectedDistrito() == null ||
                                vistaCliente.getSelectedCanton() == null) {
                            JOptionPane.showMessageDialog(null, "Debes Ingresar todos los datos requeridos","Atencion", JOptionPane.INFORMATION_MESSAGE);

                        }
                        else {
                            cliente = new Cliente(idCliente,nombre,provincia,distrito,canton);

                            if (ctrl.buscaCliente(cliente.getId()) == null){
                                ctrl.addCliente(cliente);
                                table = new JTable();
                                table.setModel(ctrl.getModeloTablaCliente());
                                vistaCliente.setTable(table);
                                vistaCliente.addListeners(new ListenerHandler());
                                ctrl.guardar();
                                vistaCliente.cancelar();

                            }else {
                                JOptionPane.showMessageDialog(null, "Ya existe un cliente con esa informacion","Atencion", JOptionPane.INFORMATION_MESSAGE);

                            }
                        }


                    } catch (Exception exception) {

                    }
                }

                case "1-10" ->{
                    //cancelar
                    vistaCliente.cancelar();
                }

                case "1-0" ->
                        //Regresar
                        {
                            vistaCliente.dispose();
                            ctrl.setVisible();
                        }
                case "1-1" ->
                        //Agregar cliente
                        {
                            //Agregar excepciones y obtener información de los botones
                            //campos de texto
                            vistaCliente.mainContentHandler(1, new ListenerHandler(), mapa);
                            vistaCliente.clearFields();

                        }
                case "1-2" ->
                        //Buscar cliente
                        {
                            vistaCliente.mainContentHandler(2, new ListenerHandler(), null);
                            vistaCliente.clearFields();
                        }
                case "1-3" ->
                        //Listado de clientes
                        {
                            vistaCliente.mainContentHandler(3, new ListenerHandler(), null);
                        }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {


            if (e.getClickCount() >= 2){
                int selectedRow = vistaCliente.getSelectedRow();
                int idCliente = ctrl.getIdClienteTabla(selectedRow,0);


                Cliente cliente = ctrl.buscaCliente(idCliente);
                vistaCliente.mostrarVentantaInfoCLiente(cliente);
            }


        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

     public static class MousePositionListener implements MouseMotionListener, MouseListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
           mapCreator.refresh();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //https://docs.oracle.com/javase/7/docs/api/java/awt/Rectangle.html
            int i = mapCreator.isInThisArea(e.getX(),e.getY());
            vistaCliente.setprovinciaMapa(i);
            if (i != -1){
                vistaCliente.cargarCantones(i);
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {


        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}