package modelo.prestamo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import modelo.pago.ListaPagos;
import modelo.pago.Pago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Prestamos")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Prestamo {
    private String id;
    private double monto;
    private double tasaDeInteres;
    private int plazo;
    private double cuota;
    private LocalDate fecha;
    private boolean estado;
    private ListaPagos listaDePagos;

    public Prestamo() {
        this.id = "Indefinido";
        this.monto = 0;
        this.tasaDeInteres = 0;
        this.plazo = 0;
        this.cuota = 0;
        this.fecha = LocalDate.now();
        this.estado = true;
        this.listaDePagos = new ListaPagos();
    }

    public Prestamo(double monto, double tasaDeInteres, int plazo) {
        this.id = "Indefinido";
        this.monto = monto;
        this.tasaDeInteres = tasaDeInteres;
        this.plazo = plazo;
        this.cuota = calculoDeCuota();
        this.fecha = LocalDate.now();
        this.estado = true;
        this.listaDePagos = new ListaPagos();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMonto() {
        return formatearDecimales(monto, 2);
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getTasaDeInteres() {
        return formatearDecimales(tasaDeInteres,2);
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

    public String leeEstado(){
        return isEstado() ? "Pendiente" : "Completado";
    }

    public boolean tienePagos(){
        List<Pago> listaPagos = getListaPagosRow();
        return listaPagos.size() > 0;
    }

    public List<Pago> getListaPagosRow(){
        return listaDePagos.getLista();
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @XmlElement(name = "Pago")
    public ListaPagos getListaDePagos() {
        return listaDePagos;
    }

    public void setListaDePagos(ListaPagos listaDePagos) {
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

    public double interesDelPago(){
        return formatearDecimales(getMonto() * getTasaDeInteres(), 2);
    }

    public int numeroDePagos(){
        return listaDePagos.getCantidadDePagos();
    }


    public void agregarPago(Pago pago){
        double nuevoMonto = 0.0;
        pago.setNumeroDePago(numeroDePagos() + 1);
        pago.setInteres(interesDelPago());
        pago.calculoAmortizacion();
        agregarCodigoAPago(pago);
        getListaDePagos().add(pago);
        nuevoMonto = (getMonto() - pago.getMontoPagado()) + pago.getInteres();
        setMonto(formatearDecimales(nuevoMonto, 2));
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
        return "\t->\n" +
                "id= " + id + "\n"+
                "monto=" + monto +'\n'+
                "tasaDeInteres=" + tasaDeInteres +'\n'+
                "fecha=" + getFecha()+'\n'+'\n';
    }
}
