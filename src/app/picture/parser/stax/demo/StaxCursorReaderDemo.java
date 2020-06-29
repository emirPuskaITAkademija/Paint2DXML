package app.picture.parser.stax.demo;

import java.io.FileReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

/**
 * Stax koja podsjeća na SAX
 *
 * @author Grupa2
 */
public class StaxCursorReaderDemo {

    public static void main(String[] args) throws Exception {
        XMLInputFactory xMLInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xMLStreamReader = xMLInputFactory.createXMLStreamReader(new FileReader("note.xml"));
        while (xMLStreamReader.hasNext()){
            int typeOfElement = xMLStreamReader.next();
            switch(typeOfElement){
                case XMLStreamReader.START_ELEMENT:
                    System.out.println("START element: " + xMLStreamReader.getName());
                    break;
                case XMLStreamReader.CHARACTERS:
                    System.out.println("DATA of element: " + xMLStreamReader.getText());
                    break;
                case XMLStreamReader.END_ELEMENT:
                    System.out.println("END element: " + xMLStreamReader.getName());
                    break;
            }
        }
    }
}
