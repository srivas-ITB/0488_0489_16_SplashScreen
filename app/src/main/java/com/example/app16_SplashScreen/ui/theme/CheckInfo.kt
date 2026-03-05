package com.example.app16_SplashScreen.ui.theme

data class CheckInfo(
    val title: String,
    var checked: Boolean = false,
    var onCheckedChange: (Boolean) -> Unit
)
