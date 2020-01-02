package com.github.floushee.playground

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route
import org.slf4j.LoggerFactory

fun Routing.calculatorAPI() {
    route("/api") {
        route("/calculator") {
            get("/sum/{a}/{b}") {
                val operands = Operands.of(call.parameters)
                if (operands != null) {
                    val result = Result(add(operands.a, operands.b))
                    call.respond(result)
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
            }
            get("/multiply/{a}/{b}") {
                val operands = Operands.of(call.parameters)
                if (operands != null) {
                    val result = Result(multiply(operands.a, operands.b))
                    call.respond(result)
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
            }
        }
    }
}

internal class Operands(val a: Double, val b: Double) {

    companion object {

        val logger = LoggerFactory.getLogger(this::class.java)

        fun of(parameters: io.ktor.http.Parameters): Operands? {
            val a = parameters["a"]
            val b = parameters["b"]
            if (a == null) {
                logger.debug("Missing argument [a]")
                return null
            }
            if (b == null) {
                logger.debug("Missing argumt [b]")
                return null
            }
            return try {
                Operands(a.toDouble(), b.toDouble())
            } catch (e: Throwable) {
                logger.error("Could not parse argument [$a] or [$b]")
                null
            }
        }
    }
}
