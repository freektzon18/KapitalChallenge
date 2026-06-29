package com.example.kapitalchallenge.architecture.data.disney

import com.google.gson.annotations.SerializedName

data class DisneyCharactersRespose(
    @SerializedName("info") var info: InfoResponse = InfoResponse(),
    @SerializedName("data") var data: ArrayList<DisneyCharactersData> = arrayListOf()
)