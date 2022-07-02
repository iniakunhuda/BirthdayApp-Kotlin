package com.subdigistudio.birthdaycard.data

import androidx.annotation.DrawableRes
import java.util.*

data class Birthday(
    val id: Long,
    val name: String,
    val date: Date,
    @DrawableRes
    val image: Int?,
    val note: String?
)