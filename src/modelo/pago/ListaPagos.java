package modelo.pago;

import modelo.prestamo.Prestamo;

import java.util.ArrayList;
import java.util.List;

public class ListaPagos {
    List<Pago> lista;

    public ListaPagos() {
        lista = new ArrayList<>();
    }

    public void add(Pago c) {
        lista.add(c);
    }

    public ListaPagos(List<Pago> lista) {
        this.lista = lista;
    }

    public List<Pago> getLista() {
        return lista;
    }

    public void setLista(List<Pago> lista) {
        this.lista = lista;
    }


    public Pago buscar(String id){
        for (Pago pp: lista){
            if (pp.getId() == id){
                return pp;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String ss = "";
        for (Pago p : getLista()){
            ss = ss + p.toString();
        }
        return ss;
    }
}
