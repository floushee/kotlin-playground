package com.github.floushee.playground

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.jackson.jackson
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, 8080) {
        install(Routing) {
            trace { application.log.trace(it.buildText()) }
            calculatorAPI()
            helloWorldAPI()
        }
        install(StatusPages) {
            this.exception<Throwable> { e ->
                call.respondText(e.localizedMessage, ContentType.Text.Plain)
                throw e
            }
        }
        install(ContentNegotiation) {
            jackson {  }
        }
    }
    server.start(wait = true)
}