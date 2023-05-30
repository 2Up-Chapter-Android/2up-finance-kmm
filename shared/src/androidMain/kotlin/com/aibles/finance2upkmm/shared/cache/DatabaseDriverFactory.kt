package com.aibles.finance2upkmm.shared.cache

import android.content.Context
import com.aibles.finance2upkmm.database.Finance2UpKMMDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Finance2UpKMMDatabase.Schema, context, "test.db")
    }
}