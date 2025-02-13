package com.kirabium.relayance.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirabium.relayance.data.CustomerRepository
import com.kirabium.relayance.domain.model.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DetailActivityViewModel @Inject constructor(private val repository: CustomerRepository) : ViewModel() {
    fun getCustomerById(id: Int): StateFlow<Customer> = repository.getCustomerById(id)
        .stateIn(
            scope = viewModelScope,
            initialValue = Customer(-1, "", "", Date()), // set initial value here
            started = SharingStarted.WhileSubscribed(5000)
        )
}