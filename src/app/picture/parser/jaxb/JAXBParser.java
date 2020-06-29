/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.picture.parser.jaxb;

import app.picture.parser.XMLParser;
import app.shape.PaintShape;
import app.shape.Square;
import java.awt.Color;
import java.io.File;
import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import xml.jaxb.picture.ObjectFactory;
import xml.jaxb.picture.Shape;
import xml.jaxb.picture.Shapes;

/**
 *
 * @author Grupa2
 */
public class JAXBParser implements XMLParser {

    @Override
    public List<PaintShape> readPicture(String pictureName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void savePicture(List<PaintShape> paintShapes, String pictureName) {
        try{
            JAXBContext jAXBContext = JAXBContext.newInstance("xml.jaxb.picture");
            Marshaller marshaller = jAXBContext.createMarshaller();
            ObjectFactory objectFactory = new ObjectFactory();
            Shapes shapes = objectFactory.createShapes();
            for(PaintShape paintShape: paintShapes){
                Shape shape = objectFactory.createShape();
                shape.setX(new BigInteger(paintShape.getX()+""));
                shape.setY(new BigInteger(paintShape.getY()+""));
                shape.setColor(paintShape.getColor().equals(Color.BLUE)?"PLAVA":"CRVENA");
                shape.setType((paintShape instanceof Square)?"KVADRAT":"KRUG");
                shapes.getShape().add(shape);
            }
            marshaller.marshal(shapes, new File(pictureName));
        }catch(Exception exc){
            throw new RuntimeException(exc.getMessage());
        }
    }

}
