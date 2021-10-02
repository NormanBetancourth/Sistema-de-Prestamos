package modelo.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaClientesHandler {
    List<Cliente> lista;

    public ListaClientesHandler() {
        lista = new ArrayList<>();
    }

    public void add(Cliente c) {
        lista.add(c);
    }

    public ListaClientesHandler(List<Cliente> lista) {
        this.lista = lista;
    }


    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    public int getCantidadClientes(){return lista.size();}

    public Cliente buscar(String id){
        for (Cliente pp: lista){
            if (Objects.equals(pp.getId(), id)){
                return pp;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        String ss=" ";
        for (Cliente p: lista){
            ss= ss +p.toString() + "\n";

        }
        return ss;
    }

}
