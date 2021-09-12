package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Prestamo {
    String id;
    double monto;
    double tasaDeInteres;
    // TODO not sure sobre lo de días
    int plazoEnDias;
    double cuota;
    LocalDate fecha;
    ArrayList<Pago> listaDePagos;

    public Prestamo() {
        this.id = "Indefinido";
        this.monto = 0;
        this.tasaDeInteres = 0;
        this.plazoEnDias = 0;
        this.cuota = 0;
        this.fecha = LocalDate.now();
        this.listaDePagos = new ArrayList<>();
    }

    public Prestamo(String id, double monto, double tasaDeInteres, int plazoEnDias) {
        this.id = id;
        this.monto = monto;
        this.tasaDeInteres = tasaDeInteres;
        this.plazoEnDias = plazoEnDias;
        this.cuota = calculoDeCuota();
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

    public int getPlazoEnDias() {
        return plazoEnDias;
    }

    public void setPlazoEnDias(int plazoEnDias) {
        this.plazoEnDias = plazoEnDias;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    public ArrayList<Pago> getListaDePagos() {
        return listaDePagos;
    }

    public void setListaDePagos(ArrayList<Pago> listaDePagos) {
        this.listaDePagos = listaDePagos;
    }

    public double calculoDeCuota(){
        // TODO redondeo a 2 decimales
        double numerador = getMonto() * getTasaDeInteres();
        double denominador = 1  - Math.pow(1 + getTasaDeInteres(), Math.negateExact(getPlazoEnDias()));
        return formatearDecimales(numerador / denominador, 2);
    }

    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    public boolean verificaExcedeCuotaEsperada(Pago pago){
        return pago.getMontoPagado() > getCuota();
    }

    public String getFecha() {
        return fecha.toString();
    }

    public void agregarPago(Pago pago){
        getListaDePagos().add(pago);
        setMonto(getMonto() - pago.getMontoPagado());
        if(verificaExcedeCuotaEsperada(pago)){
            // Si excede el monto esperado, se vuelve a calcular la cuota
          setCuota(calculoDeCuota());
        }
    }

    public String registroDePagos(){
        return getListaDePagos().toString();
    }

    //TODO configurar para presentarlo bonito en vista
    @Override
    public String toString() {
        return "Prestamo{" +
                "id='" + id + '\'' +
                ", monto=" + monto +
                ", tasaDeInteres=" + tasaDeInteres +
                ", fecha=" + getFecha() +
                ", listaDePagos=" + listaDePagos.toString() +
                '}';
    }
}
