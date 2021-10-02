package controlador;

import modelo.ModelHandler;
import modelo.mapHandler.mapHandler;
import vista.HomeFrame;

import javax.swing.*;
import java.awt.event.*;

public class Controlador {
    HomeFrame Home;
    ModelHandler modelo;
    ControladorDeClientes controladorDeClientes;
    ControladorDePrestamos controladorDePrestamos;
    ControladorDePagos controladorDePagos;
    private mapHandler mapCreator;


    public Controlador() {
        Home = new HomeFrame();
        Home.addComponents(new ListenerHandler());
        mapCreator = new mapHandler(new ControladorDeClientes.MousePositionListener(), new ControladorDeClientes.MousePositionListener());
        modelo = new ModelHandler();
    }

    public ModelHandler getModelo() {
        return modelo;
    }

    public void setVisible() {
        Home.setVisible(true);
    }

    private  class ListenerHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String valor = e.getActionCommand();
                switch (valor) {
                    case "1" -> {
                        Home.dispose();
                        controladorDeClientes = new ControladorDeClientes(mapCreator, Controlador.this);
                    }
                    case "2" -> {
                        Home.dispose();
                        controladorDePrestamos = new ControladorDePrestamos(Controlador.this);
                    }
                    case "3" -> {
                        Home.dispose();
                        controladorDePagos = new ControladorDePagos(Controlador.this);
                    }
                }
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }


}
