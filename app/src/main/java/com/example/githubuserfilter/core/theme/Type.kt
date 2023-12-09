package com.example.githubuserfilter.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.githubuserfilter.core.theme.AppFonts.notoSansFont

val Typography = Typography(
    headlineSmall = TextStyle(
        fontFamily = notoSansFont,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp,
        lineHeight = 29.sp
    ),
    titleMedium = TextStyle(
        fontFamily = notoSansFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 12.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = notoSansFont,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = notoSansFont,
        fontWeight = FontWeight.Normal,
        fontSize = 9.6.sp,
        lineHeight = 9.6.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = notoSansFont,
        fontWeight = FontWeight.Light,
        fontSize = 15.sp,
        lineHeight = 17.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = notoSansFont,
        fontWeight = FontWeight.Normal,
        fontSize = 9.6.sp,
        lineHeight = 9.6.sp,
    )
)
