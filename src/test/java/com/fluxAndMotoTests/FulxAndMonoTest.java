package com.fluxAndMotoTests;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

public class FulxAndMonoTest {
    @Test
    public void fluxTest() {
        Flux<String> stringFlux = Flux.just("Test", "Test1", "Test3")
                .concatWith(Flux.error(new RuntimeException("Exaption Occurad")))
                .concatWith(Flux.just("After error"))
                .log();
        stringFlux
                .subscribe(System.out::println,
                        System.err::println);
    }

    @Test
    public  void fluxTest2(){
        Flux<String> stringFlux = Flux.just("Test", "Test1", "Test3")
                .concatWith(Flux.error(new RuntimeException("Exaption Occurad")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Test")
                .expectNext("Test1")
                .expectNext("Test3")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void monoTest(){
        Mono<String> stringMono=Mono.just("String").log();
        StepVerifier.create(stringMono)
                .expectNext("String")
                .verifyComplete();
    }
    @Test
    public  void Test3(){
        List<String> list=new ArrayList<>();
        list.add("jack");
        list.add("kalic");
        Flux<String> flux=Flux.fromStream(list.stream());
        flux.subscribe(System.out::println);
    }

    @Test
    public  void test4(){
        List<String> list=new ArrayList<>();
        list.add("jack");
        list.add("kalic");
        Flux<Integer> integerFlux=Flux.fromIterable(list)
                .map(s -> s.length())
                .repeat(1)
                .log();
        StepVerifier.create(integerFlux)
                .expectNext(4,5,4,5)
                .verifyComplete();

    }
}
