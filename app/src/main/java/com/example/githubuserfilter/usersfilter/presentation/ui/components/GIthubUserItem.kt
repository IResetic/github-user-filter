package com.example.githubuserfilter.usersfilter.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.githubuserfilter.R
import com.example.githubuserfilter.core.constatns.mediumPadding
import com.example.githubuserfilter.core.constatns.smallPadding
import com.example.githubuserfilter.usersfilter.domain.model.BasicUserInfo

@Composable
fun GithubUserItem(
    user: BasicUserInfo,
    navigateToUserDetails: (username: String) -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable { navigateToUserDetails(user.username) }
            .fillMaxWidth()
            .padding(mediumPadding),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            UserAvatar(
                avatarUrl = user.avatarUrl
            )
        }

        Column(
            modifier = Modifier.weight(3f)
        ) {
            Text(
                text = user.username,
                style = MaterialTheme.typography.bodyLarge
            )
            Row(modifier = Modifier.padding(top = smallPadding)) {
                Text(
                    text = stringResource(id = R.string.user_item_type_label) + ":  ",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic
                )

                Text(
                    text = user.type,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Text(
            modifier = Modifier.weight(1f),
            text = user.score.toString(),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End
        )
    }
}
