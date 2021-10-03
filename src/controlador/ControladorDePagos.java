package controlador;

import modelo.cliente.Cliente;
import vista.VistaPagos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorDePagos {
    private VistaPagos vistaPagos;
    private Controlador ctrl;

    public ControladorDePagos(Controlador c) {
        ctrl = c;
        vistaPagos = new VistaPagos();
        vistaPagos.addComponents(new ListenerHandler());

    }

    private class ListenerHandler implements ActionListener {
        //int numeroDePago, double montoPagado, double interes, double amortizacion
        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            int numeroPago = 0;
            int idCliente = 0;
            String idPrestamo = null;
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
                    //Agregar Pago, configura prestamos
                {
                    try{
                        Cliente cliente = ctrl.getModelo().getAlgunCliente(idCliente);
                        if(cliente == null){
                            throw new Exception("El usuario indicado no se enuentra registrado en el sistema");
                        }
                        else{
                            try{
                                ArrayList<String> prestamos = ctrl.getModelo().retornaPrestamosActivos(cliente);
                                if(prestamos == null){
                                    throw new Exception("El usuario no cuenta con prestamos vigentes");
                                }
                                else{
                                    vistaPagos.configuraPrestamosBoton(ctrl.getModelo().retornaPrestamosActivos(cliente));
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
                }
                break;
                case "3-1-1":
                    //Agregar Pago boton
                {
                    try{
                        if(vistaPagos.getTextoId().isBlank() || vistaPagos.getTextoNumero().isBlank() ||
                            vistaPagos.getTextoMontoPagado().isBlank() || vistaPagos.getTextoTasaDeInteres().isBlank() ||
                            vistaPagos.getTextoAmortizacion().isBlank() || vistaPagos.getPrestamosButton().getSelectedItem() == null){
                            throw new Exception("Existen campos de texto vacios");
                        }
                        else{
                            try{
                                numeroPago = Integer.parseInt(vistaPagos.getTextoNumero());
                                idCliente = Integer.parseInt(vistaPagos.getTextoId());
                                idPrestamo = vistaPagos.getTextoPrestamo();
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
                                    Cliente cliente = ctrl.getModelo().getAlgunCliente(idCliente);
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
                case "3-3":
                //Listado de pagos
                {
                    vistaPagos.mainContentHandler(3, new ListenerHandler());
                }
                break;
            }
        }
    }
}
