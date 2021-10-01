package modelo.pago;

import modelo.cliente.Cliente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModeloTablaPagos extends AbstractTableModel {
    private List<Pago> filas;
    private String[] columnas = {"ID", "Monto", "Fecha"};

    public ModeloTablaPagos(List<Pago> filas) {
        this.filas = filas;
    }
    @Override
    public int getRowCount() {
        return filas.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pago emp = filas.get(rowIndex);
        switch (columnIndex){
            case 0: return emp.getId();
            case 1: return emp.getMontoPagado();
            case 2: return emp.getFecha();
            default: return null;
        }
    }



    @Override
    public int findColumn(String columnName) {
        return super.findColumn(columnName);
    }

    @Override
    public String getColumnName(int col){return columnas[col];}
}
