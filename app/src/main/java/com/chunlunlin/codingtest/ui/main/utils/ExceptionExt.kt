package com.chunlunlin.codingtest.ui.main.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chunlunlin.codingtest.R
import com.chunlunlin.codingtext.domain.use_case.UseCaseException

@Composable
fun UseCaseException.toErrorMessage(): String {
    return when (this) {
        UseCaseException.Network -> stringResource(R.string.error_network_connection)
        UseCaseException.Unauthorized -> stringResource(R.string.error_network_unauthorized)
        UseCaseException.NotFound -> stringResource(R.string.error_network_not_found)
        UseCaseException.Timeout -> stringResource(R.string.error_network_timeout)
        is UseCaseException.Unknown -> {
            val message = this.errorMessage
            if (message.isNullOrEmpty()) stringResource(R.string.error_common)
            else stringResource(
                R.string.error_network_unknown,
                message
            )
        }

    }
}