package com.pemmob.gamecatalog.data.model

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<GameDto>
)
