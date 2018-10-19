package by.itacademy.palina.task.home.hw7

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import by.itacademy.palina.task.R
import by.itacademy.palina.task.home.hw3.PicassoCircleTransformation
import com.squareup.picasso.Picasso
import java.util.*

class UserDataListAdapter(val onItemClick: (UserData) -> Unit) : RecyclerView.Adapter<UserDataListAdapter.Holder>() {
    var isSearching = false
    var listKey: MutableList<Long> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var mapData: MutableMap<Long, UserData> = mutableMapOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var sortedList: MutableList<UserData> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.hw7_user_data_list, viewGroup, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var item = mapData[listKey[position]]
        if (!isSearching)
            item = mapData[listKey[position]]
        else
            if (sortedList.size > position)
                item = sortedList[position]
        if (item!!.picLink == "" || item.picLink.isEmpty()) {
            Picasso.get().load(R.drawable.default_user).transform(PicassoCircleTransformation()).into(holder.userPic)
        } else {
            Picasso.get().load(item.picLink).transform(PicassoCircleTransformation()).into(holder.userPic)
        }
        holder.nameTextView.text = item.name
        holder.surnameTextView.text = item.surname

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

    }

    public fun sortByString(str: String) {
        if (str == "") {
            isSearching = false
        } else {
            isSearching = true
            sortedList = mutableListOf()
            for (item in mapData) {
                if (item.value.name.contains(str) || item.value.surname.contains(str)) {
                    sortedList.add(item.value)
                }
            }
            Collections.sort(sortedList, object : Comparator<UserData> {
                override fun compare(first: UserData, second: UserData): Int {
                    if (first.name.compareTo(second.name) != 0) return first.name.compareTo(second.name)
                    else return first.surname.compareTo(second.surname)
                }
            })
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int {
        if (isSearching) return sortedList.size
        else return mapData.size
    }

    inner class Holder : RecyclerView.ViewHolder {
        var nameTextView: TextView
        var surnameTextView: TextView
        var userPic: ImageView

        constructor(view: View) : super(view) {
            userPic = view.findViewById(R.id.hw7userPic)
            nameTextView = view.findViewById(R.id.hw7userName)
            surnameTextView = view.findViewById(R.id.hw7userSurname)
        }
    }
}