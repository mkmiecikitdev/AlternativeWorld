package eu.hexgate.alternativeworld

import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.ipc.netty.http.server.HttpServer


fun main(args: Array<String>) {

    val route = router {
        GET("/test") {
            return@GET ServerResponse.ok().body(fromObject("yyoyyoyoyo"))
        }
    }

    val httpHandler = RouterFunctions.toHttpHandler(route)
    val adapter = ReactorHttpHandlerAdapter(httpHandler)

    HttpServer
            .create("localhost", 8080)
            .start(adapter)



}
