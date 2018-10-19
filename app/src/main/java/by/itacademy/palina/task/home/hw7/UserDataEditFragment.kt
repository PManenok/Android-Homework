package by.itacademy.palina.task.home.hw7

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import by.itacademy.palina.task.R
import by.itacademy.palina.task.home.hw3.PicassoCircleTransformation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserDataEditFragment : Fragment() {
    companion object {
        var USER_ID: Long = 0
        fun getInstance(): UserDataEditFragment {
            return UserDataEditFragment()
        }
    }

    private lateinit var manager: LocalBroadcastManager
    private lateinit var userPicView: ImageView
    private lateinit var userPicLinkView: EditText
    private lateinit var userNameView: EditText
    private lateinit var userSurView: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCheck: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_user_data_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPicView = view.findViewById(R.id.hw7user_pic_edit)
        userPicLinkView = view.findViewById(R.id.hw7_user_pic_link_edit)
        userNameView = view.findViewById(R.id.hw7_user_name_edit)
        userSurView = view.findViewById(R.id.hw7_user_sur_edit)
        btnSave = view.findViewById(R.id.hw7_btn_save)
        btnCheck = view.findViewById(R.id.hw7_btn_check)
        btnDelete = view.findViewById(R.id.hw7_btn_delete)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val item = UserListObject.mapData[USER_ID]
        if (item!!.picLink == "") {
            Picasso.get().load(R.drawable.default_user).transform(PicassoCircleTransformation()).into(userPicView)
        } else {
            Picasso.get().load(item.picLink).transform(PicassoCircleTransformation()).into(userPicView)
            userPicLinkView.hint = item.picLink
        }
        userNameView.hint = item.name
        userSurView.hint = item.surname

        btnSave.setOnClickListener { saveChanges() }
        btnCheck.setOnClickListener { checkPicLink() }
        btnDelete.setOnClickListener { deleteUser() }

        manager = LocalBroadcastManager.getInstance(context!!)
    }

    fun saveChanges() {
        if (UserListObject.mapData.containsKey(USER_ID)) {
            if (userPicLinkView.text.toString() != "")
                UserListObject.mapData[USER_ID]!!.picLink = userPicLinkView.text.toString()
            if (userNameView.text.toString() != "")
                UserListObject.mapData[USER_ID]!!.name = userNameView.text.toString()
            if (userSurView.text.toString() != "")
                UserListObject.mapData[USER_ID]!!.surname = userSurView.text.toString()
        } else {
            UserListObject.mapData.put(USER_ID, UserData(userNameView.text.toString(), userSurView.text.toString(), userPicLinkView.text.toString(), USER_ID))
        }
        val sendIntent = Intent("USER_DATA_CHANGED")
        sendIntent.putExtra("STATUS_EXTRA", "SAVED")
        manager.sendBroadcast(sendIntent)
    }

    fun checkPicLink() {
        Picasso.get().load(userPicLinkView.text.toString()).transform(PicassoCircleTransformation()).into(userPicView, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception) {
                Log.e("ImageError", e.message)
                Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun deleteUser() {
        UserListObject.mapData.remove(USER_ID)
        val sendIntent = Intent("USER_DATA_CHANGED")
        sendIntent.putExtra("STATUS_EXTRA", "DELETED")
        sendIntent.putExtra("USER_ID", USER_ID)
        manager.sendBroadcast(sendIntent)
    }
}