package com.ad_coding.mvvmcourse.ui.screen.champion_detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ad_coding.mvvmcourse.data.repository.ApiRepositoryImpl
import com.ad_coding.mvvmcourse.domain.model.ChampionModel
import com.ad_coding.mvvmcourse.ui.screen.champion_detail.composable.ChampionHeader
import com.ad_coding.mvvmcourse.ui.screen.champion_detail.composable.ChampionLore

@Composable
fun ChampionDetailScreen(
    champion: ChampionModel
) {
    Scaffold { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            item {
                AsyncImage(
                    model = ApiRepositoryImpl.imageSplashUrl + "${champion.name}_0.jpg",
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                ChampionHeader(
                    champion = champion,
                    modifier = Modifier
                        .padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                )

                ChampionLore(
                    lore = champion.blurb,
                    modifier = Modifier
                        .padding(
                            horizontal = 20.dp,
                            vertical = 6.dp
                        )
                )
            }
        }
    }
}