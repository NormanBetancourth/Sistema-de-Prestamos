package modelo.cliente;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import modelo.prestamo.ListaPrestamos;
import modelo.prestamo.Prestamo;

import java.util.List;
import java.util.Random;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class Cliente {
    private int id;
    private String nombre;
    private String provincia;
    private String distrito;
    private String canton;
    private ListaPrestamos listaDePrestamos;



    public Cliente() {
        int id = 0;
        String nombre = "Indefinido";
        String provincia = "Indefinido";
        String distrito = "Indefinido";
        String canton = "Indefinido";
        listaDePrestamos = new ListaPrestamos();
    }

    public Cliente(int id, String nombre, String provincia, String distrito, String canton) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
        this.distrito = distrito;
        this.canton = canton;
        this.listaDePrestamos = new ListaPrestamos();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    @XmlElement(name = "Prestamo")
    public ListaPrestamos getListaDePrestamos() {
        return listaDePrestamos;
    }

    public void setListaDePrestamos(ListaPrestamos listaDePrestamos) {
        this.listaDePrestamos = listaDePrestamos;
    }

    public int getNumeroDePrestamos(){
        return listaDePrestamos.size();
    }

    public Prestamo getAlgunPrestamo(String idPrestamo){
        for(Prestamo prestamo : getListaDePrestamos().getLista()){
            if(prestamo.getId().equals(idPrestamo)){
                return prestamo;
            }
        }
        return null;
    }
    public int getCantidadPrestamos(){
        return listaDePrestamos.size();
    }



    @Override
    public int hashCode() {
        return !(getId() == 0) ? getId() * 31 : 0;

    }

    @Override
    public String toString() {
        return "\tCliente\n" +
                "id= " + id + '\n' +
                "nombre= " + nombre + '\n' +
                "provincia= " + provincia + '\n' +
                "distrito= " + distrito + '\n' +
                "canton= " + canton + '\n'+'\n' +
                "listaDePrestamos\n" + listaDePrestamos.toString()+ '\n';
    }

    public List<Prestamo> getListaDePrestamosRaw() {
        return  listaDePrestamos.getLista();
    }

    public void addPrestamo(Prestamo prestamo) {
        listaDePrestamos.add(prestamo);
    }

    public boolean tienePrestamos() {
        return listaDePrestamos.size() >0;
    }

    public String getDireccion() {
        return provincia+", "+ canton+", "+ distrito;
    }
}
