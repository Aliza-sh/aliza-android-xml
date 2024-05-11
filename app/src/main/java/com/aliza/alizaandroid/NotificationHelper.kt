package com.aliza.alizaandroid

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class NotificationHelper(private val context: Context) : AppCompatActivity() {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    class NotificationPermissionManager(
        private val context: Context,
        private val activity: Activity
    ) {
        fun requestNotificationPermission(onPermissionGranted: () -> Unit) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        1
                    )
                } else {
                    onPermissionGranted()
                }
            } else {
                //Handle notification permission request for pre-Android 13 versions (if needed)
            }
        }
    }


    inner class ChannelBuilder {
        private var _channelId: String? = null
        private var _channelName: String? = null
        private var _channelImportance: Int = NotificationManager.IMPORTANCE_DEFAULT
        private var _description: String? = null

        fun setChannelId(channelId: String): ChannelBuilder {
            this._channelId = channelId
            return this
        }

        fun setChannelName(channelName: String): ChannelBuilder {
            this._channelName = channelName
            return this
        }

        fun setChannelImportance(channelImportance: Int): ChannelBuilder {
            this._channelImportance = channelImportance
            return this
        }
        fun setChannelDescription(description: String): ChannelBuilder {
            this._description = description
            return this
        }

        fun build() {
            // Ensure both channelId and channelName are set before building
            requireNotNull(_channelId) { "Channel ID cannot be null!" }
            requireNotNull(_channelName) { "Channel name cannot be null!" }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && _channelId != null) {
                val channel = NotificationChannel(_channelId!!, _channelName!!, _channelImportance)
                channel.description = _description
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    inner class NotificationBuilder {
        private var _title: String? = null
        private var _contentText: String? = null
        private var _smallIcon: Int? = null
        private var _largeIcon: Bitmap? = null
        private var _notificationId: Int = 0
        private var _contentIntent: PendingIntent? = null
        private var _autoCancel: Boolean = true
        private var _channelId: String? = null
        private var _style: NotificationCompat.Style? = null

        fun setContentTitle(title: String): NotificationBuilder {
            this._title = title
            return this
        }

        fun setContentText(contentText: String): NotificationBuilder {
            this._contentText = contentText
            return this
        }

        fun setSmallIcon(smallIcon: Int): NotificationBuilder {
            this._smallIcon = smallIcon
            return this
        }

        fun setLargeIcon(largeIcon: Bitmap): NotificationBuilder {
            this._largeIcon = largeIcon
            return this
        }

        fun setContentIntent(contentIntent: PendingIntent): NotificationBuilder {
            this._contentIntent = contentIntent
            return this
        }

        fun setChannel(channelId: String): NotificationBuilder {
            this._channelId = channelId
            return this
        }

        fun setNotificationId(notificationId: Int): NotificationBuilder {
            this._notificationId = notificationId
            return this
        }
        fun setStyle(style: NotificationCompat.Style): NotificationBuilder {
            this._style = style
            return this
        }

        fun buildAndShow() {
            val notificationBuilder = NotificationCompat.Builder(context, _channelId!!)
                .setContentTitle(_title)
                .setContentText(_contentText)
                .setSmallIcon(_smallIcon!!)
                .setLargeIcon(_largeIcon)
                .setContentIntent(_contentIntent)
                .setAutoCancel(_autoCancel)
                .setStyle(_style)
            // Add other notification settings as needed

            val notification = notificationBuilder.build()
            notificationManager.notify(_notificationId, notification)
        }
    }

    class PendingIntentBuilder(private val context: Context) {

        private var _activity: Class<*>? = null
        private var _requestCode: Int = 0
        private var _flags: Int = 0
        private var _kayPutExtra: String? = null
        private var _dataPutExtra: String? = null

        fun setActivity(activity: Class<*>): PendingIntentBuilder {
            this._activity = activity
            return this
        }

        fun setRequestCode(requestCode: Int): PendingIntentBuilder {
            this._requestCode = requestCode
            return this
        }

        fun setFlags(flags: Int): PendingIntentBuilder {
            this._flags = flags
            return this
        }

        fun putExtraString(kayPutExtra: String, dataPutExtra: String): PendingIntentBuilder {
            this._kayPutExtra = kayPutExtra
            this._dataPutExtra = dataPutExtra
            return this
        }

        fun buildForActivity(): PendingIntent? {
            if (_activity == null) {
                return null
            }
            val intent = Intent(context, _activity)
            intent.putExtra(_kayPutExtra,_dataPutExtra)
            intent.flags = this._flags
            return PendingIntent.getActivity(context, _requestCode, intent, PendingIntent.FLAG_IMMUTABLE)
        }
    }

    fun createChannel(channelId: String, channelName: String) {
        ChannelBuilder()
            .setChannelId(channelId)
            .setChannelName(channelName)
            .build()
    }

    fun buildNotification(): NotificationBuilder {
        return NotificationBuilder()
    }

    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel("myNotif", "Football", NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.description = "this channel shows notifications about football"
        notificationManager.createNotificationChannel(notificationChannel)
    }*/
    /*val intent = Intent(this , MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    val pendingIntent = PendingIntent.getActivity(this , 12 , intent ,
        PendingIntent.FLAG_IMMUTABLE)*/
    /*val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    val notification = NotificationCompat
        .Builder( this , "myNotif" )
        .setSmallIcon( android.R.drawable.stat_notify_chat )
        .setLargeIcon( BitmapFactory.decodeResource(resources , R.drawable.notif) )
        .setContentTitle("Sam Jefry")
        .setContentText("Let's have a dinner...")
        .setContentIntent(pendingIntent)
        .setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(
                    BitmapFactory.decodeResource(resources , R.drawable.big_pic)
                )
        )
        .build()
    notificationManager.notify( (10..1000).random() , notification )*/

}

