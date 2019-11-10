package org.mycompany.animals.dogs;

import org.mycompany.animals.AnimalFactory;
import org.mycompany.animals.dogs.domain.Dog;
import org.mycompany.animals.dogs.domain.Dogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Concrete implementation of the {@link AnimalFactory} class for the Dogs
 * objects
 *
 *  @author Antonio Fernandez Alhambra
 */
public class DogRegistryFactory implements AnimalFactory<DogRegistry> {

    /**
     * Log instance used for logging purposes.
     */
    private static final Logger log = LoggerFactory.getLogger(DogRegistryFactory.class);

    /**
     * Return a concrete implementation of the {@link DogRegistry} class
     * for the Dog type and read and parse the file to be loaded in memory.
     * @param fileName Name of the file to read the Dogs information from.
     * @return {@link DogRegistryImpl} Concrete implementation of
     * the {@link DogRegistry} class containing the list of dogs laded in memory
     * @throws JAXBException in case any exception when dealing with the marshal/unmarshal of the XML file.
     */
    @Override
    public DogRegistry load(String fileName) throws JAXBException {

        File xmlFile = new File(fileName);

        JAXBContext jaxbContext;

        jaxbContext = JAXBContext.newInstance(Dogs.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Dogs dogs = (Dogs) jaxbUnmarshaller.unmarshal(xmlFile);

        List<Dog> dogList = dogs.getDogs();

        log.debug("Loaded file with " + dogList);

        return new DogRegistryImpl(fileName, dogList);
    }
}
