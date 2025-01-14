package com.ebt.core.ui.extensions

import android.view.View

fun View.showIfNeeded(visible: Boolean) {
    visibility = if (visible)
        View.VISIBLE
    else
        View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}