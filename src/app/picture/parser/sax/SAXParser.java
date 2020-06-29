/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.picture.parser.sax;

import app.PaintWindow;
import app.picture.PaintPanel;
import app.picture.parser.XMLParser;
import app.shape.PaintShape;
import app.shape.Square;
import java.awt.Color;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author Grupa2
 */
public class SAXParser implements XMLParser {

    @Override
    public List<PaintShape> readPicture(String pictureName) {
        //SAX parser -> Simple API for XML
        SAXParserFactory saxpf = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser sAXParser;
        try {
            sAXParser = saxpf.newSAXParser();
            PictureHandler pictureHandler = new PictureHandler();
            sAXParser.parse(pictureName, pictureHandler);
            return pictureHandler.getPaintShapes();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void savePicture(List<PaintShape> paintShapes, String pictureName) {
        try (PrintWriter out = new PrintWriter(new FileWriter(pictureName))) {
            out.println("<?xml version=\"1.0\"?>");
            out.println("<shapes>");
            List<PaintShape> shapes = PaintWindow.getInstance().getPaintPanel().getPaintShapes();
            for (PaintShape paintShape : shapes) {
                out.println("<shape>");
                out.println("<x>" + paintShape.getX() + "</x>");
                out.println("<y>" + paintShape.getY() + "</y>");
                String color = paintShape.getColor().equals(Color.BLUE) ? "PLAVA" : "CRVENA";
                out.println("<color>" + color + "</color>");
                String type = (paintShape instanceof Square) ? "KVADRAT" : "KRUG";
                out.println("<type>" + type + "</type>");
                out.println("</shape>");
            }
            out.println("</shapes>");
            PaintPanel paintPanel = PaintWindow.getInstance().getPaintPanel();
            paintPanel.getPaintShapes().clear();
            paintPanel.repaint();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}
