package org.mycompany.animals;

import org.mycompany.animals.dogs.DogRegistryFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Factory provider to get a concrete animal factory {@link AnimalFactory}
 * based on the type of animal passed as an argument in the getFactory method.
 *
 *  @author Antonio Fernandez Alhambra
 */
public class FactoryProvider {

    /**
     * Static {@link Map} object containing all the available factories in the application
     * for each {@link AnimalType}
     */
    private final static Map<AnimalType, Supplier<AnimalFactory>> factoryMap = new HashMap<>();

    static {
        factoryMap.put(AnimalType.DOG, DogRegistryFactory::new);
    }

    /**
     * Returns an instance of a concrete animal factory based on the animal type passed as argument
     * @param animalType {@link AnimalType}
     * @return A concrete instance of {@link AnimalFactory} available or null
     * if not available
     * @throws IllegalArgumentException Exception to be thrown in case the {@link AnimalType} object passed
     * is not implemented
     */
    public static AnimalFactory getFactory(AnimalType animalType){

        Supplier<AnimalFactory> animalFactory = factoryMap.get(animalType);

        if (animalFactory != null) {
            return animalFactory.get();
        }
        throw new IllegalArgumentException("This animal is not available");
    }
}