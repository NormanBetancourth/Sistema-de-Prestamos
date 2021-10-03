package controlador;

import modelo.cliente.Cliente;
import modelo.cliente.ModeloTablaCliente;
import modelo.pago.ModeloTablaPagos;
import modelo.prestamo.ModeloTablaPrestamos;
import modelo.prestamo.Prestamo;
import vista.VistaPagos;
import vista.VistaPrestamos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDePrestamos {
    private VistaPrestamos vistaPrestamos;
    private Controlador ctrl;
    ModeloTablaPrestamos modeloTablaPrestamos;

    public ControladorDePrestamos(Controlador c) {
        ctrl = c;
        vistaPrestamos = new VistaPrestamos();
        vistaPrestamos.addComponents(new ListenerHandler());
    }

    public void configuraTablaPrestamos(Cliente cliente){
        //modeloTablaPrestamos = new ModeloTablaPrestamos(cliente.getListaDePrestamos());
        vistaPrestamos.setModeloTabla(modeloTablaPrestamos);
    }

    private class ListenerHandler implements ActionListener{
        // double monto, double tasaDeInteres, int plazo
        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            String nombre = null;
            int id = 0;
            double monto = 0.0;
            double interes = 0.0;
            int plazo = 0;

            switch (valor) {
                case "2-0" ->
                        //Regresar
                        {
                            vistaPrestamos.dispose();
                            ctrl.setVisible();
                        }
                case "2-1" ->
                        //Agregar prestamo vista
                        {
                            vistaPrestamos.mainContentHandler(1, new ListenerHandler());
                            vistaPrestamos.clearFields();
                        }
                case "2-1-1"->
                        // Agregar prestamo boton
                        {
                            try{
                                if(vistaPrestamos.getTextoId().isBlank() || vistaPrestamos.getTextoMonto().isBlank() ||
                                    vistaPrestamos.getTextoTasaDeInteres().isBlank() || vistaPrestamos.getTextoPlazo().isBlank()){
                                    throw new Exception("Existen campos de texto vacios");
                                }
                                else{
                                    try{
                                        id = Integer.parseInt(vistaPrestamos.getTextoId());
                                        monto = Double.parseDouble(vistaPrestamos.getTextoMonto());
                                        interes = Double.parseDouble(vistaPrestamos.getTextoTasaDeInteres());
                                        plazo = Integer.parseInt(vistaPrestamos.getTextoPlazo());
                                    }
                                    catch (NumberFormatException exception){
                                        vistaPrestamos.leerError("Solo se aceptan numeros para determinadas variables");
                                        vistaPrestamos.clearFields();
                                        break;
                                    }
                                    try{
                                        if(!ctrl.getModelo().clienteEstaRegistrado(id)){
                                            throw new Exception("El usuario indicado no se encuentra en el sistema");
                                        }
                                        else{
                                            //Cliente cliente = ctrl.getModelo().getAlgunCliente(id);
                                            //ctrl.getModelo().registrarPrestamoAUnCliente(cliente, monto, interes, plazo);
                                        }
                                    }
                                    catch (Exception exception){
                                        vistaPrestamos.leerError(exception.getMessage());
                                        vistaPrestamos.clearFields();
                                        break;
                                    }
                                }
                            }
                            catch (Exception exception){
                                vistaPrestamos.leerError(exception.getMessage());
                                vistaPrestamos.clearFields();
                                break;
                            }
                            vistaPrestamos.clearFields();
                        }
                case "2-2" ->
                        //Buscar prestamo vista
                        {
                            vistaPrestamos.mainContentHandler(2, new ListenerHandler());
                            vistaPrestamos.clearFields();
                        }
                case "2-3" ->
                        //Listado de prestamos vista
                        {
                            vistaPrestamos.mainContentHandler(3, new ListenerHandler());
                        }
            }
        }
    }
}
