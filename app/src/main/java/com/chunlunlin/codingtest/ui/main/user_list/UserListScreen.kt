package com.chunlunlin.codingtest.ui.main.user_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.chunlunlin.codingtest.R
import com.chunlunlin.codingtest.ui.main.common.ErrorContent
import com.chunlunlin.codingtest.ui.main.core.UiState
import com.chunlunlin.codingtest.ui.main.utils.toErrorMessage
import com.chunlunlin.codingtext.domain.entity.GithubUserEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    onUserClick: (GithubUserEntity) -> Unit,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pullToRefreshState = rememberPullToRefreshState()

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.user_list_screen_app_title)) }) }
    ) { padding ->
        PullToRefreshBox(
            isRefreshing = uiState is UiState.Loading,
            onRefresh = { viewModel.fetchUsers() },
            state = pullToRefreshState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val state = uiState
            when (state) {
                UiState.Loading -> {
                    Text(
                        stringResource(R.string.loading),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is UiState.Error -> {
                    val errorMessage = state.exception.toErrorMessage()
                    ErrorContent(
                        message = errorMessage,
                        onRetry = viewModel::fetchUsers,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is UiState.Success -> {
                    val users = state.data
                    if (users.isEmpty()) {
                        Text(
                            stringResource(R.string.user_list_screen_no_user_hint),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        UserListSuccessContent(
                            users = users,
                            onUserClick = onUserClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UserListSuccessContent(
    users: List<GithubUserEntity>,
    onUserClick: (GithubUserEntity) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(
            items = users,
            key = { it.id }
        ) { user ->
            UserListScreenItem(user, onUserClick)
            HorizontalDivider()
        }
    }
}

@Composable
fun UserListScreenItem(
    user: GithubUserEntity,
    onUserClick: (GithubUserEntity) -> Unit
) {
    ListItem(
        modifier = Modifier.clickable { onUserClick(user) },
        leadingContent = {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = "avatar",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
        },
        headlineContent = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(user.login)
                if (user.isSiteAdmin) {
                    Spacer(Modifier.width(8.dp))
                    SuggestionChip(
                        onClick = {},
                        label = {
                            Text(
                                stringResource(R.string.user_list_screen_admin_user),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    )
                }
            }
        },
        supportingContent = { Text(user.type) },
        trailingContent = {
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null
            )
        }
    )
}