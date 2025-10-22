package com

import com.config.DatabaseFactory
import com.routes.userRoutes
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
                isLenient = true
            }
        )
    }

    DatabaseFactory.init(environment)

    routing {
        userRoutes()
    }
}
