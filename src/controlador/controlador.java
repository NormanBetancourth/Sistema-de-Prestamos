package controlador;

import modelo.ModelHandler;
import modelo.mapHandler.mapHandler;
import vista.busquedaRegistroClientes.busquedaRegistroClientes;
import vista.homeFrame;

import javax.swing.*;
import java.awt.event.*;

public class controlador {

private homeFrame home;
private ModelHandler modelo;
private busquedaRegistroClientes subMenuRegistro;
//mapa
private mapHandler mapCreator;
private JPanel mapa;

    public controlador() {
        home = new homeFrame();
        modelo = new ModelHandler();
        home.addListener(new ListenerHandler());
        subMenuRegistro = new busquedaRegistroClientes();
        mapCreator = new mapHandler(new MousePositionListener(),new MousePositionListener());
        mapa = (JPanel) mapCreator.getUI();
    }


    public void addContent(){
        home.addComponents(new ListenerHandler());
    }

    public  class ListenerHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton aux;
            try {
                if (e.getActionCommand().equals("1")){
                    home.dispose();
                    subMenuRegistro.addComponents(new ListenerHandler(), mapa);

                }
                if (e.getActionCommand().equals("0-1")){
                    subMenuRegistro.dispose();
                    home = new homeFrame();
                    home.addComponents(new ListenerHandler());
                }
                //Todo opciones en agregar cliente


                if (e.getActionCommand().equals("1-1")){
                    subMenuRegistro.mainContentHandler(1,new ListenerHandler(),mapa);

                }
                if (e.getActionCommand().equals("1-2")){
                    subMenuRegistro.mainContentHandler(2,new ListenerHandler(), mapa);
                }




            }catch (Exception exception){
                System.out.println("Error");
            }

        }
    }

    class MousePositionListener implements MouseMotionListener, MouseListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

            mapCreator.refresh();



        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println( mapCreator.isInThisArea(e.getX(),e.getY()));;//https://docs.oracle.com/javase/7/docs/api/java/awt/Rectangle.html



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
