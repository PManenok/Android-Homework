package by.itacademy.palina.task.classwork.cw3

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import by.itacademy.palina.task.R

class ItemsListAdapter(val onItemClick: (MyItem) -> Unit) : RecyclerView.Adapter<ItemsListAdapter.Holder>() {
    //var onItemClick: OnItemClick? = null

    var listData: List<MyItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        Log.e("aaa", "onCreateViewHolder")
        var view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list, viewGroup, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Log.e("aaa", "onBindViewHolder")
        val item = listData[position]
        holder.nameTextView.setText(item.name)
        holder.surnameTextView.setText(item.surname)

        holder.itemView.setOnClickListener {
            //onItemClick?.onItemClick(item) //first
            onItemClick(item) //second
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int = listData.size

    inner class Holder : RecyclerView.ViewHolder {
        var nameTextView: TextView
        var surnameTextView: TextView

        constructor(view: View) : super(view) {
            nameTextView = view.findViewById(R.id.itemTextView1)
            surnameTextView = view.findViewById(R.id.itemTextView2)
        }
    }

    interface OnItemClick {
        fun onItemClick(item: MyItem) {

        }
    }
}