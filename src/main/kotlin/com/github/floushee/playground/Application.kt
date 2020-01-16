package com.github.floushee.playground

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.jackson.jackson
import io.ktor.response.respondText
import io.ktor.routing.Routing

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

fun Application.module() {
    moduleWithDependencies()
}

fun Application.moduleWithDependencies() {
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