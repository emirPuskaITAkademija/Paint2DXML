package app.picture.parser.stax;

import app.PaintWindow;
import app.picture.PaintPanel;
import app.picture.parser.XMLParser;
import app.shape.Circle;
import app.shape.PaintShape;
import app.shape.Square;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class StaxParser implements XMLParser {

    @Override
    public List<PaintShape> readPicture(String pictureName) {
        final List<PaintShape> paintShapes = new ArrayList<>();
        try {
            XMLInputFactory xMLInputFactory = XMLInputFactory.newFactory();
            XMLStreamReader xMLStreamReader = xMLInputFactory.createXMLStreamReader(new FileReader(pictureName));
            int x = 0;
            boolean xOpen = false;
            int y = 0;
            boolean yOpen = false;
            String color = null;
            boolean colorOpen = false;
            String type = null;
            boolean typeOpen = false;
            while (xMLStreamReader.hasNext()) {
                int typeOfElement = xMLStreamReader.next();
                switch (typeOfElement) {
                    //x, y, color, type
                    case XMLStreamReader.START_ELEMENT:
                        if ("x".equalsIgnoreCase(xMLStreamReader.getName().toString())) {
                            xOpen = true;
                        } else if ("y".equalsIgnoreCase(xMLStreamReader.getName().toString())) {
                            yOpen = true;
                        } else if ("color".equalsIgnoreCase(xMLStreamReader.getName().toString())) {
                            colorOpen = true;
                        } else if ("type".equalsIgnoreCase(xMLStreamReader.getName().toString())) {
                            typeOpen = true;
                        }
                        break;
                    case XMLStreamReader.CHARACTERS:
                        if (xOpen) {
                            x = Integer.parseInt(xMLStreamReader.getText());
                            xOpen = false;
                        } else if (yOpen) {
                            y = Integer.parseInt(xMLStreamReader.getText());
                            yOpen = false;
                        } else if (colorOpen) {
                            color = xMLStreamReader.getText();
                            colorOpen = false;
                        } else if (typeOpen) {
                            type = xMLStreamReader.getText();
                            typeOpen = false;
                        }
                        break;
                    case XMLStreamReader.END_ELEMENT:
                        if ("x".equalsIgnoreCase(xMLStreamReader.getName().toString())) {

                        } else if ("y".equalsIgnoreCase(xMLStreamReader.getName().toString())) {

                        } else if ("color".equalsIgnoreCase(xMLStreamReader.getName().toString())) {

                        } else if ("type".equalsIgnoreCase(xMLStreamReader.getName().toString())) {
                            Color colorAwt = "CRVENA".equalsIgnoreCase(color) ? Color.RED : Color.BLUE;
                            if ("KRUG".equalsIgnoreCase(type)) {
                                paintShapes.add(new Circle(x, y, colorAwt));
                            } else {
                                paintShapes.add(new Square(x, y, colorAwt));
                            }
                        }
                        break;
                }
            }
            return paintShapes;
        } catch (FileNotFoundException | NumberFormatException | XMLStreamException exc) {
            throw new RuntimeException(exc.getMessage());
        }
    }

    @Override
    public void savePicture(List<PaintShape> paintShapes, String pictureName) {
        try {
            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter streamWriter = xMLOutputFactory.createXMLStreamWriter(new FileWriter(pictureName));
            streamWriter.writeStartDocument();
            streamWriter.writeStartElement("shapes");
            for (PaintShape paintShape : paintShapes) {
                streamWriter.writeStartElement("shape");
                streamWriter.writeStartElement("x");
                streamWriter.writeCharacters(paintShape.getX() + "");
                streamWriter.writeEndElement();
                streamWriter.writeStartElement("y");
                streamWriter.writeCharacters(paintShape.getY() + "");
                streamWriter.writeEndElement();
                streamWriter.writeStartElement("color");
                streamWriter.writeCharacters(paintShape.getColor().equals(Color.BLUE)? "PLAVA":"CRVENA");
                streamWriter.writeEndElement();
                streamWriter.writeStartElement("type");
                streamWriter.writeCharacters((paintShape instanceof Circle)? "KRUG":"KVADRAT");
                streamWriter.writeEndElement();
                streamWriter.writeEndElement();
            }
            streamWriter.writeEndElement();
            streamWriter.writeEndDocument();
            streamWriter.flush();
        } catch (Exception exc) {
            throw new RuntimeException(exc.getMessage());
        }
    }
}
