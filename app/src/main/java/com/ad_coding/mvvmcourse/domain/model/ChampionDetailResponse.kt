package com.ad_coding.mvvmcourse.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ChampionDetailResponse(
    // This 'data' property is a map where the key is the champion's name
    // and the value is the Champion object itself. This is the key fix.
    val type: String,
    val data: Map<String, ChampionModel>
)