package org.mycompany.animals.dogs;

import org.mycompany.animals.dogs.domain.Dog;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Predicate;

/**
 * Public interface with all the methods required for querying and registering a particular animal.
 * @param <T> Enum type to implement depending of the breed type
 */
public interface DogRegistry<T extends Enum<T>> {

    /**
     * Computes an average weight for a particular breed accepting breed as a parameter
     * @param breed Breed type object to computes the average.
     * @return Weight average of this particular breed.
     */
    double averageWeight(T breed);

    /**
     * Returns average weight per breed for all breeds
     * @return {@link java.util.EnumMap} Where T is
     * the key used for this class and the value is a {@link java.lang.Double} object as the average
     * weight
     */
    EnumMap<T, Double> averageWeightPerBreed();

    /**
     * Returns a list of all dogs based on some predicate, which
     * is passed as a parameter
     * @param predicate {@link java.util.function.Predicate}
     * containing the predicate to be queried against the list of dogs.
     * @return {@link java.util.List} List of dog which satisfy
     * the condition passed as argument
     */
    List<Dog> dogsByCondition(Predicate<Dog> predicate);

    /**
     * Returns the oldest dog born after a certain date passed as argument
     * @param date {@link java.time.LocalDate}
     * @return Oldest {@link org.mycompany.animals.dogs.domain.Dog} object born after the date passed as
     * argument.
     */
    Dog oldestDogAfterDate(LocalDate date);
}