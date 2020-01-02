package com.github.floushee.playground

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.helloWorldAPI() {
    route("/api") {
        route("/helloworld") {
            get("/") {
                call.respondText("Hello, World!")
            }
        }
    }
}