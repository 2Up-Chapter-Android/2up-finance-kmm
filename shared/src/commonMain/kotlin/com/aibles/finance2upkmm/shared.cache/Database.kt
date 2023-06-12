package com.aibles.finance2upkmm.shared.cache

import com.aibles.finance2upkmm.database.Finance2UpKMMDatabase
import comaiblesfinance2upkmmdatabase.RegisterInfo


internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Finance2UpKMMDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.finance2UpKMMDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllInfo()
        }
    }

    // entity chưa chuẩn lắm
    internal fun getAllLaunches(): List<RegisterInfo> {
        return dbQuery.selectAllRegisterInfo().executeAsList()
    }

    private fun mapSelecting(
        status: Int,
        statusMessage: String,
        timeStamp: String
    ): com.aibles.finance2upkmm.model.RegisterInfo {
        return com.aibles.finance2upkmm.model.RegisterInfo(
            status,
            statusMessage,
            timeStamp
        )
    }


}