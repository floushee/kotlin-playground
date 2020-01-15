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
            get("/{operator}/{a}/{b}") {
                val operator = Operator.of(call.parameters)
                if (operator == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

                val operands = Operands.of(call.parameters)
                if (operands == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

                val result = Result(operator.operate(operands.a, operands.b))
                call.respond(result)
            }
        }
    }
}

internal  sealed class Operator(val operate: (Double, Double) -> Double) {
    class Plus() : Operator(::add)
    class Multiply : Operator(::multiply)
    class Subtract : Operator(::subtract)
    class Divide : Operator(::divide)
    companion object {
        fun of(parameters: io.ktor.http.Parameters): Operator? {
            return when (parameters["operator"]) {
                "plus" -> Plus()
                "multiply" -> Multiply()
                "subtract" -> Subtract()
                "divide" -> Divide()
                else -> null
            }
        }
    }
}

internal class Operands(val a: Double, val b: Double) {

    companion object {
        fun of(parameters: io.ktor.http.Parameters): Operands? {
            val a = parameters["a"]
            val b = parameters["b"]
            if (a == null) {
                return null
            }
            if (b == null) {
                return null
            }
            return try {
                Operands(a.toDouble(), b.toDouble())
            } catch (e: Throwable) {
                null
            }
        }
    }
}
