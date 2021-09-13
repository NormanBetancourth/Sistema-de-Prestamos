package principal;

import modelo.*;
import vista.busquedaRegistroClientes;
import vista.homeFrame;

import javax.swing.*;

public class principal {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(()-> {
            busquedaRegistroClientes frame = new busquedaRegistroClientes();
            frame.addComponents(null);
//            homeFrame frame = new homeFrame();
//            frame.addComponents();
        });
//        Prestamo prestamo1 = new Prestamo(100, 0.0725, 60);
//        Prestamo prestamo2 = new Prestamo(33.21, 345.13, 15);
//        Prestamo prestamo3 = new Prestamo(6.42, 34.32, 13);
//
//        Pago pago1 = new Pago(1, 10, 0.1, 0.1);
//
//        Cliente cliente1 = new Cliente("123", "Rebe1", "Heredia", "Heredia", "Heredia");
//        Cliente cliente2 = new Cliente("456", "Rebe2", "Heredia", "Heredia", "Heredia");
//        Cliente cliente3 = new Cliente("789", "Rebe3", "Heredia", "Heredia", "Heredia");
//
//        Manager manager = new Manager();
//        manager.registrarCliente("123", "Rebe1", "Heredia", "Heredia", "Heredia");
//        manager.registrarCliente("456", "Rebe2", "Heredia", "Heredia", "Heredia");
//
//        manager.registrarPrestamoAUnCliente(cliente1, 100, 0.0725, 60);
//        manager.asignarCodigoDelPrestamo(prestamo1, cliente1);
//        manager.cancelacionDeCuota(prestamo1.getId(), 1, 10, 0.1, 0.1);

    }
}
