package controlador;

import modelo.cliente.Cliente;
import modelo.prestamo.ModeloTablaPrestamos;
import modelo.prestamo.Prestamo;
import vista.VistaPrestamos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorDePrestamos {
    private VistaPrestamos vistaPrestamos;
    private Controlador ctrl;

    public ControladorDePrestamos(Controlador c) {
        ctrl = c;
        vistaPrestamos = new VistaPrestamos();
        vistaPrestamos.addComponents(new ListenerHandler0());
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

        ctrl.getModelo().registrarPrestamoAUnCliente(cliente1, 10000, 0.06, 6);
        ctrl.getModelo().registrarPrestamoAUnCliente(cliente2, 2000, 0.02, 100);
        ctrl.getModelo().registrarPrestamoAUnCliente(cliente3, 20000, 0.01, 100);
        ctrl.getModelo().registrarPrestamoAUnCliente(cliente4, 200, 0.03, 100);
    }

    private class ListenerHandler0 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            switch (valor) {
                case "2-0" -> {
                    vistaPrestamos.dispose();
                    ctrl.setVisible();
                }
                case "2-1" -> {
                    vistaPrestamos.mainContentHandler(1, new ControladorDePrestamos.ListenerHandler1());
                    vistaPrestamos.clearFields();
                }
                case "2-2" -> {
                    vistaPrestamos.mainContentHandler(2, new ControladorDePrestamos.ListenerHandler2());
                    vistaPrestamos.clearFields();
                }
            }
        }
    }

    private class ListenerHandler1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            String nombre = null;
            int idCliente = 0;
            double monto = 0.0;
            double interes = 0.0;
            int plazo = 0;

            switch (valor) {
                case "2-1-0" -> {
                    try {
                        if (vistaPrestamos.getTextoId().isBlank()) {
                            throw new Exception("Existen campos de texto vacios");
                        } else {
                            try {
                                idCliente = Integer.parseInt(vistaPrestamos.getTextoId());
                            } catch (NumberFormatException exception) {
                                vistaPrestamos.leerError("Solo se aceptan numeros para determinadas variables");
                                vistaPrestamos.clearFields();
                                break;
                            }
                            try {
                                if (!ctrl.getModelo().clienteEstaRegistrado(idCliente)) {
                                    throw new Exception("El usuario indicado no se encuentra registrado en el sistema");
                                } else {
                                    vistaPrestamos.setEditableFieldsTrue();
                                    vistaPrestamos.getBoton1().setActionCommand("2-1-2");
                                    vistaPrestamos.getBoton1().setText("Confirmar");
                                    vistaPrestamos.getBoton2().setEnabled(true);
                                }
                            } catch (Exception exception) {
                                vistaPrestamos.leerError(exception.getMessage());
                                vistaPrestamos.clearFields();
                                vistaPrestamos.getBoton1().setActionCommand("2-1-0");
                                vistaPrestamos.getBoton1().setText("Enviar");
                                vistaPrestamos.getBoton2().setEnabled(false);
                                vistaPrestamos.setEditableFieldsFalse();
                                break;
                            }
                        }
                    } catch (Exception exception) {
                        vistaPrestamos.leerError(exception.getMessage());
                        vistaPrestamos.clearFields();
                        vistaPrestamos.getBoton1().setActionCommand("2-1-0");
                        vistaPrestamos.getBoton1().setText("Enviar");
                        vistaPrestamos.getBoton2().setEnabled(false);
                        vistaPrestamos.setEditableFieldsFalse();
                        break;
                    }
                }
                case "2-1-1" -> {
                    vistaPrestamos.setEditableFieldsFalse();
                    vistaPrestamos.clearFields();
                    vistaPrestamos.getBoton1().setActionCommand("2-1-0");
                    vistaPrestamos.getBoton1().setText("Enviar");
                    vistaPrestamos.getBoton2().setEnabled(false);
                }
                case "2-1-2" ->
                        // Agregar prestamo boton
                        {
                            try {
                                if (vistaPrestamos.getTextoId().isBlank() || vistaPrestamos.getTextoMonto().isBlank() ||
                                        vistaPrestamos.getTextoTasaDeInteres().isBlank() || vistaPrestamos.getTextoPlazo().isBlank()) {
                                    throw new Exception("Existen campos de texto vacios");
                                } else {
                                    try {
                                        idCliente = Integer.parseInt(vistaPrestamos.getTextoId());
                                        monto = Double.parseDouble(vistaPrestamos.getTextoMonto());
                                        interes = Double.parseDouble(vistaPrestamos.getTextoTasaDeInteres());
                                        plazo = Integer.parseInt(vistaPrestamos.getTextoPlazo());
                                    } catch (NumberFormatException exception) {
                                        vistaPrestamos.leerError("Solo se aceptan numeros para determinadas variables");
                                        vistaPrestamos.setEditableFieldsFalse();
                                        vistaPrestamos.clearFields();
                                        vistaPrestamos.getBoton1().setActionCommand("2-1-0");
                                        vistaPrestamos.getBoton1().setText("Enviar");
                                        vistaPrestamos.getBoton2().setEnabled(false);
                                        break;
                                    }
                                    try {
                                        if (!ctrl.getModelo().clienteEstaRegistrado(idCliente)) {
                                            throw new Exception("El usuario indicado no se encuentra en el sistema");
                                        } else {
                                            Cliente cliente = ctrl.getModelo().getClientePorID(idCliente);
                                            ctrl.getModelo().registrarPrestamoAUnCliente(cliente, monto, interes, plazo);
                                        }
                                    } catch (Exception exception) {
                                        vistaPrestamos.leerError(exception.getMessage());
                                        vistaPrestamos.setEditableFieldsFalse();
                                        vistaPrestamos.clearFields();
                                        vistaPrestamos.getBoton1().setActionCommand("2-1-0");
                                        vistaPrestamos.getBoton1().setText("Enviar");
                                        vistaPrestamos.getBoton2().setEnabled(false);
                                        break;
                                    }
                                }
                            } catch (Exception exception) {
                                vistaPrestamos.leerError(exception.getMessage());
                                vistaPrestamos.setEditableFieldsFalse();
                                vistaPrestamos.clearFields();
                                vistaPrestamos.getBoton1().setActionCommand("2-1-0");
                                vistaPrestamos.getBoton1().setText("Enviar");
                                vistaPrestamos.getBoton2().setEnabled(false);
                                break;
                            }
                            vistaPrestamos.setEditableFieldsFalse();
                            vistaPrestamos.clearFields();
                            vistaPrestamos.getBoton1().setActionCommand("2-1-0");
                            vistaPrestamos.getBoton1().setText("Enviar");
                            vistaPrestamos.getBoton2().setEnabled(false);
                        }
            }
        }
    }

    private class ListenerHandler2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            int idCliente = 0;
            Cliente cliente = null;
            switch (valor) {
                case "2-2-0" -> {
                    try {
                        if (vistaPrestamos.getTextoId().isBlank()) {
                            throw new Exception("Existen campos de texto vacios");
                        } else {
                            try {
                                idCliente = Integer.parseInt(vistaPrestamos.getTextoId());
                            } catch (NumberFormatException exception) {
                                vistaPrestamos.leerError("Solo se aceptan numeros para determinadas variables");
                                vistaPrestamos.clearFields();
                                break;
                            }
                            try {
                                if (!ctrl.getModelo().clienteEstaRegistrado(idCliente)) {
                                    throw new Exception("El usuario indicado no se encuentra registrado en el sistema");
                                } else {
                                    idCliente = Integer.parseInt(vistaPrestamos.getTextoId());
                                    cliente = ctrl.getModelo().getClientePorID(idCliente);
                                    vistaPrestamos.mostrarVentanaInfoPrestamos(cliente);
                                    vistaPrestamos.clearFields();
                                }
                            } catch (Exception exception) {
                                vistaPrestamos.leerError(exception.getMessage());
                                vistaPrestamos.clearFields();
                                break;
                            }
                        }
                    } catch (Exception exception) {
                        vistaPrestamos.leerError(exception.getMessage());
                        vistaPrestamos.clearFields();
                        break;
                    }
                    vistaPrestamos.clearFields();
                }
                case "2-2-1" -> {
                    vistaPrestamos.clearFields();
                }
            }
        }

    }
}
