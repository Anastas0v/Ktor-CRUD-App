package com

import com.dto.Customer
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        post("/json") {
            val customer = call.receive<Customer>()
            call.respond(customer)
        }
    }
}
