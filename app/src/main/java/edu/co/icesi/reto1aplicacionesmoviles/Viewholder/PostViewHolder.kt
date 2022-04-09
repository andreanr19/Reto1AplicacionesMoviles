package edu.co.icesi.reto1aplicacionesmoviles.Viewholder

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Build
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.reto1aplicacionesmoviles.Model.Post
import edu.co.icesi.reto1aplicacionesmoviles.R
import java.text.SimpleDateFormat
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

    fun bind(post : Post){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val monthNumber =
            Month.of(post.date.get(Calendar.MONTH)+1).toString()

            val day = post.date.get(Calendar.DAY_OF_MONTH).toString()
            val year = post.date.get(Calendar.YEAR).toString()
            datePostET.text = day + " " + monthNumber + " " + year

        } else {

/*
            var date = post.date.time
            val formatter = SimpleDateFormat("MMM dd yyyy")
            val answer: String = formatter.format(date)
            Log.e("answer",answer)*/

            val dayOfTheWeek = DateFormat.format("EEEE", post.date.time) as String // Thursday

            Log.e(">>>",dayOfTheWeek)
            val day = DateFormat.format("dd", post.date.time) as String // 20
            Log.e(">>>",day)

            val monthString = DateFormat.format("MMM", post.date.time) as String // Jun
            Log.e(">>>",monthString)

            val monthNumber = DateFormat.format("MM", post.date.time) as String // 06
            Log.e(">>>",monthNumber)

            val year = DateFormat.format("yyyy", post.date.time) as String // 2013
            Log.e(">>>",year)


            datePostET.text = day + " " + monthString+ " " + year

        }


        val bitmap = BitmapFactory.decodeFile(post.image)


        postTextRow.text = post.username
        captionPostET.text = post.caption
        cityPostET.text = post.city
        imagePost.setImageBitmap(bitmap)
        //profilePhotoPost.setImageBitmap(BitmapFactory.decodeFile())


    }
}