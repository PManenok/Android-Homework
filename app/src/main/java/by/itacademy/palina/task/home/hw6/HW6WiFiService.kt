package by.itacademy.palina.task.home.hw6

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Binder
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager

class HW6WiFiService : Service() {
    private val binder: IBinder = WifiBinder()
    private lateinit var localBroadcastManager: LocalBroadcastManager
    private lateinit var wifiManager: WifiManager

    inner class WifiBinder : Binder() {
        fun getService(): HW6WiFiService {
            return this@HW6WiFiService
        }
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)
            val sendingIntent = Intent("custom-event-name")
            when (state) {
                WifiManager.WIFI_STATE_ENABLED -> sendingIntent.putExtra("WIFI_STATE", "ENABLED")
                WifiManager.WIFI_STATE_DISABLED -> sendingIntent.putExtra("WIFI_STATE", "DISABLED")
                else -> sendingIntent.putExtra("WIFI_STATE", "UNKNOWN")
            }
            localBroadcastManager.sendBroadcast(sendingIntent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        localBroadcastManager = LocalBroadcastManager.getInstance(this)
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    override fun onBind(intent: Intent?): IBinder? {
        registerReceiver(receiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        unregisterReceiver(receiver)
        return super.onUnbind(intent)
    }

    fun changeWifi() {
        if (wifiManager.isWifiEnabled) {
            wifiManager.setWifiEnabled(false)
        } else {
            wifiManager.setWifiEnabled(true)
        }
    }
}