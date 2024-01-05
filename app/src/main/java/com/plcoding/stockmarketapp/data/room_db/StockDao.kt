package com.plcoding.stockmarketapp.data.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        companyListingEntities: List<StockTable>
    )

    @Query("DELETE FROM companyListing")
    suspend fun clearCompanyListings()

    // exp: https://youtu.be/uLs2FxFSWU4?t=1599s
    // check if query contains a name in DB. Or if the user looks for the tiker, too like TSLA
    // % can be any character
    @Query(
        """
            SELECT * 
            FROM companyListing
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == symbol
        """
    )
    suspend fun searchCompanyListing(query: String): List<StockTable>
}