package modelo.cliente;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "Clientes")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ListaClientes {
    List<Cliente> lista;

    public ListaClientes() {
        lista = new ArrayList<>();
    }

    public void add(Cliente c) {
        lista.add(c);
    }

    public ListaClientes(List<Cliente> lista) {
        this.lista = lista;
    }


    @XmlElement(name = "Cliente")
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
