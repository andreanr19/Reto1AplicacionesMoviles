package edu.co.icesi.reto1aplicacionesmoviles.Viewholder

import android.graphics.BitmapFactory
import android.os.Build
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
    var cityPostET: TextView = itemView.findViewById(R.id.captionPostET)
    var imagePost : ImageView = itemView.findViewById(R.id.imagePost)
    var profilePhotoPost : ImageView = itemView.findViewById(R.id.profilePhotoPost)

    //State

    init{

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(post : Post){

        val monthNumber = Month.of(post.date.get(Calendar.MONTH)+1)
        val day = post.date.get(Calendar.DAY_OF_MONTH).toString()
        val monthName = monthNumber.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val year = post.date.get(Calendar.YEAR).toString()
        val bitmap = BitmapFactory.decodeFile(post.image)


        postTextRow.text = post.username
        captionPostET.text = post.caption
        datePostET.text = day + " " + monthName +" " + year
        cityPostET.text = post.city
        imagePost.setImageBitmap(bitmap)
        //profilePhotoPost.setImageBitmap(BitmapFactory.decodeFile())


    }
}