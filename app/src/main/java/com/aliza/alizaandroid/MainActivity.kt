package com.aliza.alizaandroid

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.base.INTENT_NOTIFICATION
import com.aliza.alizaandroid.base.SIMPLE_NOTIFICATION
import com.aliza.alizaandroid.base.STYLE_NOTIFICATION
import com.aliza.alizaandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSimpleNotification.setOnClickListener {
            NotificationHelper
                .NotificationPermissionManager(applicationContext, this)
                .requestNotificationPermission {
                    simpleNotification()
                }
        }

        binding.btnBigPictureStyleNotification.setOnClickListener {
            NotificationHelper
                .NotificationPermissionManager(applicationContext, this)
                .requestNotificationPermission {
                    bigPictureStyleNotification()
                }
        }

        binding.btnBigTextStyleNotification.setOnClickListener {
            NotificationHelper
                .NotificationPermissionManager(applicationContext, this)
                .requestNotificationPermission {
                    bigTextStyleNotification()
                }
        }

        binding.btnInboxStyleNotification.setOnClickListener {
            NotificationHelper
                .NotificationPermissionManager(applicationContext, this)
                .requestNotificationPermission {
                    inboxStyleNotification()
                }
        }

    }

    private fun simpleNotification() {

        val pendingIntent = NotificationHelper.PendingIntentBuilder(this)
            .setActivity(SecondActivity::class.java)
            .setRequestCode(0)
            .putExtraString(INTENT_NOTIFICATION,"Simple Style")
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK))
            .buildForActivity()

        NotificationHelper(this)
            .NotificationBuilder()
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.img_notif))
            .setContentTitle("Sam Jefry")
            .setContentText("Let's have a dinner...")
            .setContentIntent(pendingIntent!!)
            .setChannel(SIMPLE_NOTIFICATION)
            .setNotificationId(0)
            .buildAndShow()
    }

    private fun bigPictureStyleNotification() {
        val pendingIntent = NotificationHelper.PendingIntentBuilder(this)
            .setActivity(SecondActivity::class.java)
            .setRequestCode(1)
            .putExtraString(INTENT_NOTIFICATION,"bigPicture Style")
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK))
            .buildForActivity()

        NotificationHelper(this)
            .NotificationBuilder()
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.img_notif))
            .setContentTitle("Sam Jefry")
            .setContentText("Let's have a dinner...")
            .setContentIntent(pendingIntent!!)
            .setChannel(STYLE_NOTIFICATION)
            .setNotificationId(1)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(
                        BitmapFactory.decodeResource(resources , R.drawable.img_big)
                    )
            )
            .buildAndShow()
    }

    private fun bigTextStyleNotification() {
        val pendingIntent = NotificationHelper.PendingIntentBuilder(this)
            .setActivity(SecondActivity::class.java)
            .setRequestCode(2)
            .putExtraString(INTENT_NOTIFICATION,"bigText Style")
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK))
            .buildForActivity()

        NotificationHelper(this)
            .NotificationBuilder()
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.img_notif))
            .setContentTitle("Sam Jefry")

            .setContentText("Let's have a dinner...")
            .setContentIntent(pendingIntent!!)
            .setChannel(STYLE_NOTIFICATION)
            .setNotificationId(2)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText( "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available. It is also used to temporarily replace text in a process called greeking, which allows designers to consider the form of a webpage or publication, without the meaning of the text influencing the design" )
            )
            .buildAndShow()
    }

    private fun inboxStyleNotification() {
        val pendingIntent = NotificationHelper.PendingIntentBuilder(this)
            .setActivity(SecondActivity::class.java)
            .setRequestCode(3)
            .putExtraString(INTENT_NOTIFICATION,"inbox Style")
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK))
            .buildForActivity()

        NotificationHelper(this)
            .NotificationBuilder()
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.img_notif))
            .setContentTitle("Sam Jefry")
            .setContentText("Let's have a dinner...")
            .setContentIntent(pendingIntent!!)
            .setChannel(STYLE_NOTIFICATION)
            .setNotificationId(3)
            .setStyle(
                NotificationCompat.InboxStyle()
                    .addLine("salam!")
                    .addLine("khoobi?!")
                    .addLine("seen nemikoni na ??????")
                    .addLine("bye for ever :))))")
            )
            .buildAndShow()
    }

}