package com.routes

import com.dto.User
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.repository.UserRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive

fun Routing.userRoutes() {
    val userRepository = UserRepository()

    route("/users") {
        get {
            call.respond(userRepository.getAll())
        }

        get("{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val user = id?.let { userRepository.getUserById(it) }
            if (user == null)
                call.respondText("User not found", status = HttpStatusCode.NotFound)
            else
                call.respond(user)
        }

        // body user
        post {
            try {
                val user = call.receive<User>()
                val savedUser = userRepository.save(user)
                call.respond(savedUser)
            } catch (e: Exception) {
                call.respondText(
                    "Invalid request body: ${e.localizedMessage}",
                    status = HttpStatusCode.BadRequest
                )
            }
        }

        // /id && body user
        put("{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val user = call.receive<User>()

            val updatedUser = id?.let { userRepository.update(it, user) } ?: false
            if (updatedUser)
                call.respondText("User updated successfully", status = HttpStatusCode.OK)
            else
                call.respondText("User not found", status = HttpStatusCode.NotFound)
        }

        delete("{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val user = id?.let { userRepository.delete(it) } ?: false
            if (user)
                call.respondText("User deleted successfully", status = HttpStatusCode.OK)
            else
                call.respondText("User not found", status = HttpStatusCode.NotFound)
        }
    }

}