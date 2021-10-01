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
            String monto = null;
            String interes = null;
            String plazo = null;

            switch (valor){
                case "2-0":
                //Regresar
                {
                    vistaPrestamos.dispose();
                    new Controlador();
                }
                break;
                case "2-1":
                //Agregar prestamo
                {
                    vistaPrestamos.mainContentHandler(1, new ListenerHandler());
                    vistaPrestamos.clearFields();
                }
                break;
                case "2-2":
                //Buscar prestamo
                {
                    vistaPrestamos.mainContentHandler(2, new ListenerHandler());
                    vistaPrestamos.clearFields();
                }
                break;
                case "2-3":
                //Listado de prestamos
                {

                }
                break;
            }
        }
    }
}
