package com.ebt.newsbook.model

sealed class Destination {
    data object Home : Destination()
    data class Detail(val rowId: Long) : Destination()
}