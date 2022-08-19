package io.logicojp.example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.runtime.Micronaut;

@Controller("/env")
public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
