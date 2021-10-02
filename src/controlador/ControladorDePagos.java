package controlador;

import vista.VistaPagos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDePagos {
    private VistaPagos vistaPagos;

    public ControladorDePagos() {
        vistaPagos = new VistaPagos();
        vistaPagos.addComponents(new ListenerHandler());
    }

    private class ListenerHandler implements ActionListener {
        //int numeroDePago, double montoPagado, double interes, double amortizacion
        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            int numeroPago = 0;
            double montoPagado = 0.0;
            double interes = 0.0;
            double amortizacion = 0.0;

            switch (valor){
                case "3-0":
                {
                    vistaPagos.dispose();
                    new Controlador();
                }
                break;
                case "3-1":
                //Agregar Pago
                {
                    vistaPagos.mainContentHandler(1, new ListenerHandler());
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
