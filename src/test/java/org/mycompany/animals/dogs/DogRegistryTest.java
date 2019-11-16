package org.mycompany.animals.dogs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mycompany.animals.AnimalFactory;
import org.mycompany.animals.AnimalType;
import org.mycompany.animals.FactoryProvider;
import org.mycompany.animals.dogs.config.DogRegistryConfig;
import org.mycompany.animals.dogs.domain.Dog;
import org.mycompany.animals.dogs.domain.DogBreed;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class performs some test cases against all the {@link org.mycompany.animals.dogs.DogRegistry} class
 * methods. Performs both positive and negative tests.
 */
class DogRegistryTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    /**
     * XML file to read and load a list of Dogs from.
     */
    final static String dogsFile =  "src/test/resources/dogs.xml";
    /**
     * Animal Factory instance class for getting a concrete DogRegistry class instance.
     */
    static AnimalFactory animalFactory;
    /**
     * Concrete instance class of {@link DogRegistry}
     */
    static DogRegistry dogRegistry;
    /**
     * {@link java.util.function.BiConsumer} instance class with assertEquals expected as per Dog bread
     * and its corresponding weight average as per the information contained in the dogs file used for this tests.
     */
    static BiConsumer<DogBreed, Double> breedEnumDoubleBiConsumer = (breed, avgWeight) -> {
        switch (breed){
            case RHODESIAN_RIDGEBACK:
                assertEquals(38.0, avgWeight);
                break;
            case BERNESE_MOUNTAIN_DOG:
                assertEquals(55.0, avgWeight);
                break;
            case SHIBA_INU:
                assertEquals(12.25, avgWeight);
                break;
            case GERMAN_SHEPHERD:
                assertEquals(24.400000000000002, avgWeight);
                break;
            case LABRADOR_RETRIEVER:
                assertEquals(33.0, avgWeight);
                break;
            case GREYHOUND:
            case SIBERIAN_HUSKY:
                assertEquals(29.0, avgWeight);
                break;
            case JAPANESE_SPITZ:
                assertEquals(12.0, avgWeight);
                break;
            case BOHEMIAN_WIREHAIRED_POINTING_GRIFFON:
                assertEquals(28.0, avgWeight);
                break;
            default:
                fail("Non valid breed has been found");
                break;
        }
    };

    /**
     * Initial method to load and instantiate the {@link DogRegistry} class with the information
     * contained in the XML file to be used across all test methods.
     * @throws JAXBException
     */
    @BeforeAll
    public static void init() throws JAXBException {
        animalFactory = FactoryProvider.getFactory(AnimalType.DOG);
        dogRegistry = (DogRegistry) animalFactory.load(dogsFile);
        System.setOut(new PrintStream(outContent));
        DogRegistryConfig.setEnableLogging(true);
    }

    /**
     * Last method to call in the testing to clean up all the references to the {@link DogRegistry} class
     */
    @AfterAll
    public static void cleanUp() {
        System.setOut(originalOut);
        dogRegistry = null;
    }

    /**
     * Tests the {@link DogRegistry#averageWeight(Enum)} averageWeight} method.
     * Input {@link DogBreed} is parameterized so all different breed types will be passed and thus this
     * method will be executed once per each {@link DogBreed} enum type.
     * @param breed Parameterized {@link DogBreed} as input so all different breed types will be passed.
     */
    @ParameterizedTest
    @EnumSource(DogBreed.class)
    void averageWeight(DogBreed breed) {
        double avgWeight;
        avgWeight = dogRegistry.averageWeight(breed);
        breedEnumDoubleBiConsumer.accept(breed, avgWeight);
    }

    /**
     * Tests the {@link DogRegistry#averageWeightPerBreed()} averageWeightPerBreed} method as expected
     * weight average for each dog breed specified in the {@link DogRegistryTest#dogsFile dogsFile} attribute
     */
    @Test
    void averageWeightPerBreed() {
        EnumMap<DogBreed, Double> enumMap = dogRegistry.averageWeightPerBreed();
        enumMap.forEach(breedEnumDoubleBiConsumer);
    }

    /**
     * Tests the {@link DogRegistry#dogsByCondition(Predicate)} dogsByCondition} method.
     * As a sample {@link Predicate} in this test:
     * Dog weight > 38 and Dog name equal to "Rex" - so only one Dog in the file meets this
     * condition and is expected to be return.
     */
    @Test
    void dogsByCondition() {
        Predicate<Dog> predicate = dog -> dog.getWeight()>38 && dog.getName().equals("Rex");
        List<Dog> dogList = dogRegistry.dogsByCondition(predicate);
        assertEquals(1, dogList.size());
        assertEquals("Rex", dogList.get(0).getName());
    }

    /**
     * Tests the {@link DogRegistry#oldestDogAfterDate(LocalDate) oldestDogAfterDate} method.
     * For this particular test, date will be 04-02-2005 so the oldest Dog in the file born after
     * that date is Riki (05-02-2005), Rhodesian Ridgeback breed.
     */
    @Test
    void oldestDogAfterDate() {
        LocalDate date = LocalDate.parse("04-02-2005", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Dog dog = dogRegistry.oldestDogAfterDate(date);
        assertEquals("Riki", dog.getName());
        assertEquals("05-02-2005", dog.getDateOfBirth());
        assertEquals(38.0, dog.getWeight());
        assertEquals("Rhodesian Ridgeback", dog.getBreed().value());
    }

    @Test
    void loadNonValidFile() {
        assertThrows(JAXBException.class, () -> {
            animalFactory = FactoryProvider.getFactory(AnimalType.DOG);
            animalFactory.load("fake.xml");
        });
    }

    @Test
    void nonValidAnimalFactoryType() {
        assertThrows(IllegalArgumentException.class, () ->{
            animalFactory = FactoryProvider.getFactory(AnimalType.CAT);
        });
    }

    @Test
    void enableLogging(){
        DogRegistryConfig.setEnableLogging(true);
        assertThat(outContent.toString(), containsString("logging is enabled"));
    }

    @Test
    void disableLogging(){
        DogRegistryConfig.setEnableLogging(false);
        assertThat(outContent.toString(), containsString("logging is disabled"));
    }

}