package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Prestamo {
    String id;
    double monto;
    double tasaDeInteres;
    LocalDate fecha;
    ArrayList<Pago> listaDePagos;

    public Prestamo() {
        this.id = "Indefinido";
        this.monto = 0;
        this.tasaDeInteres = 0;
        this.fecha = LocalDate.now();
        this.listaDePagos = new ArrayList<>();
    }

    public Prestamo(String id, double monto, double tasaDeInteres) {
        this.id = id;
        this.monto = monto;
        this.tasaDeInteres = tasaDeInteres;
        this.fecha = LocalDate.now();
        this.listaDePagos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getTasaDeInteres() {
        return tasaDeInteres;
    }

    public void setTasaDeInteres(double tasaDeInteres) {
        this.tasaDeInteres = tasaDeInteres;
    }

    public void calculoDeCuota(){

    }

    public boolean verificaExcedeCuotaEsperada(){
        return true;
    }

    public String getFecha() {
        return fecha.toString();
    }

    //TODO configurar para presentarlo bonito en vista
    @Override
    public String toString() {
        return "Prestamo{" +
                "id='" + id + '\'' +
                ", monto=" + monto +
                ", tasaDeInteres=" + tasaDeInteres +
                ", fecha=" + fecha.toString() +
                ", listaDePagos=" + listaDePagos.toString() +
                '}';
    }
}
