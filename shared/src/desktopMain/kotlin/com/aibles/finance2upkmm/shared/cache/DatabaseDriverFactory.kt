package com.aibles.finance2upkmm.shared.cache

import com.aibles.finance2upkmm.database.Finance2UpKMMDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also {
            Finance2UpKMMDatabase.Schema.create(it)
        }
        return driver
    }

}