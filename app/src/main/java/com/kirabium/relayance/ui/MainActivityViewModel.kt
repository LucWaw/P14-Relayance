package com.kirabium.relayance.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirabium.relayance.data.CustomerRepository
import com.kirabium.relayance.domain.model.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(repository: CustomerRepository)  : ViewModel(){
    val customers: StateFlow<List<Customer>> = repository.getCustomers()
        .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(), // set initial value here
            started = SharingStarted.WhileSubscribed(2000)
        )
}