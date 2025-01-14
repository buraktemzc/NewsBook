package com.ebt.data.model.response

import com.ebt.base.data.model.ResponseModel
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("userId")
    val userId: Long?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("body")
    val body: String?
) : ResponseModel