package com.ad_coding.mvvmcourse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ad_coding.mvvmcourse.ui.screen.champion_list.ChampionListScreen
import com.ad_coding.mvvmcourse.ui.screen.champion_list.ChampionListViewModel
import com.ad_coding.mvvmcourse.ui.theme.MvvmCourseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MvvmCourseTheme {
                val viewModel = hiltViewModel<ChampionListViewModel>()

                val state by viewModel.state.collectAsStateWithLifecycle()
                ChampionListScreen(
                    state = state,
                    onValueChange = viewModel::onSearchTextChange
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MvvmCourseTheme {
        Greeting("Android")
    }
}