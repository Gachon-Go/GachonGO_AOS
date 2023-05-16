package com.example.gachongo.util.extension

import android.app.Activity
import android.view.View
import androidx.fragment.app.FragmentActivity

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun FragmentActivity.backPressed() {
    this.onBackPressedDispatcher.onBackPressed()
}