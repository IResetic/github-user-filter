package com.example.githubuserfilter.core.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.githubuserfilter.R

object AppFonts {
    val notoSansFont = FontFamily(
        Font(R.font.noto_sans, weight = FontWeight.Light),
        Font(R.font.noto_sans, weight = FontWeight.Normal),
        Font(R.font.noto_sans, weight = FontWeight.Medium),
        Font(R.font.noto_sans_bold, weight = FontWeight.SemiBold),
        Font(R.font.noto_sans_bold, weight = FontWeight.Bold),
    )
}
