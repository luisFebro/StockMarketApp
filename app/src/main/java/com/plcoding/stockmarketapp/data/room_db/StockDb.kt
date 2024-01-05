package com.plcoding.stockmarketapp.data.room_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [StockTable::class],
    version = 1
)
abstract class StockDb: RoomDatabase() {
    abstract val dao: StockDao
}