package com.kirabium.relayance.ui.addcustomer

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kirabium.relayance.R
import com.kirabium.relayance.databinding.ActivityAddCustomerBinding
import com.kirabium.relayance.extension.StringExt.Companion.isValidEmail
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddCustomerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCustomerBinding
    private val addCustomerActivityViewModel: AddCustomerActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupToolbar()
        setupListeners()
    }

    private fun setupListeners() {
        binding.emailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (!binding.emailEditText.text.toString().isValidEmail()) {
                    binding.emailEditText.error = getString(R.string.invalid_format)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })


        binding.saveFab.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()

            if (!binding.emailEditText.text.toString().isValidEmail()) {
                binding.emailEditText.error = getString(R.string.invalid_format)
                return@setOnClickListener
            }

            addCustomerActivityViewModel.addCustomer(name, email)
            Toast.makeText(this, R.string.customer_added, Toast.LENGTH_SHORT).show()
            finish()
        }
    }


    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupBinding() {
        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}