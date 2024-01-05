package com.plcoding.stockmarketapp.domain.repository

import com.plcoding.stockmarketapp.domain.model.CompanyInfo
import com.plcoding.stockmarketapp.domain.model.CompanyListing
import com.plcoding.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean, //  exp: https://youtu.be/uLs2FxFSWU4?t=2393s
        query: String
    ): Flow<Resource<List<CompanyListing>>>

//    suspend fun getIntradayInfo(
//        symbol: String
//    ): Resource<List<IntradayInfo>>

//    suspend fun getCompanyInfo(
//        symbol: String
//    ): Resource<CompanyInfo>
}