package controlador;

import modelo.cliente.Cliente;
import vista.VistaPrestamos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDePrestamos {
    private VistaPrestamos vistaPrestamos;
    private Controlador ctrl;

    public ControladorDePrestamos(Controlador c) {
        ctrl = c;
        vistaPrestamos = new VistaPrestamos();
        vistaPrestamos.addComponents(new ListenerHandler0());
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
                                            ctrl.guardar();
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
