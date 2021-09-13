package principal;

import modelo.*;
import vista.homeFrame;

import javax.swing.*;

public class principal {
    public static void main(String[] args) {
        //PRUEBAS
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
//        manager.registrarPrestamoAUnCliente(cliente1.getId(), prestamo1);
//        manager.asignarCodigoDelPrestamo(prestamo1, cliente1);
//        manager.cancelacionDeCuota(prestamo1.getId(), pago1);
//
//        System.out.println(prestamo1.getCuota());
//        //Al agregar un pago sobre el monto esperado se vuelve a calcular la cuota
//        System.out.println(prestamo1.getCuota());
//        System.out.println(prestamo1);
//        System.out.println(pago1.getId());
        SwingUtilities.invokeLater(()-> {
            new homeFrame();
        });

    }
}
