package com.fluxAndMotoTests;


import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

public class testVirtualizeClock {

    @Test
    public void combineUsingConcst_withDelay(){
        VirtualTimeScheduler.getOrSet();
        Flux<String> flax1=Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
        Flux<String> flax2=Flux.just("D","E","F").delayElements(Duration.ofSeconds(1));

        Flux<String> mergedFlux=Flux.concat(flax1,flax2);
        StepVerifier.withVirtualTime(()->mergedFlux.log())
                .expectSubscription()
                .thenAwait(Duration.ofSeconds(6))
                .expectNextCount(6)
                .verifyComplete();
    }

}
