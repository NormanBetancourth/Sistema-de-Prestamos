package controlador;

import modelo.ModelHandler;
import vista.homeFrame;

import java.awt.event.*;

public class controlador {
    homeFrame Home;
    ModelHandler modelo;
    ControladorDeClientes controladorDeClientes;
    ControladorDePrestamos controladorDePrestamos;
    ControladorDePagos controladorDePagos;

    public controlador() {
        Home = new homeFrame();
        modelo = new ModelHandler();
        Home.addListener(new ListenerHandler());
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
