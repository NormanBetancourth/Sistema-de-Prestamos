package modelo.prestamo;

import modelo.pago.Pago;

import java.time.LocalDate;
import java.util.ArrayList;

public class Prestamo {
    private String id;
    private double monto;
    private double tasaDeInteres;
    private int plazo;
    private double cuota;
    private LocalDate fecha;
    private boolean estado;
    private ArrayList<Pago> listaDePagos;

    public Prestamo() {
        this.id = "Indefinido";
        this.monto = 0;
        this.tasaDeInteres = 0;
        this.plazo = 0;
        this.cuota = 0;
        this.fecha = LocalDate.now();
        this.estado = true;
        this.listaDePagos = new ArrayList<>();
    }

    public Prestamo(double monto, double tasaDeInteres, int plazo) {
        this.id = "Indefinido";
        this.monto = monto;
        this.tasaDeInteres = tasaDeInteres;
        this.plazo = plazo;
        this.cuota = calculoDeCuota();
        this.fecha = LocalDate.now();
        this.estado = true;
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

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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
        double denominador = 1  - Math.pow(1 + getTasaDeInteres(), Math.negateExact(getPlazo()));
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

    public void agregarCodigoAPago(Pago pago){
        // Igual al codigo aleatorio de prestamo + el numero correpsonidente al pago
        pago.setId(getId() + "-" + pago.getNumeroDePago());
    }

    public void agregarPago(Pago pago){
        agregarCodigoAPago(pago);
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
