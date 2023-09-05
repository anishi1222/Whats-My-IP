package io.logicojp.example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.reactivestreams.Publisher;

@Controller("/caller")
public class GeoIPController {

    private final GeoIPClient geoIPClient;

    public GeoIPController(GeoIPClient geoIPClient) {
        this.geoIPClient = geoIPClient;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    Publisher<String> sayHello() {
        return geoIPClient.getMyIpText();
    }
}
