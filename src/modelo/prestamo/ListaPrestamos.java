package modelo.prestamo;

import java.util.ArrayList;
import java.util.List;

public class ListaPrestamos {
    List<Prestamo> lista;

    public ListaPrestamos() {
        lista = new ArrayList<>();
    }

    public void add(Prestamo c) {
        lista.add(c);
    }

    public ListaPrestamos(List<Prestamo> lista) {
        this.lista = lista;
    }

    public List<Prestamo> getLista() {
        return lista;
    }

    public void setLista(List<Prestamo> lista) {
        this.lista = lista;
    }


    public Prestamo buscar(String id){
        for (Prestamo pp: lista){
            if (pp.getId() == id){
                return pp;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String ss = "";
        for (Prestamo p : getLista()){
            ss = ss + p.toString();
        }
        return ss;
    }

    public int size() {
        return lista.size();
    }
}
