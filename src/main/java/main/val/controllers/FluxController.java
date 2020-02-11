package main.val.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/flux")
public class FluxController {
    @GetMapping("/get")
    public Flux<Integer> returnFlux(){
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/stream" ,produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public  Flux<Integer> returnFluxStream(){
        return Flux.just(1,2,3,4).delayElements(Duration.ofSeconds(1)).log();
    }
    @GetMapping(value = "/stream1" ,produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public  Flux<Long> returnFluxStream1(){
        return Flux.interval(Duration.ofSeconds(1)).log();
    }
}
