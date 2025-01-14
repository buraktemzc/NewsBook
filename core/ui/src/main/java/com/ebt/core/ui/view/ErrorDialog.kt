package com.ebt.core.ui.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.StringRes
import com.ebt.core.ui.databinding.BackgroundErrorDialogBinding
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ErrorDialog @Inject constructor(@ActivityContext private val context: Context) :
    Dialog(context) {

    private var binding = BackgroundErrorDialogBinding.inflate(LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setCancelable(false)
        binding.okButton.setOnClickListener {
            dismiss()
        }
    }

    fun setErrorMessage(errorMessage: String) {
        binding.descriptionText.text = errorMessage
    }

    fun setErrorMessage(@StringRes errorRes: Int) {
        binding.descriptionText.text = context.getString(errorRes)
    }
}