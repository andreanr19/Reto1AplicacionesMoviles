package edu.co.icesi.reto1aplicacionesmoviles.Viewholder

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.reto1aplicacionesmoviles.Model.Post
import edu.co.icesi.reto1aplicacionesmoviles.R
import java.time.Month
import java.time.format.TextStyle
import java.util.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //UI Controllers
    var postTextRow : TextView = itemView.findViewById(R.id.postTextRow)
    var captionPostET: TextView = itemView.findViewById(R.id.captionPostET)
    var datePostET: TextView = itemView.findViewById(R.id.datePostET)
    var cityPostET: TextView = itemView.findViewById(R.id.cityPostET)
    var imagePost : ImageView = itemView.findViewById(R.id.imagePost)
    var profilePhotoPost : ImageView = itemView.findViewById(R.id.profilePhotoPost)

    //State

    init{

    }

    @SuppressLint("NewApi")
    fun bind(post : Post){

        var calendar : Calendar = Calendar.getInstance()
        calendar.time= post.date
        val monthNumber = calendar.get(Calendar.MONTH+1).toString()
        Log.e(">>>", monthNumber)
        val day = calendar.get(Calendar.DAY_OF_WEEK).toString()
        Log.e(">>>", day)
        val year =calendar.get(Calendar.YEAR).toString()
        Log.e(">>>", year)

        val bitmap = BitmapFactory.decodeFile(post.image)


        postTextRow.text = post.username
        captionPostET.text = post.caption
        datePostET.text = day + " " + monthNumber +" " + year
        cityPostET.text = post.city
        imagePost.setImageBitmap(bitmap)
        //profilePhotoPost.setImageBitmap(BitmapFactory.decodeFile())


    }
}