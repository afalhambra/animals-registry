package org.mycompany.animals.dogs;

import org.junit.jupiter.api.Test;
import org.mycompany.animals.AnimalFactory;
import org.mycompany.animals.AnimalType;
import org.mycompany.animals.FactoryProvider;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import javax.xml.bind.JAXBException;
import java.util.concurrent.TimeUnit;

/**
 * Micro-benchmark test class that measures execution time of the "averageWeight" method
 * in class {@link org.mycompany.animals.dogs.DogRegistry}
 */
public class DogRegistryBenchMarkTest {

    /**
     * XML file to read and load a list of Dogs from.
     */
    static final String dogsFile = "src/test/resources/dogs.xml";
    /**
     * Animal Factory instance class for getting a concrete DogRegistry class instance.
     */
    static AnimalFactory animalFactory;
    /**
     * Concrete instance class to call "averageWeight" method
     */
    static DogRegistry dogRegistry;

    static  {
        animalFactory = FactoryProvider.getFactory(AnimalType.DOG);
        try {
            dogRegistry = (DogRegistry) animalFactory.load(dogsFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches main JHM class and configure default details to start benchmarking the "averageWeight"
     * method.
     * JMH Configuration:
     *  - Mode: AverageTime
     *  - Time measure: Microseconds
     *  - Forks: 1
     *  - Warmup iterations: 10
     *  - Threads: 2
     *  - ShouldFailOnError: True
     *  - ShouldDoGC: True
     * @throws RunnerException In case of an error when launching and configuring JMH
     */
    @Test
    public void launchBenchMark() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(this.getClass().getName() + ".*")
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.microseconds(1))
                .warmupIterations(5)
                .measurementTime(TimeValue.microseconds(1))
                .measurementIterations(5)
                .threads(2)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();
        new Runner(opt).run();
    }

    /**
     * Benchmarks the method call to {@link DogRegistryImpl#averageWeightPerBreed()} method
     * class.
     */
    @Benchmark
    public void benchMarkAverageWeight(){
        dogRegistry.averageWeightPerBreed();
    }

    @Benchmark
    public int baseline(){
        int a = 1;
        int b = 2;
        return a+b;
    }
}
