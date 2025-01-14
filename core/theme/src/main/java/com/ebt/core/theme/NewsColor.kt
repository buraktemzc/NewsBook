package com.ebt.core.theme

object NewsColor {

    fun findUpdatedColor(updated: Boolean): Int = if (updated) {
        R.color.red
    } else {
        R.color.black
    }
}