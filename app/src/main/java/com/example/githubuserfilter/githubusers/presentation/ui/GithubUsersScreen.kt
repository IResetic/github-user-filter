package com.example.githubuserfilter.githubusers.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubuserfilter.R
import com.example.githubuserfilter.core.constatns.largerPadding
import com.example.githubuserfilter.core.constatns.mediumPadding
import com.example.githubuserfilter.core.constatns.smallPadding
import com.example.githubuserfilter.githubusers.presentation.model.GithubUsersScreenUIState
import com.example.githubuserfilter.githubusers.presentation.ui.components.GithubUsersSearchBar

@ExperimentalMaterial3Api
@Composable
fun GithubUsersScreen() {

    val viewModel = hiltViewModel<GithubUsersScreenViewModel>()
    val githubUsersStateUi by viewModel.githubUsersState.collectAsState()
    val listState = rememberLazyListState()
    val context = LocalContext.current


    LaunchedEffect(listState.layoutInfo.visibleItemsInfo) {
        val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
        val totalNumbersItems = listState.layoutInfo.totalItemsCount

        if(lastVisibleItem != null && lastVisibleItem.index >= totalNumbersItems - 20) {
            viewModel.onEvent(GithubUsersScreenEvent.LoadMoreUsers)
        }
    }

    LaunchedEffect(githubUsersStateUi.errorMessage) {
        if (githubUsersStateUi.errorMessage.isNotBlank()) {
            Toast.makeText(context, githubUsersStateUi.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    val filterText = githubUsersStateUi.filterKeyword
    val emptyListText = stringResource(id = if (filterText.isEmpty()) {
        R.string.github_users_empty_filter_keyword
    } else {
        R.string.github_users_no_data_to_display
    })

    Scaffold(
        topBar = { GithubUsersSearchBar() }
    ) { paddingValues ->

        if (githubUsersStateUi.uiState == GithubUsersScreenUIState.StartFilteringUsers) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) { CircularProgressIndicator() }
        } else {
            if(githubUsersStateUi.filterUsers.isEmpty()) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(horizontal = largerPadding * 2)
                ) {
                    Text(
                        text = emptyListText,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            lineHeight = 28.sp

                        ),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = mediumPadding)
                ) {
                    LazyColumn(
                        state = listState
                    ) {
                        items(githubUsersStateUi.filterUsers) { user ->
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 20.dp),
                                text = user.username
                            )
                        }


                        item {
                            if(githubUsersStateUi.uiState == GithubUsersScreenUIState.LoadMoreUsers) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(smallPadding),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
