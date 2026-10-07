package com.pemmob.gamecatalog.data.model

import com.google.gson.annotations.SerializedName

data class GameDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("description_raw")
    val descriptionRaw: String?,
    @SerializedName("platforms")
    val platforms: List<PlatformWrapper>?,
    @SerializedName("genres")
    val genres: List<GenreDto>?
)

data class PlatformWrapper(
    @SerializedName("platform")
    val platform: PlatformDto
)

data class PlatformDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class GenreDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
