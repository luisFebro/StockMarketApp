package com.plcoding.stockmarketapp.presentation.company_listings

/*
In Kotlin, it's a common convention to use uppercase for the first letter of class and interface names. This convention, known as PascalCase, helps distinguish class and interface names from variable and function names, which typically use camelCase.
 */
/*
In summary, sealed interfaces provide a way to create a limited hierarchy of classes or interfaces, ensuring that all possible subclasses are known and handled appropriately.
 */

/* diff object vs data class
the main difference is that object is used for singleton instances without additional data, while data class is used when you need to hold and represent data with automatically generated methods for common operations.

 */

sealed class CompanyListingsEvent {
    object Refresh: CompanyListingsEvent()
    data class OnSearchQueryChange(val query: String): CompanyListingsEvent()
}
