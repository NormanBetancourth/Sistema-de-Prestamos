package controlador;

import modelo.cliente.Cliente;
import modelo.pago.ModeloTablaPagos;
import modelo.prestamo.ModeloTablaPrestamos;
import modelo.prestamo.Prestamo;
import vista.VistaPagos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ControladorDePagos {
    private VistaPagos vistaPagos;
    private Controlador ctrl;

    public ControladorDePagos(Controlador c) {
        ctrl = c;
        vistaPagos = new VistaPagos();
        vistaPagos.addComponents(new ListenerHandler());
        datosBase();
    }

    //BORRAR
    public void datosBase() {
        ctrl.getModelo().registrarCliente(111, "Rebeca1", "H", "H", "H");
        ctrl.getModelo().registrarCliente(222, "Rebeca2", "H", "H", "H");
        ctrl.getModelo().registrarCliente(333, "Rebeca3", "H", "H", "H");
        ctrl.getModelo().registrarCliente(444, "Rebeca4", "H", "H", "H");

        Cliente cliente1 = ctrl.getModelo().getClientePorID(111);
        Cliente cliente2 = ctrl.getModelo().getClientePorID(222);
        Cliente cliente3 = ctrl.getModelo().getClientePorID(333);
        Cliente cliente4 = ctrl.getModelo().getClientePorID(444);

        ctrl.getModelo().registrarPrestamoAUnCliente(cliente1, 200, 0.2, 2);
        ctrl.getModelo().registrarPrestamoAUnCliente(cliente2, 200, 0.2, 100);
        ctrl.getModelo().registrarPrestamoAUnCliente(cliente3, 200, 0.2, 100);
        ctrl.getModelo().registrarPrestamoAUnCliente(cliente4, 200, 0.2, 100);
    }

    private class ListenerHandler implements ActionListener, MouseListener {
        //int numeroDePago, double montoPagado, double interes, double amortizacion
        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            int numeroPago = 0;
            int idCliente = 0;
            String idPrestamo = null;
            String idPago = null;
            double montoPagado = 0.0;
            double interes = 0.0;
            double amortizacion = 0.0;

            switch (valor) {
                case "3-0": {
                    vistaPagos.dispose();
                    ctrl.setVisible();
                }
                break;
                case "3-1":
                    //Agregar Pago
                {
                    vistaPagos.mainContentHandler(1, new ListenerHandler());
                    vistaPagos.clearFields();
                }
                break;
                case "3-1-0":
                    //Agregar Pago
                {
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
                                    //Prestamo prestamo = ctrl.getModelo().getAlgunPrestamo("3441");
                                    ModeloTablaPrestamos modelo = ctrl.getModelo().configuraModelo(cliente);
                                    vistaPagos.setModeloTablaPrestamos(modelo);
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
                        //vistaPagos.setEditableButtons();
                        break;
                    }
                }
                break;
                case "3-1-1":
                    //Agregar Pago boton
                {
                    try {
                        if (vistaPagos.getTextoId().isBlank() || vistaPagos.getTextoNumero().isBlank() ||
                                vistaPagos.getTextoMontoPagado().isBlank() || vistaPagos.getTextoTasaDeInteres().isBlank() ||
                                vistaPagos.getTextoAmortizacion().isBlank() || vistaPagos.getTextoPrestamo().isBlank()) {
                            throw new Exception("Existen campos de texto vacios");
                        } else {
                            try {
                                numeroPago = Integer.parseInt(vistaPagos.getTextoNumero());
                                idCliente = Integer.parseInt(vistaPagos.getTextoId());
                                idPrestamo = vistaPagos.getTextoPrestamo();
                                idPago = vistaPagos.getTextoPago();
                                montoPagado = Double.parseDouble(vistaPagos.getTextoMontoPagado());
                                interes = Double.parseDouble(vistaPagos.getTextoTasaDeInteres());
                                amortizacion = Double.parseDouble(vistaPagos.getTextoAmortizacion());
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
                                    ctrl.getModelo().cancelacionDeCuota(idPrestamo, numeroPago, montoPagado, interes, amortizacion);
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
                    vistaPagos.setEditableButtons();
                    vistaPagos.clearFields();
                }
                break;
                case "3-2":
                    //Buscar Pago
                {
                    vistaPagos.mainContentHandler(2, new ListenerHandler());
                    vistaPagos.clearFields();
                }
                break;
                case "3-2-0":
                    //Buscar Pago
                {
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
                                    //Prestamo prestamo = ctrl.getModelo().getAlgunPrestamo("3441");
                                    ModeloTablaPrestamos modelo = ctrl.getModelo().configuraModelo(cliente);
                                    vistaPagos.setModeloTablaPrestamos(modelo);
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
                break;
                case "3-2-1":
                    //Buscar Pago
                {
                    Cliente cliente = ctrl.getModelo().getClientePorID(idCliente);
                    Prestamo prestamo = ctrl.getModelo().getAlgunPrestamo("3441");
                    ModeloTablaPagos modelo = ctrl.getModelo().configuraModelo(prestamo);
                    vistaPagos.setModeloTabla(modelo);
                }
                break;
                case "3-3":
                    //Listado de pagos
                {
                    vistaPagos.mainContentHandler(3, new ListenerHandler());
                }
                break;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JTable jTable = vistaPagos.getTabla();
            if (e.getSource().equals(jTable)) {
                int selectedRow = jTable.getSelectedRow();
                vistaPagos.setTextoPrestamo(String.valueOf(jTable.getValueAt(selectedRow,0)));
                vistaPagos.setTextoMonto(String.valueOf(jTable.getValueAt(selectedRow, 4)));
                vistaPagos.setTextoInteres(String.valueOf(jTable.getValueAt(selectedRow, 2)));
                vistaPagos.getBoton().setText("Confirmar");
                vistaPagos.getBoton().setActionCommand("3-1-1");
                vistaPagos.getIdTextField().setEditable(false);
                vistaPagos.getPrestamoTextField().setEditable(false);
                vistaPagos.getTasaDeInteresTextField().setEditable(false);
                vistaPagos.getAmortizacionTextField().setEditable(true);
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
}