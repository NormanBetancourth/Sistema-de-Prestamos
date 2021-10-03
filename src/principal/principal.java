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

            //Controlador ctrl = new Controlador();

//            Pago p1 = new Pago(1,1000,0.5,5);
//            Pago p2 = new Pago(2,1000,0.5,5);
//            Pago p3 = new Pago(3,1000,0.5,5);
//
//            ListaPagos listaPagos = new ListaPagos();
//            listaPagos.add(p1);
//            listaPagos.add(p2);
//            listaPagos.add(p3);
//
//            Prestamo prestamo1 = new Prestamo(343000,5,60);
//            prestamo1.setListaDePagos(listaPagos);
//            Prestamo prestamo2 = new Prestamo(343000,5,60);
//
//
//
//
//            ListaPrestamos l1 = new ListaPrestamos();
//            l1.add(prestamo1);
//            l1.add(prestamo2);
//
//            Cliente cliente= new Cliente(01,"Norman", "San Jose", "Merced","San  Jose");
//            cliente.setListaDePrestamos(l1);
//            ListaClientes clientes  = new ListaClientes();
//            clientes.add(cliente);
//
//            JAXBParser parser = new JAXBParser();
//            parser.marshall(clientes,"src/modelo/dataBase/Clientes.xml");

            JAXBParser parser= new JAXBParser();

            ListaClientes c1 = (ListaClientes) parser.unmarshall(new ListaClientes(), "src/modelo/dataBase/Clientes.xml" );
            c1.add(new Cliente());

            System.out.println(c1);




        });
    }
}
