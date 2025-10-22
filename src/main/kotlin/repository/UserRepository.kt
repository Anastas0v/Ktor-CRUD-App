package com.repository

import com.dto.User
import com.model.Users
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.transactions.transaction

object UserRepository {
    // map from Users to User(DTO)
    fun getAll(): List<User> = transaction {
        Users.selectAll().map{
            User(it[Users.id], it[Users.firstName], it[Users.lastName])
        }
    }

    fun getUserById(id: Int): User? = transaction {
        Users.select{Users.id eq id}
        .map{ User(it[Users.id], it[Users.firstName], it[Users.lastName]) }
        .singleOrNull()
    }

    fun save(user: User): User = transaction {
        val id = Users.insert {
            it[firstName] = user.firstName
            it[lastName] = user.lastName
        } get Users.id

        user.copy(id = id)
    }

    fun update(id: Int, user: User): Boolean = transaction {
        Users.update({ Users.id eq id }) {
            it[firstName] = user.firstName
            it[lastName] = user.lastName
        } > 0
    }

    fun delete(id: Int): Boolean = transaction {
        Users.deleteWhere { Users.id eq id } > 0
    }
}