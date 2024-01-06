package com.plcoding.stockmarketapp.presentation.company_listings

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.stockmarketapp.domain.repository.StockRepository
import com.plcoding.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val repository: StockRepository
): ViewModel() {

    var state by mutableStateOf(CompanyListingsState())

    // exp: https://youtu.be/uLs2FxFSWU4?t=4432s
    /*
        Without searchJob, there might be potential issues with concurrent searches. If a new search is triggered before the previous one completes, it could lead to unexpected behavior or unnecessary resource consumption. Using searchJob allows you to cancel the ongoing search before starting a new one, preventing overlapping and ensuring that only the latest search is executed.
        In short, searchJob helps in managing the execution flow and avoids potential conflicts when handling search queries.
     */
    private var searchJob: Job? = null

    init {
        getCompanyListings()
    }

    fun onEvent(event: CompanyListingsEvent) {
        when(event) {
            is CompanyListingsEvent.Refresh -> {
                getCompanyListings(fetchFromRemote = true)
            }
            is CompanyListingsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)

                /* Here it is using a throttling
                Debouncing delays the execution of your code until the user stops performing a certain action for a specified amount of time. Throttling limits the execution of your code to once in every specified time interval
                 // exp: https://youtu.be/uLs2FxFSWU4?t=4509s
                 */

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyListings()
                }
            }
        }
    }

    // API REQUEST
    private fun getCompanyListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getCompanyListings(fetchFromRemote, query)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    companies = listings
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}