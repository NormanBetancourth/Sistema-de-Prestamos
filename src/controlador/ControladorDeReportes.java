package controlador;

import modelo.cliente.Cliente;
import modelo.cliente.ModeloTablaCliente;
import modelo.prestamo.ModeloTablaPrestamos;
import modelo.prestamo.Prestamo;
import vista.VistaBuilder;
import vista.VistaReportes;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorDeReportes {
    private VistaReportes vistaReportes;
    private Controlador ctrl;

    public ControladorDeReportes(Controlador c){
        ctrl = c;
        vistaReportes = new VistaReportes();
        vistaReportes.addComponents(new ListenerHandler());
    }

    public void actualizaTablaCliente() {
        ModeloTablaCliente modelo = ctrl.getModeloTablaCliente();
        vistaReportes.setModeloTabla(modelo);
    }

    public void actualizaTablaPrestamos(){
        ModeloTablaPrestamos modeloTabla = new ModeloTablaPrestamos(ctrl.getTodoslosPrestamos());
        vistaReportes.setModeloTablaPrestamos(modeloTabla);
    }

    private class ListenerHandler implements ActionListener, MouseListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            int idCliente = 0;

            switch (valor){
                // Regresar a inicio
                case "4-0": {
                    vistaReportes.dispose();
                    ctrl.setVisible();
                }
                break;
                case "4-1":
                // cambiar vista reporte general
                {
                    vistaReportes.mainContentHandler(1, new ControladorDeReportes.ListenerHandler());
                    vistaReportes.clearFields();
                }
                break;
                case "4-1-0":
                    //Obtener reporte de todos los clientes
                {

                }
                break;
                case "4-1-1":
                // Enviar info para presentar tabla de prestamos
                {
                    try {
                        if (vistaReportes.getTextoId().isBlank()) {
                            throw new Exception("Existen campos de texto vacios");
                        } else {
                            try {
                                idCliente = Integer.parseInt(vistaReportes.getTextoId());
                            } catch (NumberFormatException exception) {
                                vistaReportes.leerError("Solo se aceptan numeros para determinadas variables");
                                vistaReportes.clearFields();
                                break;
                            }
                            try {
                                if (!ctrl.getModelo().clienteEstaRegistrado(idCliente)) {
                                    throw new Exception("El usuario indicado no se encuentra registrado en el sistema");
                                } else {
                                    Integer index = ctrl.getModelo().getIndexTablaCliente((String.valueOf(idCliente)));
                                    vistaReportes.seleccionarIntervalo(index, index);
                                    vistaReportes.getBoton().setActionCommand("4-1-1-Reporte");
                                    vistaReportes.getBoton().setText("Generar");
                                }
                            } catch (Exception exception) {
                                vistaReportes.leerError(exception.getMessage());
                                vistaReportes.clearFields();
                                break;
                            }
                        }
                    } catch (Exception exception) {
                        vistaReportes.leerError(exception.getMessage());
                        vistaReportes.clearFields();
                        break;
                    }
                    vistaReportes.clearFields();
                    break;
                }
                case "4-1-1-Reporte":
                {
                    // Realizar reporte de prestamos de un cliente
                    vistaReportes.quitarSeleccion();
                    vistaReportes.getBoton().setActionCommand("4-1-1");
                    vistaReportes.getBoton().setText("Enviar");
                    vistaReportes.getIdTextField().setEditable(true);
                    vistaReportes.clearFields();
                }
                break;
                case "4-2":
                {
                    // Vista reporte de un prestamo
                    vistaReportes.mainContentHandler(2, new ControladorDeReportes.ListenerHandler());
                    vistaReportes.clearFields();
                    actualizaTablaCliente();
                }
                break;
                case "4-3":
                {
                    // Vista reporte de pagos de un prestamo
                    vistaReportes.mainContentHandler(3, new ControladorDeReportes.ListenerHandler());
                    vistaReportes.clearFields();
                    actualizaTablaPrestamos();
                }
                break;
                case "Cancelacion":
                {
                    vistaReportes.getIdTextField().setEditable(true);
                    vistaReportes.clearFields();
                }
                break;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JTable jTable = vistaReportes.getTabla();
            if (e.getSource().equals(jTable)) {
                int selectedRow = jTable.getSelectedRow();
                vistaReportes.setTextoId(String.valueOf(jTable.getValueAt(selectedRow, 0)));
                vistaReportes.getBoton().setText("Generar");
                vistaReportes.getBoton().setActionCommand("4-1-1-Reporte");
                vistaReportes.getBoton2().setEnabled(true);
                vistaReportes.getIdTextField().setEditable(false);
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
