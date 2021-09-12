package modelo;
import java.util.ArrayList;

public class Cliente {
    private String id;
    private String nombre;
    private String provincia;
    private String distrito;
    private String canton;
    private ArrayList<Prestamo> listaDePrestamos;

    public Cliente() {
        String id = "Indefinido";
        String nombre = "Indefinido";
        String provincia = "Indefinido";
        String distrito = "Indefinido";
        String canton = "Indefinido";
        ArrayList<Prestamo> listaDePrestamos = new ArrayList<>();
    }

    public Cliente(String id, String nombre, String provincia, String distrito, String canton) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
        this.distrito = distrito;
        this.canton = canton;
        this.listaDePrestamos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public ArrayList<Prestamo> getListaDePrestamos() {
        return listaDePrestamos;
    }

    public void setListaDePrestamos(ArrayList<Prestamo> listaDePrestamos) {
        this.listaDePrestamos = listaDePrestamos;
    }

    public Prestamo getAlgunPrestamo(String idPrestamo){
        for(Prestamo prestamo : getListaDePrestamos()){
            if(prestamo.getId().equals(idPrestamo)){
                return prestamo;
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        return !getId().equals("") ? Integer.parseInt(getId()) * 31 : 0;
    }

    // TODO Configurar para presentarlo en vista
    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", provincia='" + provincia + '\'' +
                ", distrito='" + distrito + '\'' +
                ", canton='" + canton + '\'' +
                ", listaDePrestamos=" + listaDePrestamos.toString() +
                '}';
    }
}
