package io.logicojp.example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.Map;
import java.util.Optional;

@Controller("/env")
public class Environment {

    @Get(produces = MediaType.APPLICATION_JSON)
    Optional<Map<String, String>> environment() {
        // Return Environment variables defined in the environment simplily
        return Optional.of(System.getenv());
    }
}
