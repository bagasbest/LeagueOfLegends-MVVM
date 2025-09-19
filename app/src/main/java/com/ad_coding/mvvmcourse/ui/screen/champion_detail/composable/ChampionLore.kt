package com.ad_coding.mvvmcourse.ui.screen.champion_detail.composable

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChampionLore(
    lore: String,
    modifier: Modifier = Modifier,
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .clickable {
                isExpanded = !isExpanded
            }
    ) {
        Text(
            text = "Lore",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = lore,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = if (!isExpanded) 3 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 22.sp,
            modifier = Modifier.animateContentSize()
        )

        Text(
            text = if (!isExpanded) "Show more" else "Show less",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}