package com.example.gachongo.api

import com.example.gachongo.data.NotificationBundle

interface NotificationView {
    fun onGetNotificationSuccess(successMessage: NotificationBundle)
    fun onGetNotificationFailure(messageDigest: String)
}
