package com

import com.config.DatabaseFactory
import com.routes.userRoutes
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init(environment)
    configureRouting()
    userRoutes()
}
