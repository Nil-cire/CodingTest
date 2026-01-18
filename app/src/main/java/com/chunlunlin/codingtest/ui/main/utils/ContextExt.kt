package com.chunlunlin.codingtest.ui.main.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri
import com.chunlunlin.codingtest.R

fun Context.openInBrowser(url: String?) {
    if (url.isNullOrBlank()) return
    try {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        this.startActivity(intent)
    } catch (_: Exception) {
        Toast.makeText(this, getString(R.string.open_link_fail), Toast.LENGTH_SHORT).show()
    }
}