package com.example.tbc_android_2025.presentation.service

import android.util.Log.d
import com.example.tbc_android_2025.presentation.util.NotificationHelper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService @Inject constructor(private val notificationHelper: NotificationHelper) :
    FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title ?: DEFAULT_NOTIFICATION_TITLE
        val body = remoteMessage.notification?.body ?: DEFAULT_NOTIFICATION_BODY
        val link = remoteMessage.data[PAYLOAD_KEY_LINK] ?: DEFAULT_DEEP_LINK
        notificationHelper.showNotification(title = title, message = body, deepLink = link)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        d(FCM_LOGCAT_TAG, FCM_LOGCAT_TOKEN.plus(other = token))
    }

    private companion object {
        const val DEFAULT_NOTIFICATION_TITLE = "New Update"
        const val DEFAULT_NOTIFICATION_BODY = "Tap to see what's new!"
        const val PAYLOAD_KEY_LINK = "link"
        const val DEFAULT_DEEP_LINK = "myapp://dashboard"
        const val FCM_LOGCAT_TAG = "FIREBASE"
        const val FCM_LOGCAT_TOKEN = "Firebase Token: "
    }
}
