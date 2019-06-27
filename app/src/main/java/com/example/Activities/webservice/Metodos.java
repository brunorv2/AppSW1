package com.example.Activities.webservice;

public class Metodos {

    public static <T> T get(Class<T> c){
        return WebService.get().create(c);
    }
}
