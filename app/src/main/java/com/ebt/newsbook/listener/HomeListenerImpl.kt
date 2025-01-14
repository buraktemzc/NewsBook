package com.ebt.newsbook.listener

import com.ebt.features.home_impl.listener.HomeListener
import com.ebt.newsbook.MainActivity
import javax.inject.Inject

class HomeListenerImpl @Inject constructor(
    private val mainActivity: MainActivity
) : HomeListener {
    override fun onNewsSelected(rowId: Long) {
        mainActivity.onNewsSelected(rowId)
    }
}