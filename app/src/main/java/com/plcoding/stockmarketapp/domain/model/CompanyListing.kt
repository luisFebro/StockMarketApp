package com.plcoding.stockmarketapp.domain.model

// exp: https://youtu.be/uLs2FxFSWU4?t=1265s
// data is separated from any third-party library(e.g room and retrofit) so that we can use in the presentation layer
// thus, this data is independent. if we need to change our db, this domain level stays intact
data class CompanyListing(
    val name: String,
    val symbol: String,
    val exchange: String,
)
