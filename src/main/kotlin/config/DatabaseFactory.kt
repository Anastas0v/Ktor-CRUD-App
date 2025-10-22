package com.config

import com.model.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        Database.connect("jdbc:postgresql://localhost:5432/ktor_crud", driver = "org.postgresql.Driver", user = "user", password = "admin")
        transaction {
            SchemaUtils.create(Users);
        }
    }
}