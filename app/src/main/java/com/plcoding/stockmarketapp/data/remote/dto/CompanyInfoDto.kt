package com.plcoding.stockmarketapp.data.remote.dto

import com.squareup.moshi.Json

// @field:Json converts the received JSON da into another name
// exp: https://youtu.be/uLs2FxFSWU4?t=6544s
/*
In the context of your code snippet, CompanyInfoDto is a Data Transfer Object representing information about a company. It's designed to carry data between different parts of a program, and it's likely used for communication between, say, a backend server and a frontend application.
By using DTOs, you can encapsulate the data related to a specific entity (in this case, company information) in a structured way, making it easier to manage and transport across different components of your software system.
 */
data class CompanyInfoDto(
    @field:Json(name = "Symbol") val symbol: String?,
    @field:Json(name = "Description") val description: String?,
    @field:Json(name = "Name") val name: String?,
    @field:Json(name = "Country") val country: String?,
    @field:Json(name = "Industry") val industry: String?,
)
