package edu.co.icesi.reto1aplicacionesmoviles.fragments


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import edu.co.icesi.reto1aplicacionesmoviles.Model.Post
import edu.co.icesi.reto1aplicacionesmoviles.Reto1Application
import edu.co.icesi.reto1aplicacionesmoviles.UtilDomi
import edu.co.icesi.reto1aplicacionesmoviles.databinding.FragmentPostBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class PostFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding:FragmentPostBinding? = null
    private val binding get() = _binding!!

    private var rutaImagen : String? =null
    private var file : File? = null
    private lateinit var timestamp : String



    private lateinit var arrayCities : ArrayAdapter<String>
    private lateinit var selectedCity :String
    //Listener
   // var listener : OnNewPostListener? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentPostBinding.inflate(inflater, container, false)
        val view = binding.root

         arrayCities= ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_spinner_dropdown_item)

        arrayCities.addAll(Arrays.asList("Cali", "Bogot??", "Medell??n", "Palmira"))
        binding.addLocationSpinner.onItemSelectedListener = this
        binding.addLocationSpinner.adapter = arrayCities


        binding.postBtn.setOnClickListener {

            val caption = binding.captionET.text.toString()
            var user = Reto1Application.prefs.getLoggedUser()
            var nameuser = user?.username



            val dateText = Calendar.getInstance()

            if(rutaImagen!=null){
                var post = Post(user!!.id,rutaImagen!!, caption, dateText, selectedCity)
                Toast.makeText(requireContext(), "The photo was successfully posted!", Toast.LENGTH_SHORT).show()
                Reto1Application.prefs.savePosts(post)
                //it.onNewPost(post)
            }else{
                Toast.makeText(requireContext(), "Upload an image", Toast.LENGTH_SHORT).show()
            }

        }
        requestPermissions(arrayOf(Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE),1)


        val cameraLauncher = registerForActivityResult(StartActivityForResult(), ::onCameraResult)
        val galleryLauncher = registerForActivityResult(StartActivityForResult(), ::onGalleryResult)


        binding.cameraBtn.setOnClickListener{
            val intent =Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            timestamp = UUID.randomUUID().toString()
            file = File("${context?.getExternalFilesDir(null)}/ $timestamp")
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
         //val bitmap =  result.data?.extras?.get("data") as Bitmap
         //binding.image.setImageBitmap(bitmap)
        if(result.resultCode == RESULT_OK){
            rutaImagen= file?.path
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
            val bitmap :  Bitmap  = MediaStore.Images.Media.getBitmap(context?.contentResolver, uriImage)
            val thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.width/4, bitmap.height/4, true)
            binding.image.setImageBitmap(thumbnail)
            timestamp = UUID.randomUUID().toString()
            file = File("${context?.getExternalFilesDir(null)}/ $timestamp")
            this.rutaImagen = file!!.path
            val sourcePath= UtilDomi.getPath(requireContext(), uriImage!!) //path de la galeria
            copy(File(sourcePath), file)
        }
    }
    @Throws(IOException::class)
    fun copy(src: File?, dst: File?) {
        FileInputStream(src).use { `in` ->
            FileOutputStream(dst).use { out ->
                // Transfer bytes from in to out
                val buf = ByteArray(1024)
                var len: Int
                while (`in`.read(buf).also { len = it } > 0) {
                    out.write(buf, 0, len)
                }
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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val cityPosition = arrayCities.getItem(p2)
        selectedCity = cityPosition!!
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}