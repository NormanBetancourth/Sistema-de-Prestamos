package principal;

import modelo.*;
import vista.busquedaRegistroClientes;
import vista.homeFrame;

import javax.annotation.processing.Filer;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class principal {
    public static void main(String[] args) throws FileNotFoundException {

//        SwingUtilities.invokeLater(()-> {
//            busquedaRegistroClientes frame = new busquedaRegistroClientes();
//            frame.addComponents(null);
////            homeFrame frame = new homeFrame();
////            frame.addComponents();
//        });
//
        Manager m = new Manager();
        System.out.println(m.mostrarCantones("6"));
        System.out.println(m.mostrarDistritos("DESAMPARADOS"));


    }




}
