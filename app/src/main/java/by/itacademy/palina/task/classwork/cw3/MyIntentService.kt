package by.itacademy.palina.task.classwork.cw3

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService : IntentService("MyIntentService") {
    companion object {
        const val LINK_EXTRA = "Link_Extra"
    }

    override fun onHandleIntent(intent: Intent) {
        val link = intent.getStringExtra(LINK_EXTRA)
        Log.e("aaa", "link = $link start")
        Thread.sleep(3000)
        Log.e("aaa", "link = $link stop")
    }

}