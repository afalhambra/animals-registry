package org.mycompany.animals;

import org.mycompany.animals.dogs.DogRegistryFactory;

/**
 * Factory provider to get a concrete animal factory {@link AnimalFactory}
 * based on the type of animal passed as an argument in the getFactory method.
 *
 *  @author Antonio Fernandez Alhambra
 */
public class FactoryProvider {
    /**
     * Returns an instance of a concrete animal factory based on the animal type passed as argument
     * @param animalType {@link AnimalType}
     * @return A concrete instance of {@link AnimalFactory} available or null
     * if not available
     */
    public static AnimalFactory getFactory(AnimalType animalType){
        
        if(AnimalType.DOG == animalType){
            return new DogRegistryFactory();
        } else {
            return null;
        }
    }
}