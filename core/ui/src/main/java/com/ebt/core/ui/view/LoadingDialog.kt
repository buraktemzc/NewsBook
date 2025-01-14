package com.ebt.core.ui.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.ebt.core.ui.databinding.BackgroundLoadingDialogBinding
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class LoadingDialog @Inject constructor(@ActivityContext private val context: Context) :
    Dialog(context) {

    private var binding = BackgroundLoadingDialogBinding.inflate(LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}