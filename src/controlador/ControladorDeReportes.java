package controlador;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfDate;
import modelo.cliente.Cliente;
import modelo.cliente.ModeloTablaCliente;
import modelo.pago.Pago;
import modelo.prestamo.ModeloTablaPrestamos;
import modelo.prestamo.Prestamo;
import vista.VistaBuilder;
import vista.VistaReportes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class ControladorDeReportes {
    private VistaReportes vistaReportes;
    private Controlador ctrl;

    public ControladorDeReportes(Controlador c){
        ctrl = c;
        vistaReportes = new VistaReportes();
        vistaReportes.addComponents(new ListenerHandler());
    }

    public void actualizaTablaCliente() {
        ModeloTablaCliente modelo = ctrl.getModeloTablaCliente();
        vistaReportes.setModeloTabla(modelo);
    }

    public void actualizaTablaPrestamos(){
        ModeloTablaPrestamos modeloTabla = new ModeloTablaPrestamos(ctrl.getTodoslosPrestamos());
        vistaReportes.setModeloTablaPrestamos(modeloTabla);
    }

    private class ListenerHandler implements ActionListener, MouseListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            int idCliente = 0;
            String idPrestamo;

            switch (valor){
                // Regresar a inicio
                case "4-0": {
                    vistaReportes.dispose();
                    ctrl.setVisible();
                }
                break;
                case "4-1":
                // cambiar vista reporte general
                {
                    vistaReportes.mainContentHandler(1, new ControladorDeReportes.ListenerHandler());
                    vistaReportes.clearFields();
                }
                break;
                case "4-1-0":
                    //Obtener reporte de todos los clientes
                {
                    Document document = new Document();
                    try{
                        String path = "src/Reportes/ReporteClientes.pdf";
                        PdfWriter.getInstance(document, new FileOutputStream(path));

                        document.open();
                        PdfPTable table = new PdfPTable(4);



                        table.addCell("ID");
                        table.addCell("Nombre");
                        table.addCell("Direccion");
                        table.addCell("Cantidad de prestamos");

                        for (Cliente c: ctrl.getListaClientesRaw()){
                            table.addCell(new Phrase(String.valueOf(c.getId())));
                            table.addCell(new Phrase(c.getNombre()));
                            table.addCell(new Phrase(c.getDireccion()));
                            table.addCell(new Phrase(String.valueOf(c.getCantidadPrestamos())));
                        }
                        document.add(new Header("Reporte de clientes", "Reporte de Clientes"));
                        Paragraph pp = new Paragraph("Reporte de Clientes", new Font(Font.FontFamily.HELVETICA, 16f));
                        pp.setAlignment(Element.ALIGN_CENTER);
                        pp.setSpacingAfter(25f);
                        document.add(pp);
                        document.add(table);
                        document.close();

                        JOptionPane.showMessageDialog(null, "Se ha creado un reporte de clientes. Consulte en la carpeta Reportes", "",JOptionPane.INFORMATION_MESSAGE);


                    } catch (DocumentException documentException) {
                        documentException.printStackTrace();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }

                }
                break;
                case "4-1-1":
                // Enviar info para presentar tabla de prestamos
                {
                    try {
                        if (vistaReportes.getTextoId().isBlank()) {
                            throw new Exception("Existen campos de texto vacios");
                        } else {
                            try {
                                idCliente = Integer.parseInt(vistaReportes.getTextoId());
                            } catch (NumberFormatException exception) {
                                vistaReportes.leerError("Solo se aceptan numeros para determinadas variables");
                                vistaReportes.clearFields();
                                break;
                            }
                            try {
                                if (!ctrl.getModelo().clienteEstaRegistrado(idCliente)) {
                                    throw new Exception("El usuario indicado no se encuentra registrado en el sistema");
                                } else {
                                    Integer index = ctrl.getModelo().getIndexTablaCliente((String.valueOf(idCliente)));
                                    Cliente cliente = ctrl.getCliente(idCliente);

                                    //PDF
                                    Document document = new Document();
                                    try{
                                        String path = "src/Reportes/ReportePrestamosDeCliente.pdf";
                                        PdfWriter.getInstance(document, new FileOutputStream(path));

                                        document.open();
                                        PdfPTable table = new PdfPTable(6);


                                        table.addCell("ID");
                                        table.addCell("Monto");
                                        table.addCell("Tasa de interes");
                                        table.addCell("Plazo");
                                        table.addCell("Cuota mensual");
                                        table.addCell("Fecha");

                                        for (Prestamo c: cliente.getListaDePrestamosRaw()){
                                            table.addCell(new Phrase(String.valueOf(c.getId())));
                                            table.addCell(new Phrase(String.valueOf(c.getMonto())));
                                            table.addCell(new Phrase(String.valueOf(c.getTasaDeInteres())));
                                            table.addCell(new Phrase(String.valueOf(c.getPlazo())));
                                            table.addCell(new Phrase(String.valueOf(c.getCuota())));
                                            table.addCell(new Phrase(c.getFecha()));
                                        }
                                        document.add(new Header("Reporte Prestamos", "Reporte de Prestamos"));
                                        Paragraph pp = new Paragraph("Reporte de Prestamos de "+ cliente.getNombre()+"  (ID:  "+cliente.getId()+")", new Font(Font.FontFamily.HELVETICA, 16f));

                                        pp.setAlignment(Element.ALIGN_CENTER);
                                        pp.setSpacingAfter(25f);
                                        document.add(pp);
                                        document.add(table);
                                        document.close();

                                        JOptionPane.showMessageDialog(null, "Se ha creado un reporte de prestamos de un cliente. Consulte en la carpeta Reportes", "",JOptionPane.INFORMATION_MESSAGE);


                                    } catch (DocumentException documentException) {
                                        documentException.printStackTrace();
                                    } catch (FileNotFoundException fileNotFoundException) {
                                        fileNotFoundException.printStackTrace();
                                    }






                                    vistaReportes.seleccionarIntervalo(index, index);
                                    vistaReportes.getBoton().setActionCommand("4-1-1-Reporte");
                                    vistaReportes.getBoton().setText("Generar");
                                }
                            } catch (Exception exception) {
                                vistaReportes.leerError(exception.getMessage());
                                vistaReportes.clearFields();
                                break;
                            }
                        }
                    } catch (Exception exception) {
                        vistaReportes.leerError(exception.getMessage());
                        vistaReportes.clearFields();
                        break;
                    }
                    vistaReportes.clearFields();
                    break;
                }
                case "4-1-1-Reporte":
                {
                    // Realizar reporte de prestamos de un cliente
                    vistaReportes.quitarSeleccion();
                    vistaReportes.getBoton().setActionCommand("4-1-1");
                    vistaReportes.getBoton().setText("Enviar");
                    vistaReportes.getIdTextField().setEditable(true);
                    vistaReportes.clearFields();
                }
                break;
                case "4-2":
                {
                    // Vista reporte de un prestamo
                    vistaReportes.mainContentHandler(2, new ControladorDeReportes.ListenerHandler());
                    vistaReportes.clearFields();
                    actualizaTablaCliente();
                }
                break;
                case "4-3":
                {
                    // Vista reporte de pagos de un prestamo
                    vistaReportes.mainContentHandler(3, new ControladorDeReportes.ListenerHandler());
                    vistaReportes.clearFields();
                    actualizaTablaPrestamos();

                }
                break;
                case "Cancelacion":
                {
                    vistaReportes.getIdTextField().setEditable(true);
                    vistaReportes.clearFields();
                }
                break;
                case "4-2-1": {
                    //Reporte de pagos de un prestamo
                    try {
                        if (vistaReportes.getTextoId().isBlank()) {
                            throw new Exception("Existen campos de texto vacios");
                        } else {
                            try {
                                idPrestamo= vistaReportes.getTextoId();
                            } catch (NumberFormatException exception) {
                                vistaReportes.clearFields();
                                break;
                            }
                            try {
                                if (ctrl.getModelo().getAlgunPrestamo(idPrestamo) == null) {
                                    throw new Exception("No existe ningun prestamo con ese ID");
                                } else {
                                    Prestamo prestamo = ctrl.getPrestamo(idPrestamo);

                                    //PDF
                                    Document document = new Document();
                                    try{
                                        String path = "src/Reportes/ReportePagosDePrestamo.pdf";
                                        PdfWriter.getInstance(document, new FileOutputStream(path));

                                        document.open();
                                        PdfPTable table = new PdfPTable(6);


                                        table.addCell("Numero de Pago");
                                        table.addCell("Codigo");
                                        table.addCell("Tasa de Interes");
                                        table.addCell("Amortizacion");
                                        table.addCell("Monto");
                                        table.addCell("Fecha");

                                        for (Pago p: prestamo.getListaPagosRow()){
                                            table.addCell(new Phrase(String.valueOf(p.getNumeroDePago())));
                                            table.addCell(new Phrase(String.valueOf(p.getId())));
                                            table.addCell(new Phrase(String.valueOf(p.getInteres())));
                                            table.addCell(new Phrase(String.valueOf(p.getAmortizacion())));
                                            table.addCell(new Phrase(String.valueOf(p.getMontoPagado())));
                                            table.addCell(new Phrase(p.getFecha()));
                                        }
                                        document.add(new Header("Reporte Pagos ", "Reporte de Pagos"));
                                        Paragraph pp = new Paragraph("Reporte Pagos de un Prestamo de "+"  (ID Prestamo:  "+prestamo.getId()+")", new Font(Font.FontFamily.HELVETICA, 16f));


                                        pp.setAlignment(Element.ALIGN_CENTER);
                                        pp.setSpacingAfter(25f);
                                        document.add(pp);
                                        document.add(table);
                                        document.close();

                                        JOptionPane.showMessageDialog(null, "Se ha creado un reporte de pagos de un prestamo. Consulte en la carpeta Reportes", "",JOptionPane.INFORMATION_MESSAGE);


                                    } catch (DocumentException documentException) {
                                        documentException.printStackTrace();
                                    } catch (FileNotFoundException fileNotFoundException) {
                                        fileNotFoundException.printStackTrace();
                                    }


                                    vistaReportes.getBoton().setText("Generar");
                                }
                            } catch (Exception exception) {
                                vistaReportes.leerError(exception.getMessage());
                                vistaReportes.clearFields();
                                break;
                            }
                        }
                    } catch (Exception exception) {
                        vistaReportes.leerError(exception.getMessage());
                        vistaReportes.clearFields();
                        break;
                    }
                    vistaReportes.clearFields();

                }
                break;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JTable jTable = vistaReportes.getTabla();
            if (e.getSource().equals(jTable)) {
                int selectedRow = jTable.getSelectedRow();
                vistaReportes.setTextoId(String.valueOf(jTable.getValueAt(selectedRow, 0)));
                vistaReportes.getBoton().setText("Generar");
                //vistaReportes.getBoton().setActionCommand("4-1-1-Reporte");
                vistaReportes.getBoton2().setEnabled(true);
                vistaReportes.getIdTextField().setEditable(false);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
