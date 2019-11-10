package org.mycompany.animals.dogs;

import org.mycompany.animals.dogs.domain.Dogs;

public final class DogsFacade {

    private static Dogs dogs;
    private String info = "Initial info class";

    private DogsFacade() {
    }
/*
    public static ClassSingleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ClassSingleton();
        }

        return INSTANCE;
    }*/
}
