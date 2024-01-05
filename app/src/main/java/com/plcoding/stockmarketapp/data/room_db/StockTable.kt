package com.plcoding.stockmarketapp.data.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "companyListing"
)
data class StockTable(
    val name: String,
    val symbol: String,
    val exchange: String,
    @PrimaryKey val id: Int? = null
)
