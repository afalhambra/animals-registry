package org.mycompany.animals.dogs;

import org.mycompany.animals.AnimalType;
import org.mycompany.animals.dogs.config.DogRegistryConfig;
import org.mycompany.animals.dogs.domain.Dog;
import org.mycompany.animals.dogs.domain.DogBreed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

/**
 * This class is a particular and specific implementation of the interface
 * {@link DogRegistry}. This is the logic for a Dog type from
 * {@link AnimalType}.
 * Contains a in-memory object with all the information fetched and retrieved from a particular file.
 * All the implementation logic and query will be done against this particular object in memory.
 *
 * @author Antonio Fernandez Alhambra
 */
public class DogRegistryImpl implements DogRegistry<DogBreed> {

    /**
     * Log instance used for logging purposes.
     */
    private static final Logger log = LoggerFactory.getLogger(DogRegistryImpl.class);

    /**
     * A Java List object containing the information read from a file with the dogs information.
     */
    private List<Dog> dogsList = new ArrayList<>();

    /**
     * Config instance used mainly for getting a default date format used in the dateOfBirth field
     * in the {@link org.mycompany.animals.dogs.domain.Dog}.
     */
    private DogRegistryConfig config = new DogRegistryConfig();

    /**
     * Constructor only available for the classes in this package to avoid any misused
     * @param dogs list of Dogs read from the file
     */
    DogRegistryImpl(List<Dog> dogs) { dogsList.addAll(dogs); }

    /**
     * Computes an average weight for a particular breed accepting breed as a parameter
     * @param breed {@link org.mycompany.animals.dogs.domain.DogBreed} object to computes the average.
     * @return Weight average of this particular breed.
     */
    @Override
    public double averageWeight(DogBreed breed) {
        double averageWeight = dogsList.stream()
                .filter( dog -> dog.getBreed() == breed )
                .mapToDouble(Dog::getWeight)
                .average()
                .orElse(0);
        log.debug(breed.value() + " average weight is " + averageWeight);
        return averageWeight;
    }

    /**
     * Returns average weight per breed for all breeds
     * @return {@link java.util.EnumMap} Where {@link org.mycompany.animals.dogs.domain.DogBreed} is
     * the key used for this class and the value is a {@link java.lang.Double} object as the average
     * weight
     */
    @Override
    public EnumMap<DogBreed, Double> averageWeightPerBreed() {
        EnumMap<DogBreed, Double> enumMap = dogsList.stream()
                .collect(groupingBy(
                        Dog::getBreed,
                        () -> new EnumMap<>(DogBreed.class),
                        averagingDouble(Dog::getWeight))
                );
        log.debug("Average Weight for all breeds: " + enumMap);
        return enumMap;
    }

    /**
     * Returns a list of all dogs based on some predicate, which
     * is passed as a parameter
     * @param predicate {@link java.util.function.Predicate}
     * containing the predicate to be queried against the list of dogs.
     * @return {@link java.util.List} List of dog which satisfy
     * the condition passed as argument
     */
    @Override
    public List<Dog> dogsByCondition(Predicate<Dog> predicate) {
        List<Dog> dogs = dogsList.stream()
                .filter(predicate)
                .collect(toList());
        log.debug("List of dogs meeting condition: " + dogs);
        return dogs;
    }

    /**
     * Returns the oldest dog born after a certain date passed as argument
     * @param date {@link java.time.LocalDate}
     * @return Oldest {@link org.mycompany.animals.dogs.domain.Dog} object born after the date passed as
     * argument.
     */
    @Override
    public Dog oldestDogAfterDate(LocalDate date) {
        Dog oldestDog = dogsList.stream()
                .filter( dog -> {
                    LocalDate dob = LocalDate.parse(dog.getDateOfBirth(), config.getDateFormat());
                    return dob.isAfter(date);
                })
                .reduce( (dogA, dogB) -> {
                    LocalDate dobA = LocalDate.parse(dogA.getDateOfBirth(), config.getDateFormat());
                    LocalDate dobB = LocalDate.parse(dogB.getDateOfBirth(), config.getDateFormat());
                    return (dobA.isBefore(dobB)) ? dogA : dogB;
                })
                .orElse(null);
        log.debug("Oldest dog born after " + date + " is " + oldestDog);
        return oldestDog;
    }
}
