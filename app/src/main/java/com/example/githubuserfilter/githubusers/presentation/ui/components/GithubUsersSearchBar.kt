package com.example.githubuserfilter.githubusers.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBarDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubuserfilter.core.constatns.mediumPadding
import com.example.githubuserfilter.githubusers.presentation.ui.GithubUsersScreenEvent.FilterUsers
import com.example.githubuserfilter.githubusers.presentation.ui.GithubUsersScreenViewModel

@ExperimentalMaterial3Api
@Composable
fun GithubUsersSearchBar() {
    val viewModel = hiltViewModel<GithubUsersScreenViewModel>()
    // val filterKeyword = viewModel.filterKeyword.collectAsState()
    val githubUsersScreenState = viewModel.githubUsersState.collectAsState()

    var isFilterFocused by remember { mutableStateOf(false) }

    TextField(
        value = githubUsersScreenState.value.filterKeyword2,
        onValueChange = {
            viewModel.onEvent(FilterUsers(it))
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding)
            .onFocusChanged { isFilterFocused = it.isFocused },
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { viewModel.onEvent(FilterUsers("")) }) {
                if(isFilterFocused && githubUsersScreenState.value.filterKeyword2.length > 1) {
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

    /*

                if (usernameFocused && username.length > 1) {
                IconButton(onClick = { updateUsername("") }) {
                    AppIcon(imageVector = Icons.Outlined.Cancel)
                }
            }

        OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = smallPadding,
                vertical = mediumPadding
            ),
        value = githubUsersScreenState.value.filterKeyword2,
        onValueChange = {
            viewModel.onEvent(FilterUsers(it))
        },
        singleLine = true,
        shape = SearchBarDefaults.inputFieldShape,

    )

        BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = smallPadding,
                vertical = mediumPadding
            ),
        value = githubUsersScreenState.value.filterKeyword2,
        onValueChange = {
            viewModel.onEvent(FilterUsers(it))
        },
        singleLine = true,
        ) {
    }

        TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = smallPadding, vertical = mediumPadding),
        value = githubUsersScreenState.value.filterKeyword2,
        onValueChange = {
            viewModel.onEvent(FilterUsers(it))
        },
        shape = SearchBarDefaults.inputFieldShape,
        singleLine = true,

    )
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = smallPadding, vertical = mediumPadding),
        query = githubUsersScreenState.value.filterKeyword2,
        // query = filterKeyword.value,
        onQueryChange ={
            // viewModel.onEvent(UpdateSearchKeyword(it))
            viewModel.onEvent(FilterUsers(it))
        } ,
        onSearch = {
            focusManager.clearFocus()
            //viewModel.onEvent(FilterUsers(it))
        },
        active = false,
        onActiveChange = {}
    ) {

    }
    */
}