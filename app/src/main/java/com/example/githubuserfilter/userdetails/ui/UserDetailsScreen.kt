package com.example.githubuserfilter.userdetails.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    navigateBack: () -> Unit
) {

    val viewModel = hiltViewModel<UserDetailsScreenViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(viewModel.userId.toString()) },
                navigationIcon = { IconButton(onClick = { navigateBack() }) {
                    Icon(Icons.Filled.ArrowBack, "Back")
                }}
            )
        }
    ) { paddingValue ->
        Box(
            modifier = Modifier.padding(paddingValue)
        ) {
            Text(text = "User details")
        }
    }
}