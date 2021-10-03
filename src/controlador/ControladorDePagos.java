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

            switch (valor){
                case "3-0":
                {
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
                    try{
                        if(vistaPagos.getTextoId().isBlank()){
                            throw new Exception("Existen campos de texto vacios");
                        }
                        else{
                            try{
                                idCliente = Integer.parseInt(vistaPagos.getTextoId());
                            }
                            catch (NumberFormatException exception){
                                vistaPagos.leerError("Solo se aceptan numeros para determinadas variables");
                                vistaPagos.clearFields();
                                break;
                            }
                            try{
                                if(!ctrl.getModelo().clienteEstaRegistrado(idCliente)){
                                    throw new Exception("El usuario indicado no se encuentra registrado en el sistema");
                                }
                                else{
                                    Cliente cliente = ctrl.getModelo().getClientePorID(idCliente);
                                    Prestamo prestamo = ctrl.getModelo().getAlgunPrestamo("3441");
                                    //ctrl.getModelo().pr(cliente);

                                }
                            }
                            catch (Exception exception){
                                vistaPagos.leerError(exception.getMessage());
                                vistaPagos.clearFields();
                                break;
                            }
                        }
                    }
                    catch (Exception exception){
                        vistaPagos.leerError(exception.getMessage());
                        vistaPagos.clearFields();
                        break;
                    }
                    //vistaPagos.clearFields();
                }
                break;
                case "3-1-1":
                    //Agregar Pago boton
                {
                    try{
                        if(vistaPagos.getTextoId().isBlank() || vistaPagos.getTextoNumero().isBlank() ||
                            vistaPagos.getTextoMontoPagado().isBlank() || vistaPagos.getTextoTasaDeInteres().isBlank() ||
                            vistaPagos.getTextoAmortizacion().isBlank() || vistaPagos.getTextoPrestamo().isBlank() ||
                            vistaPagos.getTextoPago().isBlank()){
                            throw new Exception("Existen campos de texto vacios");
                        }
                        else{
                            try{
                                numeroPago = Integer.parseInt(vistaPagos.getTextoNumero());
                                idCliente = Integer.parseInt(vistaPagos.getTextoId());
                                idPrestamo = vistaPagos.getTextoPrestamo();
                                idPago = vistaPagos.getTextoPago();
                                montoPagado = Double.parseDouble(vistaPagos.getTextoMontoPagado());
                                interes = Double.parseDouble(vistaPagos.getTextoTasaDeInteres());
                                amortizacion = Double.parseDouble(vistaPagos.getTextoAmortizacion());
                            }
                            catch (NumberFormatException exception){
                                vistaPagos.leerError("Solo se aceptan numeros para determinadas variables");
                                vistaPagos.clearFields();
                                break;
                            }
                            try{
                                if(!ctrl.getModelo().clienteEstaRegistrado(idCliente)){
                                    throw new Exception("El usuario indicado no se encuentra en el sistema");
                                }
                                else{
                                    //Cliente cliente = ctrl.getModelo().getAlgunCliente(idCliente);
                                    ctrl.getModelo().cancelacionDeCuota(idPrestamo, numeroPago, montoPagado, interes, amortizacion);
                                }
                            }
                            catch (Exception exception){
                                vistaPagos.leerError(exception.getMessage());
                                vistaPagos.clearFields();
                                break;
                            }
                        }
                    }
                    catch (Exception exception){
                        vistaPagos.leerError(exception.getMessage());
                        vistaPagos.clearFields();
                        break;
                    }
                    //vistaPagos.clearFields();
                }
                break;
                case "3-2":
                //Buscar Pago
                {
                    vistaPagos.mainContentHandler(2, new ListenerHandler());
                    vistaPagos.clearFields();
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
            if(e.getSource().equals(jTable)){
                vistaPagos.setTextoPrestamo(String.valueOf(jTable.getValueAt(jTable.getSelectedRow(),0)));
                vistaPagos.setTextoMonto(String.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 4)));
                vistaPagos.setTextoInteres(String.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 2)));

                vistaPagos.getIdTextField().setEditable(false);
                vistaPagos.getPrestamoTextField().setEditable(false);
                vistaPagos.getTasaDeInteresTextField().setEditable(false);
                vistaPagos.getAmortizacionTextField().setEditable(true);
                vistaPagos.getMontoPagadoTextField().setEditable(true);
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
