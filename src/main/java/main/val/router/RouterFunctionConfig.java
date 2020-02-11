package main.val.router;

import main.val.handler.SimpleHendlerFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;


@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> router(SimpleHendlerFunction simpleHendlerFunction) {
        return RouterFunctions.route(GET("/func/flux").and(accept(MediaType.APPLICATION_JSON)),simpleHendlerFunction::flux)
                .andRoute(GET("/func/mono").and(accept(MediaType.APPLICATION_JSON)),simpleHendlerFunction::mono);
    }
}
