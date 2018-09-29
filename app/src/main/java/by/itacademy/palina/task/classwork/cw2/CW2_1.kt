package by.itacademy.palina.task.classwork.cw2

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import by.itacademy.palina.task.R

class CW2_1 : Activity() {
    var test: Int = 0 //simple var
    val aaa: Int = 0 //val == final

    companion object {
        const val TAG = "asdfghj"

        @JvmStatic
        fun show(activity: Activity) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cw2_1)
        //test = 1
        //aaa = 1 cos final
        //CW2_1.TAG
        //CW2_1.show()//static in Java CW2_1.companion.show()
        //val mySingl = MySingl//Singleton
        //mySuperUtil()
        //savedInstanceState.toString()


    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(wifiReciever, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(wifiReciever)
    }

    private val wifiReciever = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            Log.e("AAA", "onReceive" + intent?.action)
        }
    }
}