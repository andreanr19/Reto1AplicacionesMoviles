package edu.co.icesi.reto1aplicacionesmoviles
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.co.icesi.reto1aplicacionesmoviles.Model.Post

class HomeViewModel : ViewModel() {


    var posts = MutableLiveData<MutableList<Post>> ()

    init {

        posts.postValue(Reto1Application.prefs.getPosts())
    }
}