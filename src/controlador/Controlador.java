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
        HiloMapa h1 = new HiloMapa();
        h1.start();
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
                        try {
                            if (mapCreator != null){
                                Home.dispose();
                                controladorDeClientes = new ControladorDeClientes(mapCreator, Controlador.this);
                            }else {
                                throw  new Exception("Por favor espere unos segundos mientras se carga el mapa");
                            }

                        }catch (Exception xe){
                            JOptionPane.showMessageDialog(null, xe.getMessage(),"Atencion", JOptionPane.INFORMATION_MESSAGE);
                        }
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

    private class HiloMapa extends Thread{

        @Override
        public void run() {
            mapCreator = new mapHandler(new ControladorDeClientes.MousePositionListener(), new ControladorDeClientes.MousePositionListener());

        }

    }



}
