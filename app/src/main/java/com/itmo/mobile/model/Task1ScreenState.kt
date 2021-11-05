package com.itmo.mobile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task1ScreenState(
    var isChecked: Boolean = false,
    var textEditText: String = "Enter text",
    var textLabelText: String,
    var isVisibleList: Boolean = true
) : Parcelable