package org.mycompany.animals;

import javax.xml.bind.JAXBException;

/**
 * Animal Abstract Factory interface defining one single method to load a particular animal instance class.
 * @param <T> Generic type to be passed as parameter in order to implement the registry interfaces with
 *           the functionality
 *
 * @author Antonio Fernandez Alhambra
 */
public interface AnimalFactory<T> {
    /**
     * Interface method to load a particular instance class into memory once from a particular file
     * based on the fileName passed as argument.
     * @param fileName File name to read
     * @return Concrete implementation of a particular Animal Factory
     * @throws JAXBException Exception thrown in case of any error when marshaling/unmarshaling the
     * XML file into objects
     */
    T load(String fileName) throws JAXBException;
}