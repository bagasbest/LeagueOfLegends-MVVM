package com.ad_coding.mvvmcourse.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionResponseModel(

	@SerialName("data")
	val champion: Map<String, ChampionModel> = emptyMap(),

	@SerialName("format")
	val format: String,

	@SerialName("type")
	val type: String,

	@SerialName("version")
	val version: String
)

@Serializable
data class Image(

	@SerialName("full")
	val full: String,

	@SerialName("group")
	val group: String
)

@Serializable
data class Info(

	@SerialName("magic")
	val magic: Int,

	@SerialName("difficulty")
	val difficulty: Int,

	@SerialName("defense")
	val defense: Int,

	@SerialName("attack")
	val attack: Int
)

@Serializable
data class Stats(
	@SerialName("mpregen")
	val mpregen: Double,

	@SerialName("attackdamageperlevel")
	val attackdamageperlevel: Double,

	@SerialName("mp")
	val mp: Double,

	@SerialName("attackrange")
	val attackrange: Double,

	@SerialName("hpperlevel")
	val hpperlevel: Double,

	@SerialName("hp")
	val hp: Double,

	@SerialName("hpregen")
	val hpregen: Double,

	@SerialName("mpregenperlevel")
	val mpregenperlevel: Double,

	@SerialName("spellblock")
	val spellblock: Double,

	@SerialName("critperlevel")
	val critperlevel: Double,

	@SerialName("movespeed")
	val movespeed: Double,

	@SerialName("mpperlevel")
	val mpperlevel: Double,

	@SerialName("armor")
	val armor: Double,

	@SerialName("armorperlevel")
	val armorperlevel: Double,

	@SerialName("crit")
	val crit: Double,

	@SerialName("attackdamage")
	val attackdamage: Double,

	@SerialName("attackspeed")
	val attackspeed: Double,

	@SerialName("spellblockperlevel")
	val spellblockperlevel: Double,

	@SerialName("attackspeedperlevel")
	val attackspeedperlevel: Double,

	@SerialName("hpregenperlevel")
	val hpregenperlevel: Double
)


@Serializable
data class ChampionModel(

	@SerialName("name")
	val name: String,

	@SerialName("title")
	val title: String,

	@SerialName("blurb")
	val blurb: String,

	@SerialName("tags")
	val tags: List<String>
)

fun Map<String, ChampionModel>.toChampionList(): List<ChampionModel> =
	this.values.toList()