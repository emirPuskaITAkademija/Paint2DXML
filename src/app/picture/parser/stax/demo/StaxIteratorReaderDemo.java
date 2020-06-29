package app.picture.parser.stax.demo;

import java.io.FileReader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Grupa2
 */
public class StaxIteratorReaderDemo {

    public static void main(String[] args) throws Exception{
        XMLInputFactory xMLInputFactory = XMLInputFactory.newFactory();
        //OVDJE JE razlika
        XMLEventReader xMLEventReader = xMLInputFactory.createXMLEventReader(new FileReader("note.xml"));
        while(xMLEventReader.hasNext()){
            XMLEvent xMLEvent = xMLEventReader.nextEvent();
            int typeOfElement = xMLEvent.getEventType();
            switch(typeOfElement){
                case XMLStreamReader.START_ELEMENT:
                    System.out.println("START element: " +xMLEvent.asStartElement().getName());
                    break;
                case XMLStreamReader.CHARACTERS:
                    System.out.println("DATA of element: " + xMLEvent.asCharacters().getData());
                    break;
                case XMLStreamReader.END_ELEMENT:
                    System.out.println("END element: " + xMLEvent.asEndElement().getName());
                    break;
            }
        }
    }
}
