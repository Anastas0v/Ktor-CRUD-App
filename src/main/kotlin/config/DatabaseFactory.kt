package com.config

import com.model.Users
import io.ktor.server.application.ApplicationEnvironment
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
/*    fun init() {
        Database.connect("jdbc:postgresql://localhost:5432/ktor_crud", driver = "org.postgresql.Driver", user = "user", password = "admin")
        transaction {
            SchemaUtils.create(Users);
        }
    }*/

    fun init(environment: ApplicationEnvironment) {
        val config = environment.config.config("database")
        val url = config.property("url").getString()
        val user = config.property("user").getString()
        val password = config.property("password").getString()
        val driver = config.property("driver").getString()

        Database.connect(url, driver, user, password)

        transaction {
            SchemaUtils.create(Users)
        }
    }
}