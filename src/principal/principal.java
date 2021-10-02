package principal;

import controlador.Controlador;

import javax.swing.*;
import java.io.FileNotFoundException;

public class principal {
    public static void main(String[] args) throws FileNotFoundException {

        SwingUtilities.invokeLater(()-> {
            Controlador ctrl = new Controlador();
        });
    }
}
