package io.logicojp.example.micronaut;
import org.reactivestreams.Publisher;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("https://ip.seeip.org/geoip")
public interface GeoIPClient  {
    @Get(consumes = MediaType.APPLICATION_JSON)
    @SingleResult
    Publisher<String> getMyIpText();
}
