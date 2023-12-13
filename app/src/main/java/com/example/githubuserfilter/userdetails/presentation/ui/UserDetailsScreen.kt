package com.example.githubuserfilter.userdetails.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubuserfilter.R
import com.example.githubuserfilter.core.constatns.largerPadding
import com.example.githubuserfilter.core.constatns.mediumPadding
import com.example.githubuserfilter.core.constatns.smallPadding
import com.example.githubuserfilter.userdetails.presentation.model.UserDetailsScreenUiState
import com.example.githubuserfilter.userdetails.presentation.ui.UserDetailsScreenEvent.FetchUserDetails
import com.example.githubuserfilter.userdetails.presentation.ui.components.UserImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    navigateBack: () -> Unit
) {

    val viewModel = hiltViewModel<UserDetailsScreenViewModel>()
    val userDetailsState by viewModel.userDetailsState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(viewModel.username ?: "") },
                navigationIcon = { IconButton(onClick = { navigateBack() }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        stringResource(id = R.string.content_description_toolbar_back_arrow)
                    )
                }}
            )
        }
    ) { paddingValues ->
        when(userDetailsState.uiState) {
            is UserDetailsScreenUiState.GetUserData -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) { CircularProgressIndicator() }
            }

            is UserDetailsScreenUiState.Error -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(horizontal = largerPadding * 2)
                ) {
                    Text(
                        text = stringResource(id = R.string.user_details_error_text),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            lineHeight = 28.sp

                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(smallPadding))

                    Button(onClick = { viewModel.onEvent(FetchUserDetails) }) {
                        Text(stringResource(id = R.string.user_details_try_again_button_text))
                    }
                }
            }

            else -> {
                Box(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(mediumPadding))

                        UserImage(
                            avatarUrl = userDetailsState.userDetails?.avatarUrl ?: ""
                        )

                        Spacer(modifier = Modifier.height(mediumPadding))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(mediumPadding)
                        ) {
                            Text(text = "${stringResource(id = R.string.user_details_name_label)}:  ")
                            Text(text = userDetailsState.userDetails?.name ?: "")
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(mediumPadding)
                        ) {
                            Text(text = "${stringResource(id = R.string.user_details_email_label)}:  ")
                            Text(text = userDetailsState.userDetails?.email ?: "")
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(mediumPadding)
                        ) {
                            Text(text = "${stringResource(id = R.string.user_details_number_of_followers_label)}:  ")
                            Text(text = userDetailsState.userDetails?.followers.toString() ?: "")
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(mediumPadding)
                        ) {
                            Text(text = "${stringResource(id = R.string.user_details_number_of_repos_label)}:  ")
                            Text(text = userDetailsState.userDetails?.publicRepos.toString() ?: "")
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(mediumPadding)
                        ) {
                            Text(text = "${stringResource(id = R.string.user_details_bio_label)}:  ")
                            Text(text = userDetailsState.userDetails?.bio ?: "")
                        }

                    }
                }
            }
        }
    }
}
