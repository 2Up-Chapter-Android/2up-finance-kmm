package com.aibles.finance2upkmm.shared.cache

import com.aibles.finance2upkmm.database.Finance2UpKMMDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(Finance2UpKMMDatabase.Schema, "test.db")
    }
}