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
        datosBase();
    }

    public void actualizaTablaPrestamos(Cliente cliente) {
        ModeloTablaPrestamos modelo = ctrl.getModelo().configuraModelo(cliente);
        vistaPagos.setModeloTablaPrestamos(modelo);
    }

    public void actualizaTablaPagos(Prestamo prestamo) {
        ModeloTablaPagos modelo = ctrl.getModelo().configuraModelo(prestamo);
        vistaPagos.setModeloTablaPagos(modelo);
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
            int numeroPago = 0;
            int idCliente = 0;
            String idPrestamo = null;
            double montoPagado = 0.0;
            double interes = 0.0;
            double amortizacion = 0.0;

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
                                        vistaPagos.getTextoMontoPagado().isBlank() || vistaPagos.getTextoTasaDeInteres().isBlank() ||
                                        vistaPagos.getTextoAmortizacion().isBlank() || vistaPagos.getTextoPrestamo().isBlank()) {
                                    throw new Exception("Existen campos de texto vacios");
                                } else {
                                    try {
                                        numeroPago = Integer.parseInt(vistaPagos.getTextoNumero());
                                        idCliente = Integer.parseInt(vistaPagos.getTextoId());
                                        idPrestamo = vistaPagos.getTextoPrestamo();
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
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JTable jTable = vistaPagos.getTabla();
            if (e.getSource().equals(jTable)) {
                int selectedRow = jTable.getSelectedRow();
                vistaPagos.setTextoPrestamo(String.valueOf(jTable.getValueAt(selectedRow, 0)));
                vistaPagos.setTextoMonto(String.valueOf(jTable.getValueAt(selectedRow, 4)));
                vistaPagos.setTextoInteres(String.valueOf(jTable.getValueAt(selectedRow, 2)));
                vistaPagos.getBoton().setText("Confirmar");
                vistaPagos.getBoton().setActionCommand("3-1-1");
                vistaPagos.getIdTextField().setEditable(false);
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

    //BUSCAR PAGO
    private class ListenerHandler2 implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String idPago = null;
            int idCliente = 0;
            String valor = e.getActionCommand();
            switch (valor) {
                case "3-2-0": {
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
                break;
                case "3-2-1": {
                    String codigo = vistaPagos.getTextoPrestamo();
                    Prestamo prestamo =  ctrl.getModelo().getAlgunPrestamo(codigo);
                    vistaPagos.mostrarVentantaInfoPago(prestamo);
                }
                break;
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
                String idPrestamo = vistaPagos.getTextoPrestamo();
                Prestamo prestamo = ctrl.getModelo().getAlgunPrestamo(idPrestamo);
                vistaPagos.getIdTextField().setEditable(false);
                actualizaTablaPagos(prestamo);
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