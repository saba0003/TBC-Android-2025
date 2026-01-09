package com.example.tbc_android_2025.presentation.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log.d
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationHelper @Inject constructor(@param:ApplicationContext private val context: Context) {

    init {
        createNotificationChannels()
    }


    fun showRegisterSuccess() = showNotification(
        title = MSG_REGISTER_TITLE,
        message = MSG_REGISTER_BODY,
        deepLink = DEEP_LINK_WELCOME
    )

    fun showLoginSuccess() = showNotification(
        title = MSG_LOGIN_TITLE,
        message = MSG_LOGIN_BODY,
        deepLink = DEEP_LINK_DASHBOARD
    )

    fun showNotification(title: String, message: String, deepLink: String) {
        d("NOTIFICATION_DEBUG", "Attempting to show notification: $title")
        val intent = Intent(Intent.ACTION_VIEW, deepLink.toUri())
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_AUTH_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Pops up on screen
            .setContentIntent(pendingIntent)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(System.currentTimeMillis().toInt(), notification)
    }


    /** AUX */
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val authChannel = NotificationChannel(
                CHANNEL_AUTH_ID,
                CHANNEL_AUTH_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = CHANNEL_AUTH_DESCRIPTION }

            val promoChannel = NotificationChannel(
                CHANNEL_PROMO_ID,
                CHANNEL_PROMO_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = CHANNEL_PROMO_DESCRIPTION }

            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(authChannel)
            manager.createNotificationChannel(promoChannel)
        }
    }

    private companion object {
        const val CHANNEL_AUTH_ID = "auth_notifications"
        const val CHANNEL_AUTH_NAME = "Authentication Updates"
        const val CHANNEL_AUTH_DESCRIPTION = "Notifications for Login and Register"
        const val CHANNEL_PROMO_ID = "promo_notifications"
        const val CHANNEL_PROMO_NAME = "General Updates"
        const val CHANNEL_PROMO_DESCRIPTION = "General app updates"
        const val DEEP_LINK_WELCOME = "myapp://welcome"
        const val DEEP_LINK_DASHBOARD = "myapp://dashboard"
        const val MSG_REGISTER_TITLE = "Welcome!"
        const val MSG_REGISTER_BODY = "Registration successful. Tap to explore."
        const val MSG_LOGIN_TITLE = "Success"
        const val MSG_LOGIN_BODY = "You have logged in successfully."
    }
}
