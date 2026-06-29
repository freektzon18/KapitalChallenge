package com.example.kapitalchallenge.architecture.data.disney

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "disney_characters")
data class DisneyCharactersData(
    @PrimaryKey
    @SerializedName("_id") var Id: Int,
    @SerializedName("films") var films: ArrayList<String> = arrayListOf(),
    @SerializedName("shortFilms") var shortFilms: ArrayList<String> = arrayListOf(),
    @SerializedName("tvShows") var tvShows: ArrayList<String> = arrayListOf(),
    @SerializedName("videoGames") var videoGames: ArrayList<String> = arrayListOf(),
    @SerializedName("parkAttractions") var parkAttractions: ArrayList<String> = arrayListOf(),
    @SerializedName("allies") var allies: ArrayList<String> = arrayListOf(),
    @SerializedName("enemies") var enemies: ArrayList<String> = arrayListOf(),
    @SerializedName("name") var name: String = "",
    @SerializedName("imageUrl") var imageUrl: String? = null,
    @SerializedName("url") var url: String? = null,
    var isFavorite: Boolean = false
)