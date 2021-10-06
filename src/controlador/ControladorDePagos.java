package controlador;

import modelo.cliente.Cliente;
import modelo.pago.ModeloTablaPagos;
import modelo.pago.Pago;
import modelo.prestamo.ModeloTablaPrestamos;
import modelo.prestamo.Prestamo;
import vista.VistaPagos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorDePagos {
    private VistaPagos vistaPagos;
    private Controlador ctrl;

    public ControladorDePagos(Controlador c) {
        ctrl = c;
        vistaPagos = new VistaPagos();
        vistaPagos.addComponents(new ListenerHandler0());
    }

    public void actualizaTablaPrestamos(Cliente cliente) {
        ModeloTablaPrestamos modelo = ctrl.getModelo().configuraModelo(cliente);
        vistaPagos.setModeloTablaPrestamos(modelo);
    }

    // Cambiar vista de acuerdo al botón seleccionado
    private class ListenerHandler0 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            switch (valor) {
                case "3-0" -> {
                    vistaPagos.dispose();
                    ctrl.setVisible();
                }
                case "3-1" -> {
                    vistaPagos.mainContentHandler(1, new ListenerHandler1());
                    vistaPagos.clearFields();
                }
                case "3-2" -> {
                    vistaPagos.mainContentHandler(2, new ListenerHandler2());
                    vistaPagos.clearFields();
                }
            }
        }
    }

    // AGREGAR PAGO
    private class ListenerHandler1 implements ActionListener, MouseListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            int idCliente = 0;
            String idPrestamo = null;
            double montoPagado = 0.0;

            switch (valor) {
                case "3-1-0" -> {
                    try {
                        if (vistaPagos.getTextoId().isBlank()) {
                            throw new Exception("Existen campos de texto vacios");
                        } else {
                            try {
                                idCliente = Integer.parseInt(vistaPagos.getTextoId());
                            } catch (NumberFormatException exception) {
                                vistaPagos.leerError("Solo se aceptan numeros para determinadas variables");
                                vistaPagos.clearFields();
                                vistaPagos.setEditableButtons();
                                break;
                            }
                            try {
                                if (!ctrl.getModelo().clienteEstaRegistrado(idCliente)) {
                                    throw new Exception("El usuario indicado no se encuentra registrado en el sistema");
                                } else {
                                    Cliente cliente = ctrl.getModelo().getClientePorID(idCliente);
                                    actualizaTablaPrestamos(cliente);
                                }
                            } catch (Exception exception) {
                                vistaPagos.leerError(exception.getMessage());
                                vistaPagos.clearFields();
                                vistaPagos.setEditableButtons();
                                break;
                            }
                        }
                    } catch (Exception exception) {
                        vistaPagos.leerError(exception.getMessage());
                        vistaPagos.clearFields();
                        vistaPagos.setEditableButtons();
                        break;
                    }
                }
                case "3-1-1" ->
                        //Confirmar Pago
                        {
                            try {
                                if (vistaPagos.getTextoId().isBlank() || vistaPagos.getTextoNumero().isBlank() ||
                                        vistaPagos.getTextoMontoPagado().isBlank() || vistaPagos.getTextoPrestamo().isBlank()) {
                                    throw new Exception("Existen campos de texto vacios");
                                } else {
                                    try {
                                        idCliente = Integer.parseInt(vistaPagos.getTextoId());
                                        idPrestamo = vistaPagos.getTextoPrestamo();
                                        montoPagado = Double.parseDouble(vistaPagos.getTextoMontoPagado());
                                    } catch (NumberFormatException exception) {
                                        vistaPagos.leerError("Solo se aceptan numeros para determinadas variables");
                                        vistaPagos.clearFields();
                                        vistaPagos.setEditableButtons();
                                        break;
                                    }
                                    try {
                                        if (!ctrl.getModelo().clienteEstaRegistrado(idCliente)) {
                                            throw new Exception("El usuario indicado no se encuentra en el sistema");
                                        } else {
                                            if (!ctrl.getModelo().isPrestamoActivo(idPrestamo)) {
                                                throw new Exception("El prestamo ya ha sido completamente cancelado");
                                            }
                                            ctrl.getModelo().cancelacionDeCuota(idPrestamo, montoPagado);
                                        }
                                    } catch (Exception exception) {
                                        vistaPagos.leerError(exception.getMessage());
                                        vistaPagos.clearFields();
                                        vistaPagos.setEditableButtons();
                                        break;
                                    }
                                }
                            } catch (Exception exception) {
                                vistaPagos.leerError(exception.getMessage());
                                vistaPagos.clearFields();
                                vistaPagos.setEditableButtons();
                                break;
                            }
                            vistaPagos.getBoton2().setEnabled(false);
                            vistaPagos.setEditableButtons();
                            vistaPagos.clearFields();
                            vistaPagos.getBoton().setActionCommand("3-1-0");
                        }
                case "3-1-2" -> {
                    vistaPagos.getBoton().setActionCommand("3-1-0");
                    vistaPagos.clearFields();
                    vistaPagos.setEditableButtons();
                    vistaPagos.getBoton2().setEnabled(false);
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JTable jTable = vistaPagos.getTabla();
            if (e.getSource().equals(jTable)) {
                int selectedRow = jTable.getSelectedRow();
                vistaPagos.setTextoPrestamo(String.valueOf(jTable.getValueAt(selectedRow, 0)));
                vistaPagos.setTextoMonto(String.valueOf(jTable.getValueAt(selectedRow, 4)));
                vistaPagos.getBoton().setText("Confirmar");
                vistaPagos.getBoton().setActionCommand("3-1-1");
                vistaPagos.getBoton2().setEnabled(true);
                vistaPagos.getIdTextField().setEditable(false);
                vistaPagos.getMontoPagadoTextField().setEditable(true);
                String id = vistaPagos.getTextoPrestamo();
                Prestamo prestamo = ctrl.getModelo().getAlgunPrestamo(id);
                String numeroString = String.valueOf(prestamo.numeroDePagos() + 1);
                vistaPagos.setTextoNumero(numeroString);
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

    //LISTAR PAGOS
    private class ListenerHandler2 implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String idPago = null;
            int idCliente = 0;
            String valor = e.getActionCommand();
            switch (valor) {
                case "3-2-0" -> {
                    try {
                        if (vistaPagos.getTextoId().isBlank()) {
                            throw new Exception("Existen campos de texto vacios");
                        } else {
                            try {
                                idCliente = Integer.parseInt(vistaPagos.getTextoId());
                            } catch (NumberFormatException exception) {
                                vistaPagos.leerError("Solo se aceptan numeros para determinadas variables");
                                vistaPagos.clearFields();
                                vistaPagos.setEditableButtons();
                                break;
                            }
                            try {
                                if (!ctrl.getModelo().clienteEstaRegistrado(idCliente)) {
                                    throw new Exception("El usuario indicado no se encuentra registrado en el sistema");
                                } else {
                                    Cliente cliente = ctrl.getModelo().getClientePorID(idCliente);
                                    actualizaTablaPrestamos(cliente);
                                }
                            } catch (Exception exception) {
                                vistaPagos.leerError(exception.getMessage());
                                vistaPagos.clearFields();
                                break;
                            }
                        }
                    } catch (Exception exception) {
                        vistaPagos.leerError(exception.getMessage());
                        vistaPagos.clearFields();
                        break;
                    }
                }
                case "3-2-1" -> {
                    String codigo = vistaPagos.getTextoPrestamo();
                    Prestamo prestamo = ctrl.getModelo().getAlgunPrestamo(codigo);
                    vistaPagos.mostrarVentantaInfoPago(prestamo);
                    vistaPagos.setEditableButtons();
                    vistaPagos.clearFields();
                    vistaPagos.getBoton2().setEnabled(false);
                    vistaPagos.getBoton().setActionCommand("3-2-0");
                }
                case "3-2-2" ->{
                    vistaPagos.getBoton().setActionCommand("3-2-0");
                    vistaPagos.clearFields();
                    vistaPagos.setEditableButtons();
                    vistaPagos.getBoton2().setEnabled(false);
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JTable jTable = vistaPagos.getTabla();
            if (e.getSource().equals(jTable)) {
                int selectedRow = jTable.getSelectedRow();
                vistaPagos.setTextoPrestamo(String.valueOf(jTable.getValueAt(selectedRow, 0)));
                vistaPagos.getBoton().setText("Confirmar");
                vistaPagos.getBoton().setActionCommand("3-2-1");
                vistaPagos.getIdTextField().setEditable(false);
                vistaPagos.getBoton2().setEnabled(true);
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