package com.plcoding.stockmarketapp.data.mapper

import com.plcoding.stockmarketapp.domain.model.CompanyListing
import com.plcoding.stockmarketapp.data.remote.dto.CompanyInfoDto
import com.plcoding.stockmarketapp.data.room_db.StockTable
import com.plcoding.stockmarketapp.domain.model.CompanyInfo

fun StockTable.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toStockTable(): StockTable {
    return StockTable(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}