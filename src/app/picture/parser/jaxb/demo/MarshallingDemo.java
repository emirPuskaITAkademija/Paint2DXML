package app.picture.parser.jaxb.demo;

import java.io.File;
import java.math.BigInteger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import xml.jaxb.Country;
import xml.jaxb.ObjectFactory;

public class MarshallingDemo {
    public static void main(String[] args) throws JAXBException {
        ObjectFactory objectFactory = new ObjectFactory();
        Country country = objectFactory.createCountry();
        country.setCountryCapital("Sarajevo");
        country.setCountryContinent("Evropa");
        country.setCountryFoundationDate("1189 godina");
        country.setCountryName("Bosna i Hercegovina");
        country.setCountryPopulation(new BigInteger("3500"));
        //očekuje ime paketa unutar kojeg se nalaze izgenerisane klase
        JAXBContext jAXBContext = JAXBContext.newInstance("xml.jaxb");
        Marshaller marshaller = jAXBContext.createMarshaller();
        marshaller.marshal(country, System.out);
        marshaller.marshal(country, new File("bosna.xml"));
    }
}
