package principal;

import modelo.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class principal {
    public static void main(String[] args) {
        //PRUEBAS
        Prestamo prestamo1 = new Prestamo("1", 12.33, 323.343);
        Prestamo prestamo2 = new Prestamo("2", 33.21, 345.13);
        Prestamo prestamo3 = new Prestamo("3", 6.42, 34.32);

        Cliente cliente1 = new Cliente("123", "Rebe1", "Heredia", "Heredia", "Heredia");
        Cliente cliente2 = new Cliente("456", "Rebe2", "Heredia", "Heredia", "Heredia");
        Cliente cliente3 = new Cliente("789", "Rebe3", "Heredia", "Heredia", "Heredia");

        Manager manager = new Manager();
        manager.registrarCliente(cliente1);

    }
}
