package modelo.mapHandler;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;

public class mapHandler {

    private JComponent ui = null;
    JLabel output = new JLabel();
    BufferedImage image;
    Area area;
    public ArrayList<Shape> shapeList;

    public mapHandler(MouseMotionListener motion, MouseListener listener) {
        try {
            initUI(motion,listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public JLabel getOutput() {
        return output;
    }

    public final void initUI(MouseMotionListener motion, MouseListener listener) throws Exception {
        if (ui != null) {
            return;
        }
        File file = new File("src/vista/images/unnamed.png");
        image = ImageIO.read(file);//leemos la imagen del archivo
        area = getOutline(Color.WHITE, image, 12);//tomamos el contorno del area
        shapeList = separateShapeIntoRegions(area);//lista de shapes la llenamos con el area delimitada por el mapeo de pixeles diferentes a blanco
        ui = new JPanel(new BorderLayout(4,4));
        output.addMouseMotionListener(motion);
        output.addMouseListener(listener);
        ui.add(output);
        refresh();
    }


    public Area getOutline(Color target, BufferedImage bi, int tolerance) {//crea los bordes
        // construct the GeneralPath
        GeneralPath gp = new GeneralPath();

        boolean cont = false;
        for (int xx = 0; xx < bi.getWidth(); xx++) {
            for (int yy = 0; yy < bi.getHeight(); yy++) {
                if (isIncluded(new Color(bi.getRGB(xx, yy)), target, tolerance)) {
                    //if (bi.getRGB(xx,yy)==targetRGB) {
                    if (cont) {
                        gp.lineTo(xx, yy);
                        gp.lineTo(xx, yy + 1);
                        gp.lineTo(xx + 1, yy + 1);
                        gp.lineTo(xx + 1, yy);
                        gp.lineTo(xx, yy);
                    } else {
                        gp.moveTo(xx, yy);
                    }
                    cont = true;
                } else {
                    cont = false;
                }
            }
            cont = false;
        }
        gp.closePath();

        // construct the Area from the GP & return it
        return new Area(gp);
    }

    public static ArrayList<Shape> separateShapeIntoRegions(Shape shape) {
        ArrayList<Shape> regions = new ArrayList<>();

        PathIterator pi = shape.getPathIterator(null);//https://docs.oracle.com/javase/7/docs/api/java/awt/Shape.html
        GeneralPath gp = new GeneralPath();
        while (!pi.isDone()) {//iterador completo
            double[] coords = new double[6];
            int pathSegmentType = pi.currentSegment(coords);//Devuelve las coordenadas y el tipo del segmento del path actual en la iteración.
            int windingRule = pi.getWindingRule();//Devuelve la linea sinuosa para determinar el interior del path

            gp.setWindingRule(windingRule);
            if (pathSegmentType == PathIterator.SEG_MOVETO) {//inicio de un nuevo subPath
                gp = new GeneralPath();
                gp.setWindingRule(windingRule);
                gp.moveTo(coords[0], coords[1]);
            } else if (pathSegmentType == PathIterator.SEG_LINETO) {//el punto final de una línea que se dibujará desde el punto especificado más recientemente
                gp.lineTo(coords[0], coords[1]);
            } else if (pathSegmentType == PathIterator.SEG_QUADTO) {
                gp.quadTo(coords[0], coords[1], coords[2], coords[3]);
            } else if (pathSegmentType == PathIterator.SEG_CUBICTO) {
                gp.curveTo(
                        coords[0], coords[1],
                        coords[2], coords[3],
                        coords[4], coords[5]);
            } else if (pathSegmentType == PathIterator.SEG_CLOSE) {//se cierra un segmento

                gp.closePath();
                //aqui crgr las coordenadas de gp
                Area aux = new Area(gp);
                if (aux.getBounds().width > 10 && (aux.getBounds().getWidth() < 499 && aux.getBounds().getHeight() < 480)){

                    regions.add(new Area(aux));
                }
                if (aux.getBounds().getWidth() < 512 && aux.getBounds().getHeight() < 492){//evitamos agregar el fondo

                }
            } else {
                System.err.println("Unexpected value! " + pathSegmentType);
            }

            pi.next();
        }

        return regions;
    }


    public int  isInThisArea(int x,int y){

        for (int i = 0; i<shapeList.size(); i++){
            if (shapeList.get(i).contains(x,y)){

                return i;
            }
        }
        return -1;
    }




    public static boolean isIncluded(Color target, Color pixel, int tolerance) {//target es el blanco, pixel es el de la pic

        //valores rgb del color blanco
        int rT = target.getRed();
        int gT = target.getGreen();
        int bT = target.getBlue();

        //valores rgb del pixel mapeado de la imagen
        int rP = pixel.getRed();
        int gP = pixel.getGreen();
        int bP = pixel.getBlue();

        //validamos que los rangos de colores sean correctos, Blanco -> (255,255,255)
        return ((rP - tolerance <= rT) && (rT <= rP + tolerance)
                && (gP - tolerance <= gT) && (gT <= gP + tolerance)
                && (bP - tolerance <= bT) && (bT <= bP + tolerance));
    }

    public void refresh() {
        output.setIcon(new ImageIcon(getImage()));
    }


    private BufferedImage getImage() {
        BufferedImage bi = new BufferedImage(
                514, 495, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bi.createGraphics();
        g.drawImage(image, 0, 0, output);
        g.setColor(Color.WHITE);
        g.fill(area);
        g.setColor(Color.BLACK);
        g.draw(area);
        try {
            Point p = MouseInfo.getPointerInfo().getLocation();
            Point p1 = output.getLocationOnScreen();
            int x = p.x - p1.x;
            int y = p.y - p1.y;
            Point pointOnImage = new Point(x, y);
            for (Shape shape : shapeList) {
                if (shape.contains(pointOnImage)) {
                    g.setColor(Color.RED);
                    g.fill(shape);
                    break;
                }
            }
        } catch (Exception doNothing) {
        }

        g.dispose();

        return bi;
    }

    public JComponent getUI() {
        return ui;
    }

    public ArrayList<Shape> getShapeList() {
        return shapeList;
    }

}
