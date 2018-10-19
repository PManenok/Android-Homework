package by.itacademy.palina.task.home.hw6

import android.app.Activity
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager
import android.widget.Button
import by.itacademy.palina.task.R

class HW6 : Activity() {
    private lateinit var wifiService: HW6WiFiService
    private lateinit var btn: Button
    private lateinit var manager: LocalBroadcastManager

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder) {
            val binder: HW6WiFiService.WifiBinder = service as HW6WiFiService.WifiBinder
            wifiService = binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val state = intent.getStringExtra("WIFI_STATE")
            when (state) {
                "ENABLED" -> {
                    btn.setText(R.string.hw6_enabled)
                    btn.setBackgroundResource(R.drawable.green_chrome_button)
                }
                "DISABLED" -> {
                    btn.setText(R.string.hw6_disabled)
                    btn.setBackgroundResource(R.drawable.red_chrome_button)
                }
                else -> {
                    btn.setText(R.string.hw6_unknown)
                    btn.setBackgroundResource(R.drawable.yellow_chrome_button)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hw6)
        btn = findViewById(R.id.hw6btn)
        btn.setOnClickListener {
            wifiService.changeWifi()
        }
        manager = LocalBroadcastManager.getInstance(this)
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, HW6WiFiService::class.java)
        manager.registerReceiver(receiver, IntentFilter("custom-event-name"))
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
        manager.unregisterReceiver(receiver)
    }
}