package controlador;

import modelo.Manager;
import modelo.mapHandler;
import vista.busquedaRegistroClientes;
import vista.homeFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controlador {

private homeFrame home;
private Manager modelo;
private busquedaRegistroClientes subMenuRegistro;
//mapa
private mapHandler mapCreator;
private JPanel mapa;

    public controlador() {
        home = new homeFrame();
        modelo = new Manager();
        home.addListener(new ListenerHandler());
        subMenuRegistro = new busquedaRegistroClientes();
        mapCreator = new mapHandler();
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
}
