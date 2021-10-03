package modelo.pago;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

@XmlRootElement(name = "Pago")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Pago {
    String id;
    int numeroDePago;
    double montoPagado;
    double interes;
    double amortizacion;
    LocalDate fecha;

    public Pago() {
        this.id = "Indefinido";
        this.numeroDePago = 0;
        this.montoPagado = 0;
        this.interes = 0;
        this.amortizacion = 0;
        this.fecha = LocalDate.now();
    }

    public Pago(int numeroDePago, double montoPagado, double interes, double amortizacion) {
        this.id = "Indefinido";
        this.numeroDePago = numeroDePago;
        this.montoPagado = montoPagado;
        this.interes = interes;
        this.amortizacion = amortizacion;
        this.fecha = LocalDate.now();
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

    public String getFecha(){
        //Para tener fecha y hora:
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        //Para obtener solo fecha
        //LocalDate fecha = LocalDate.now();
        //return fecha.toString();
        return fecha.toString();
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id='" + id + '\'' +
                ", numeroDePago=" + numeroDePago +
                ", montoPagado=" + montoPagado +
                ", interes=" + interes +
                ", amortizacion=" + amortizacion +
                ", fecha=" + getFecha() +
                '}';
    }
}
