package by.itacademy.palina.task.home.hw5

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Button
import by.itacademy.palina.task.R


class HW5 : Activity() {
    private lateinit var btn: Button
    private lateinit var clock: ClockView

    private val innerReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            var state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)
            if (state == WifiManager.WIFI_STATE_ENABLED) {
                btn.setText(R.string.hw5wifiEnabled)
                btn.setBackgroundResource(R.drawable.green_chrome_button)
            } else if (state == WifiManager.WIFI_STATE_DISABLED) {
                btn.setText(R.string.hw5wifiDisabled)
                btn.setBackgroundResource(R.drawable.red_chrome)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hw5)

        clock = findViewById(R.id.hw5clock)
        clock.setAutoUpdate(true)

        btn = findViewById(R.id.hw5btn)
    }

    override fun onResume() {
        super.onResume()
        clock.setAutoUpdate(true)
        val intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(innerReceiver, intentFilter)
    }


    override fun onPause() {
        super.onPause()
        clock.setAutoUpdate(false)
        unregisterReceiver(innerReceiver)
    }
}