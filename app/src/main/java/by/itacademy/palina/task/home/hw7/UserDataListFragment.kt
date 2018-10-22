package by.itacademy.palina.task.home.hw7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.itacademy.palina.task.R

class UserDataListFragment : Fragment() {
    companion object {
        fun getInstance(): UserDataListFragment {
            return UserDataListFragment()
        }
    }

    private lateinit var manager: LocalBroadcastManager
    private lateinit var recyclerView: RecyclerView

    private val adapter = UserDataListAdapter {
        val sendIntent = Intent("USER_DATA_CHANGED")
        sendIntent.putExtra("STATUS_EXTRA", "EDITED")
        sendIntent.putExtra("USER_ID", it.userID)
        manager.sendBroadcast(sendIntent)
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            if (intent.action == "USER_DATA_CHANGED" && intent.getStringExtra("STATUS_EXTRA") == "DELETED") {
                val userID = intent.getLongExtra("USER_ID", 0)
                if (adapter.listKey.contains(userID)) {
                    adapter.listKey.remove(userID)
                }
            } else if (intent.action == "SEARCH_TEXT_CHANGED") {
                val str = intent.getStringExtra("SEARCH_STRING")
                adapter.sortByString(str)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_user_data_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.hw7recycler)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter.mapData = UserListObject.mapData
        adapter.listKey = UserListObject.mapData.keys.toMutableList()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)//same width, height and etc.

        manager = LocalBroadcastManager.getInstance(context!!)
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter("USER_DATA_CHANGED")
        intentFilter.addAction("SEARCH_TEXT_CHANGED")
        manager.registerReceiver(receiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        manager.unregisterReceiver(receiver)
    }
}