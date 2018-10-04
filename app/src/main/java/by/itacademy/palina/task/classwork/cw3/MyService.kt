package by.itacademy.palina.task.classwork.cw3

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        Log.e("aaa", "onBind")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("aaa", "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("aaa", "onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //intent...
        Log.e("aaa", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("aaa", "onUnbind")
        return super.onUnbind(intent)
    }

}