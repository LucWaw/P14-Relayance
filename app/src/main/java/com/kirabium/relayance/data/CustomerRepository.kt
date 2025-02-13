package com.kirabium.relayance.data

import com.kirabium.relayance.domain.model.Customer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CustomerRepository {

    fun getCustomers() : Flow<List<Customer>> =
        flow {
            emit(DummyData.customers.value)
        }

    fun getCustomerById(id: Int) : Flow<Customer> =
        getCustomers().map { customers ->
            customers.find { it.id == id } ?: throw IllegalArgumentException("Customer not found")
        }

    fun addCustomer(name: String, email: String) {
        DummyData.addCustomer(name, email)
    }


}