package by.itacademy.palina.task.home.hw7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import by.itacademy.palina.task.R

class HW7 : AppCompatActivity() {
    private var isTablet = false
    private var isLand = false
    private val fragmentManager = supportFragmentManager
    private lateinit var manager: LocalBroadcastManager
    private lateinit var searchUser: EditText

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val status = intent.getStringExtra("STATUS_EXTRA")
            val id = intent.getLongExtra("USER_ID", 0)
            if (isTablet || isLand) {
                Log.e("aaa", "Tab or Land")
                tabletChangeFragment(status, id)
            } else {
                Log.e("aaa", "Phone")
                phoneChangeFragment(status, id)
            }
        }
    }

    fun tabletChangeFragment(status: String, id: Long) {
        val transactionFragment = fragmentManager.beginTransaction()
        if (status == "EDITED") {
            UserDataEditFragment.USER_ID = id
            transactionFragment.replace(R.id.hw7_container_detail, UserDataEditFragment.getInstance(), "EDIT_FRAGMENT")
        } else {
            val frag = fragmentManager.findFragmentByTag("LIST_FRAGMENT")
            transactionFragment.detach(frag)
            transactionFragment.detach(fragmentManager.findFragmentByTag("EDIT_FRAGMENT"))
            transactionFragment.attach(frag)
        }
        transactionFragment.commit()
    }

    fun phoneChangeFragment(status: String, id: Long) {
        val transactionFragment = fragmentManager.beginTransaction()
        if (status == "EDITED") {
            UserDataEditFragment.USER_ID = id
            transactionFragment.replace(R.id.hw7_container, UserDataEditFragment.getInstance(), "EDIT_FRAGMENT")
            transactionFragment.addToBackStack(null)
            transactionFragment.commit()
        } else {
            val frag = fragmentManager.findFragmentByTag("EDIT_FRAGMENT")
            transactionFragment.detach(frag)
            fragmentManager.popBackStack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hw7)
        isLand = (findViewById<LinearLayout>(R.id.hw7_main_layout).getTag().toString() == "phone_land")
        isTablet = (findViewById<LinearLayout>(R.id.hw7_main_layout).getTag().toString() == "tablet")
        setListFragment()
        manager = LocalBroadcastManager.getInstance(this)
        searchUser = findViewById(R.id.hw7_search_edit)
        searchUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val sendIntent = Intent("SEARCH_TEXT_CHANGED")
                sendIntent.putExtra("SEARCH_STRING", s.toString())
                manager.sendBroadcast(sendIntent)
            }
        })

    }


    private fun setListFragment() {
        val transactionFragment = fragmentManager.beginTransaction()
        if (isTablet || isLand)
            transactionFragment.replace(R.id.hw7_container_list, UserDataListFragment.getInstance(), "LIST_FRAGMENT")
        else
            transactionFragment.replace(R.id.hw7_container, UserDataListFragment.getInstance(), "LIST_FRAGMENT")
        transactionFragment.commit()
    }

    override fun onResume() {
        super.onResume()
        manager.registerReceiver(receiver, IntentFilter("USER_DATA_CHANGED"))
    }

    override fun onPause() {
        super.onPause()
        manager.unregisterReceiver(receiver)
    }
}