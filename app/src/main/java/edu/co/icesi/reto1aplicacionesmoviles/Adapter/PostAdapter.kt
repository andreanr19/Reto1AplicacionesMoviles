package edu.co.icesi.reto1aplicacionesmoviles.Adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.reto1aplicacionesmoviles.Model.Post
import edu.co.icesi.reto1aplicacionesmoviles.R
import edu.co.icesi.reto1aplicacionesmoviles.Reto1Application.Companion.prefs
import edu.co.icesi.reto1aplicacionesmoviles.Viewholder.PostViewHolder

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private val posts = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        //Inflate: XML -->View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.postrow, parent, false)
        val postViewHolder = PostViewHolder(view)
        return postViewHolder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postn = posts[position]
       holder.bind(postn)
    }

    fun addPost(post:Post){
        posts.add(post)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return posts.size
    }
}