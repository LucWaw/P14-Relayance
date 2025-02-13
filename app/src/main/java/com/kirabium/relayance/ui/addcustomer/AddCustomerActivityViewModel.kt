package com.kirabium.relayance.ui.addcustomer

import androidx.lifecycle.ViewModel
import com.kirabium.relayance.data.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCustomerActivityViewModel @Inject constructor(private val repository: CustomerRepository) : ViewModel() {
    fun addCustomer(name: String, email: String) {
        repository.addCustomer(name, email)
    }
}