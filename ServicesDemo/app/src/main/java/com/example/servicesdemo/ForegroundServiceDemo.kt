package com.example.servicesdemo

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.servicesdemo.MainActivity.Companion.INPUT_EXTRA
import kotlinx.android.synthetic.main.activity_main.*



// Service is also a context
class ForegroundServiceDemo : Service() {


    // var notificationManager: NotificationManagerCompat? = null
    // to show our notifications - wraps around normal NM and has checks for backwards compatibility
    // Cannot create notification channels - so we need 2 diff NMs
    // But here we just use the startForeground method as Foreground services MUST have notifications

    // For bound services
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    // Called every time we call start service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(HI, "Hello from onStartCommand")
        val input = intent?.getStringExtra(INPUT_EXTRA)

        // Needed to make manual notifications in general
        // notificationManager = NotificationManagerCompat.from(this)

        // if we want to click our notification and go to our main activity
        val notificationIntent = Intent(this, MainActivity :: class.java)
        val pendingIntent = PendingIntent.getActivity(this,
             0,   // request code - later use to cancel this intent too
            notificationIntent,
            0 // flag to do after pendingIntent is updated
            )

        // Can be made in on create since when we may want to make this notification just once
        // Here it changes with user input
        val notification = NotificationCompat.Builder(this, App.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_android) // mandatory
            .setContentTitle("Foreground Service Demo")
            .setContentText(input)
            .setContentIntent(pendingIntent)
            .build()


        startForeground(1, notification) // to start notification, id is notifier

        //            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        //            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
        // for API level lower than oreo for higher these other methods are deprecated since they're defined in out App class
        // If we wanted to send a notification we do this usually, but in this case the service does that for us
        // notificationManager?.notify(1,notification ) // updates the notification of given id

        return START_NOT_STICKY

        /*
        * START_NOT_STICKY
        * If the system kills the service after onStartCommand() returns,
        * do not recreate the service unless there are pending intents to deliver.
        *  This is the safest option to avoid running your service when not necessary and
        * when your application can simply restart any unfinished jobs.
        * START_STICKY
        * If the system kills the service after onStartCommand() returns, recreate the service and
        * call onStartCommand(), but do not redeliver the last intent. Instead, the system calls
        * onStartCommand() with a null intent unless there are pending intents to start the service.
        *  In that case, those intents are delivered. This is suitable for media players
        * (or similar services) that are not executing commands but are running indefinitely
        * and waiting for a job.
        * START_REDELIVER_INTENT
        * If the system kills the service after onStartCommand() returns, recreate the service
        * and call onStartCommand() with the last intent that was delivered to the service.
        * Any pending intents are delivered in turn. This is suitable for services that are
        * actively performing a job that should be immediately resumed, such as downloading a file.
        * */


    }

    // Called when service is created
    override fun onCreate() {
        super.onCreate()
        Log.d(HI, "Hello from onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(HI, "Hello from onDestroy")
    }


    companion object {
        const val HI = "HI"
    }
}