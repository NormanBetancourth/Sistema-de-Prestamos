package principal;

import controlador.controlador;

import javax.swing.*;
import java.io.FileNotFoundException;

public class principal {
    public static void main(String[] args) throws FileNotFoundException {

        SwingUtilities.invokeLater(()-> {
            controlador ctrl = new controlador();
            ctrl.addContent();

        });
//
//        Manager m = new Manager();
//        System.out.println(m.mostrarCantones("6"));
//        System.out.println(m.mostrarDistritos("DESAMPARADOS"));


    }




}
