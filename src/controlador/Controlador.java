package controlador;

import modelo.ModelHandler;
import vista.HomeFrame;

import java.awt.event.*;

public class Controlador {
    HomeFrame Home;
    ModelHandler modelo;
    ControladorDeClientes controladorDeClientes;
    ControladorDePrestamos controladorDePrestamos;
    ControladorDePagos controladorDePagos;

    public Controlador() {
        Home = new HomeFrame();
        Home.addComponents(new ListenerHandler());
        modelo = new ModelHandler();
    }

    public ModelHandler getModelo() {
        return modelo;
    }

    public  class ListenerHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String valor = e.getActionCommand();
                switch (valor) {
                    case "1" -> {
                        Home.dispose();
                        controladorDeClientes = new ControladorDeClientes();
                    }
                    case "2" -> {
                        Home.dispose();
                        controladorDePrestamos = new ControladorDePrestamos();
                    }
                    case "3" -> {
                        Home.dispose();
                        controladorDePagos = new ControladorDePagos();
                    }
                }
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}
