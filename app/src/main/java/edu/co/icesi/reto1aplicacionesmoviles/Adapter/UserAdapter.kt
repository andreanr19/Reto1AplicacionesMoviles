package edu.co.icesi.reto1aplicacionesmoviles.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import edu.co.icesi.reto1aplicacionesmoviles.Model.User
import edu.co.icesi.reto1aplicacionesmoviles.R

class UserAdapter (private var mContext: Context,
                    private var mUser : List<User>,
                    private var isFragment : Boolean = false) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.useritem_layout, parent, false)

        return UserAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {

        val user = mUser[position]

    }

    override fun getItemCount(): Int {
        return mUser.size
    }

    class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView){

        var userNameTextView : TextView = itemView.findViewById(R.id.username_search)
        var userProfileImage : CircleImageView = itemView.findViewById(R.id.user_profile_image_search)
        var followButton : Button = itemView.findViewById(R.id.follow_btn_search)


    }

}