package modelo.prestamo;

import modelo.pago.Pago;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModeloTablaPrestamos extends AbstractTableModel {
    private List<Prestamo> filas;
    private String[] columnas = {"ID", "Monto", "Tasa de Interes","Plazo","Cuota mensual", "Fecha"};

    public ModeloTablaPrestamos(List<Prestamo> filas) {
        this.filas = filas;
    }

    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    @Override
    public int getRowCount() {
        return filas.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prestamo emp = filas.get(rowIndex);
        switch (columnIndex){
            case 0: return emp.getId();
            case 1: return formatearDecimales(emp.getMonto() + (emp.getMonto() * emp.getTasaDeInteres()),2);
            case 2: return emp.getTasaDeInteres();
            case 3: return emp.getPlazo();
            case 4: return emp.getCuota();
            case 5: return emp.getFecha();
            default: return null;
        }
    }



    @Override
    public int findColumn(String columnName) {
        return super.findColumn(columnName);
    }

    @Override
    public String getColumnName(int col){return columnas[col];}

    public Integer getIndex(String valueOf) {
        for(int i = 0; i < getRowCount(); i++){
            if(valueOf.equals(String.valueOf(getValueAt(i, 0)))){
                return i;
            }
        }
        return null;

    }
}
