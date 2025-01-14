package com.ebt.core.ui.extensions

import com.ebt.core.theme.R.color as themeColorResource

fun Boolean.findUpdatedColor(): Int =
    if (this) {
        themeColorResource.red
    } else {
        themeColorResource.black
    }