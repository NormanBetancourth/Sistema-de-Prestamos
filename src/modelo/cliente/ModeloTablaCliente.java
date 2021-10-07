package modelo.cliente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModeloTablaCliente extends AbstractTableModel {
    private List<Cliente> filas;
    private String[] columnas = {"ID", "Nombre", "Direccion", "Cantidad de Prestamos"};

    public ModeloTablaCliente(List<Cliente> filas) {
        this.filas = filas;
    }
    @Override
    public int getRowCount() {
        return filas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente emp = filas.get(rowIndex);
        switch (columnIndex){
            case 0: return emp.getId();
            case 1: return emp.getNombre();
            case 2: return emp.getProvincia()+" "+emp.getCanton()+" "+emp.getDistrito();
            case 3: return emp.getCantidadPrestamos();
            default: return null;
        }
    }

    public Integer getIndex(String id){
        for(int i = 0; i < getRowCount(); i++){
            if(id.equals(String.valueOf(getValueAt(i, 0)))){
                return i;
            }
        }
        return null;
    }


    @Override
    public int findColumn(String columnName) {
        return super.findColumn(columnName);
    }

    @Override
    public String getColumnName(int col){return columnas[col];}
}
