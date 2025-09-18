package com.ad_coding.mvvmcourse.domain.model

data class ChampionListState(
    val searchText: String = "",
    val champions: List<ChampionModel> = emptyList(),
    val filterChampions: List<ChampionModel> = emptyList()
)