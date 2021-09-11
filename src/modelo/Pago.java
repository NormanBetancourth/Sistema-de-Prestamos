package modelo;

public class Pago {
    String id;
    int numeroDePago;
    double montoPagado;
    double interes;
    double amortizacion;
    //Fecha fecha;

    public Pago() {
        this.id = "Indefinido";
        this.numeroDePago = 0;
        this.montoPagado = 0;
        this.interes = 0;
        this.amortizacion = 0;
    }

    public Pago(String id, int numeroDePago, double montoPagado, double interes, double amortizacion) {
        this.id = id;
        this.numeroDePago = numeroDePago;
        this.montoPagado = montoPagado;
        this.interes = interes;
        this.amortizacion = amortizacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumeroDePago() {
        return numeroDePago;
    }

    public void setNumeroDePago(int numeroDePago) {
        this.numeroDePago = numeroDePago;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getAmortizacion() {
        return amortizacion;
    }

    public void setAmortizacion(double amortizacion) {
        this.amortizacion = amortizacion;
    }
}
