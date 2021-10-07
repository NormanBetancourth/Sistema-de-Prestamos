package principal;

import controlador.Controlador;
import modelo.JAXBParser;
import modelo.cliente.Cliente;
import modelo.cliente.ListaClientes;
import modelo.pago.ListaPagos;
import modelo.pago.Pago;
import modelo.prestamo.ListaPrestamos;
import modelo.prestamo.Prestamo;

import javax.swing.*;
import java.io.FileNotFoundException;

public class principal {
    public static void main(String[] args) throws FileNotFoundException {

        SwingUtilities.invokeLater(()-> {
            Controlador ctrl = new Controlador();
        });
    }
}
