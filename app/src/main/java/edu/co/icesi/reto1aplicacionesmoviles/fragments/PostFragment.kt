package edu.co.icesi.reto1aplicacionesmoviles.fragments


import android.Manifest
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.core.content.FileProvider
import edu.co.icesi.reto1aplicacionesmoviles.Model.Post
import edu.co.icesi.reto1aplicacionesmoviles.Reto1Application
import edu.co.icesi.reto1aplicacionesmoviles.databinding.FragmentPostBinding
import java.io.File
import java.util.*

class PostFragment : Fragment() {

    private var _binding:FragmentPostBinding? = null
    private val binding get() = _binding!!

    private lateinit var captionET : EditText
    private var file : File? = null
    //Listener
    var listener : OnNewPostListener? =null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentPostBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.postBtn.setOnClickListener {
            val text = binding.captionET.text.toString()

            //publicación del dato
            listener?.let {
                val dateText = Calendar.getInstance()

                var post = Post("beta","", text, Calendar.getInstance(), "cali")
                Reto1Application.prefs.savePosts(post)
                it.onNewPost(post)
            }
        }
        requestPermissions(arrayOf(Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE),1)


        val cameraLauncher = registerForActivityResult(StartActivityForResult(), ::onCameraResult)
        val galleryLauncher = registerForActivityResult(StartActivityForResult(), ::onGalleryResult)


        binding.cameraBtn.setOnClickListener{
            val intent =Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = File("${context?.getExternalFilesDir(null)}/photo.png")
            Log.e(">>>", file?.path.toString())
            val uri = FileProvider.getUriForFile(requireContext(), requireContext().packageName, file!!)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

            cameraLauncher.launch(intent)
        }

        binding.galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type ="image/*"
            galleryLauncher.launch(intent)
        }

        return view
    }

    fun onCameraResult(result: ActivityResult){

        //Thumbnail
        // val bitmap =  result.data?.extras?.get("data") as Bitmap
        // binding.image.setImageBitmap(bitmap)
        if(result.resultCode == RESULT_OK){
            val bitmap = BitmapFactory.decodeFile(file?.path)
            val thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.width/4, bitmap.height/4, true)

            binding.image.setImageBitmap(thumbnail)
        }else if(result.resultCode== RESULT_CANCELED){
            Toast.makeText(requireContext(), "You didn't take any photo", Toast.LENGTH_LONG).show()
        }
    }
    fun onGalleryResult(result: ActivityResult){

        if(result.resultCode==RESULT_OK){
            val uriImage = result.data?.data
            uriImage?.let{
                binding.image.setImageURI(uriImage)
            }
        }
    }


    interface OnNewPostListener{
        fun onNewPost(post: Post)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
     @JvmStatic
        fun newInstance() =
            PostFragment()


    }
}