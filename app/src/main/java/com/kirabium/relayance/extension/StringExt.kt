package com.kirabium.relayance.extension

import androidx.core.util.PatternsCompat

class StringExt {

    companion object {
        fun CharSequence?.isValidEmail() = !isNullOrEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
    }
}