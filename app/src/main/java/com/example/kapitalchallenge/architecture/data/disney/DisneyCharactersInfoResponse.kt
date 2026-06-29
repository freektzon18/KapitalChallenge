package com.example.kapitalchallenge.architecture.data.disney

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("totalPages") var totalPages: Int? = null,
    @SerializedName("previousPage") var previousPage: String? = null,
    @SerializedName("nextPage") var nextPage: String? = null
)