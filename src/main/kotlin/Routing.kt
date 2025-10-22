package com

import com.dto.User
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        post("/"){
            val text = call.receive<User>()
            call.respond(text)
        }
    }
}
