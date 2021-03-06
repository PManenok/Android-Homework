package by.itacademy.palina.task.classwork.cw3

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.content.SharedPreferences
import android.os.Bundle
import android.os.IBinder
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import by.itacademy.palina.task.R

class CW3 : Activity() {
    companion object {
        const val SHARED_PREFS_NAME = "awsxfgvbhj"
        const val HELLO_KEY = "asdfg"
    }

    //val service: MyService? = null
    private lateinit var progressBar: ProgressBar
    //private val adapter = ItemsListAdapter()
    private val adapter = ItemsListAdapter {
        Log.e("aaa", "student name = ${it.name}")
    }
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cw3)

        val recyclerView = findViewById<RecyclerView>(R.id.cw3recycler)
        progressBar = findViewById<ProgressBar>(R.id.cw3progress)

        sharedPrefs = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

        val listData = listOf<MyItem>(
                MyItem("12345", "67890"),
                MyItem("12s345", "6aerg90"),
                MyItem("123oij45", "678tyh"),
                MyItem("12we5", "6rth0"),
                MyItem("123a", "aer90"),
                MyItem("123wfewg", "67rth0"),
                MyItem("12jty", "6aerf90"),
                MyItem("12aer", "678tyj"),
                MyItem("12eg5", "6aw0"),
                MyItem("1aegr5", "67tyj0"),
                MyItem("12aerg5", "6WEF90"),
                MyItem("123rtjy5", "6uyk90"),
                MyItem("1zd5", "67ese0"),
                MyItem("1bn45", "6hh90"))

        adapter.listData = listData

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)//same width, height and etc.

        //adapter.onItemClick = object : ItemsListAdapter.OnItemClick{
        //    override fun onItemClick(item : MyItem){
        //        Log.e("aaa", "item name = ${item.name}")
        //    }
        //}
    }

    override fun onStart() {
        super.onStart()
        val text = sharedPrefs.getString(HELLO_KEY, "No data")
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        Log.e("aaa", "message = $text")
    }

    override fun onStop() {
        super.onStop()
        sharedPrefs.edit().putString(HELLO_KEY, "Hello").apply()
    }

    override fun onResume() {
        super.onResume()
        //val intent = Intent(this, MyService::class.java)
        //bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE )
        //startService(intent)
        // val intent1 = Intent(this, MyIntentService::class.java)
        //        intent1.putExtra(MyIntentService.LINK_EXTRA,  "url/1111")
        //        val intent2 = Intent(this, MyIntentService::class.java)
        //        intent2.putExtra(MyIntentService.LINK_EXTRA,  "url/2222")
        //        val intent3 = Intent(this, MyIntentService::class.java)
        //        intent3.putExtra(MyIntentService.LINK_EXTRA,  "url/3333")
        //        startService(intent1)
        //        startService(intent2)
        //        startService(intent3)
    }

    override fun onPause() {
        super.onPause()
        //stopService(Intent(this, MyService::class.java))
        //unbindService(serviceConnection)
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.e("aaa", "onServiceDisconnected")
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            Log.e("aaa", "onServiceConnected")
        }
    }
}