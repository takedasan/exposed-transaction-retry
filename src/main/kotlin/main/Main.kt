package main

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection
import java.sql.SQLException

fun main() {
    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
    var counter = 0

    transaction(Connection.TRANSACTION_SERIALIZABLE, 2) {
        println("transaction開始！: $counter")

        when (counter) {
            0 -> println("0に入ったよ")
            1 -> println("1に入ったよ")
            2 -> println("2に入ったよ")
            3 -> println("3に入ったよ")
        }

        counter++
        throw SQLException("Exception発生！：$counter")
    }
}