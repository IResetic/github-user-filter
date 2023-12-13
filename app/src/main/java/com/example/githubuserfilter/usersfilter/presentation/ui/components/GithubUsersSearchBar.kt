package com.example.githubuserfilter.usersfilter.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubuserfilter.core.constatns.mediumPadding
import com.example.githubuserfilter.usersfilter.presentation.ui.GithubUsersScreenEvent.FilterUsers
import com.example.githubuserfilter.usersfilter.presentation.ui.GithubUsersScreenViewModel

@ExperimentalMaterial3Api
@Composable
fun GithubUsersSearchBar() {
    val viewModel = hiltViewModel<GithubUsersScreenViewModel>()
    val githubUsersScreenState = viewModel.githubUsersState.collectAsState()

    var isFilterFocused by remember { mutableStateOf(false) }

    TextField(
        value = githubUsersScreenState.value.filterKeyword,
        onValueChange = {
            viewModel.onEvent(FilterUsers(it))
        },
        placeholder = {
            Text(
                text = "Enter keyword",
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                fontStyle = FontStyle.Italic
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding)
            .onFocusChanged { isFilterFocused = it.isFocused },
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { viewModel.onEvent(FilterUsers("")) }) {
                if (isFilterFocused && githubUsersScreenState.value.filterKeyword.length > 1) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
                }
            }
        },
        shape = SearchBarDefaults.inputFieldShape,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}
