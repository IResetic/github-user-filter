package com.example.githubuserfilter.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.githubuserfilter.core.theme.GithubUserFilterTheme
import com.example.githubuserfilter.main.ui.GithubUserFilterContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            GithubUserFilterTheme {
                GithubUserFilterContent(navController = navController)
            }
        }
    }
}
