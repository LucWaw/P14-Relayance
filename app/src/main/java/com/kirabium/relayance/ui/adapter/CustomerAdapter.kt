package com.kirabium.relayance.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kirabium.relayance.databinding.CustomerItemBinding
import com.kirabium.relayance.domain.model.Customer

class CustomerAdapter(private val onClick: (Customer) -> Unit) : ListAdapter<Customer,CustomerAdapter.CustomerViewHolder>(
    DIFF_CALLBACK
) {

    class CustomerViewHolder(private val binding: CustomerItemBinding, val onClick: (Customer) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        private var currentCustomer: Customer? = null

        init {
            binding.root.setOnClickListener {
                currentCustomer?.let {
                    onClick(it)
                }
            }
        }

        fun bind(customer: Customer) {
            currentCustomer = customer
            with(binding) {
                nameTextView.text = customer.name
                emailTextView.text = customer.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding = CustomerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = getItem(position)

        holder.bind(customer)
    }


    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Customer> =
            object : DiffUtil.ItemCallback<Customer>() {
                override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
                    return oldItem === newItem
                }

                override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
