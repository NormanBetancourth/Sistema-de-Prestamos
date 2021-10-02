package controlador;
import modelo.mapHandler.SubMapHandler;
import modelo.mapHandler.mapHandler;
import vista.VistaCliente;

import javax.swing.*;
import java.awt.event.*;

public class ControladorDeClientes {
    private static VistaCliente vistaCliente;
    private JPanel mapa;
    private static mapHandler mapCreator;
    private Controlador ctrl;
    public ControladorDeClientes(mapHandler mapC, Controlador c) {
        ctrl = c;
        mapCreator = mapC;
        mapa = (JPanel) mapCreator.getUI();
        vistaCliente = new VistaCliente();
        vistaCliente.addComponents(new ListenerHandler(), mapa);
    }

    public void setMapa(JPanel mapa) {
        this.mapa = mapa;
    }
    public void setProvincia(int i){
        vistaCliente.setprovinciaMapa(i);

    }


    private class ListenerHandler implements ActionListener {
        //Cliente: cédula, nombre y provincia, cantón y distrito de su dirección
        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            int cedula = 0;
            String nombre = null;
            String provincia = null;
            String canton = null;
            String distrito = null;

            switch (valor) {


                case "Canton" ->{
                    if (vistaCliente.getSelectedCanton() != null){
                        vistaCliente.cargarDistritos(vistaCliente.getSelectedCanton());
                    }
                    break;
                }

                case "1-0" ->
                        //Regresar
                        {
                            vistaCliente.dispose();
                            ctrl.setVisible();
                        }
                case "1-1" ->
                        //Agregar cliente
                        {
                            //Agregar excepciones y obtener información de los botones
                            //campos de texto
                            vistaCliente.mainContentHandler(1, new ListenerHandler(), mapa);
                            vistaCliente.clearFields();
                        }
                case "1-2" ->
                        //Buscar cliente
                        {
                            vistaCliente.mainContentHandler(2, new ListenerHandler(), null);
                            vistaCliente.clearFields();
                        }
                case "1-3" ->
                        //Listado de clientes
                        {
                            vistaCliente.mainContentHandler(3, new ListenerHandler(), null);
                        }
            }
        }
    }

     public static class MousePositionListener implements MouseMotionListener, MouseListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mapCreator.refresh();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println( mapCreator.isInThisArea(e.getX(),e.getY()));//https://docs.oracle.com/javase/7/docs/api/java/awt/Rectangle.html
            int i = mapCreator.isInThisArea(e.getX(),e.getY());
            vistaCliente.setprovinciaMapa(i);
            if (i != -1){
                vistaCliente.cargarCantones(i);
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
