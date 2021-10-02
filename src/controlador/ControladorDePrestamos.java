package controlador;

import vista.VistaPrestamos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDePrestamos {
    private VistaPrestamos vistaPrestamos;

    public ControladorDePrestamos() {
        vistaPrestamos = new VistaPrestamos();
        vistaPrestamos.addComponents(new ListenerHandler());
    }

    private class ListenerHandler implements ActionListener{
        // double monto, double tasaDeInteres, int plazo
        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            double monto = 0.0;
            double interes = 0.0;
            int plazo = 0;

            switch (valor) {
                case "2-0" ->
                        //Regresar
                        {
                            vistaPrestamos.dispose();
                            new Controlador();
                        }
                case "2-1" ->
                        //Agregar prestamo
                        {
                            vistaPrestamos.mainContentHandler(1, new ListenerHandler());
                            vistaPrestamos.clearFields();
                        }
                case "2-2" ->
                        //Buscar prestamo
                        {
                            vistaPrestamos.mainContentHandler(2, new ListenerHandler());
                            vistaPrestamos.clearFields();
                        }
                case "2-3" ->
                        //Listado de prestamos
                        {
                            vistaPrestamos.mainContentHandler(3, new ListenerHandler());
                        }
            }
        }
    }
}
