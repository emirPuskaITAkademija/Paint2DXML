/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.picture.parser.jaxb.demo;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import xml.jaxb.Country;


public class UnmarashallingDemo {
    public static void main(String[] args) throws JAXBException {
        JAXBContext jAXBContext = JAXBContext.newInstance("xml.jaxb");
        Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();
        Country country =(Country) unmarshaller.unmarshal(new File("bosna.xml"));
        System.out.println(country.getCountryFoundationDate());
    }
}
