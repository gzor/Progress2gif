package g.progress2gif.progress2gif

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.graphics.Bitmap
import kotlinx.android.synthetic.main.activity_main.*


const val Extra_Message = "g.progress2gif.MESSAGE"
const val REQUEST_IMAGE_CAPTURE = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    /** Called when user taps send */
    fun sendMessage(view: View){
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()
        val intent = Intent(this,DisplayMessageActivity::class.java).apply {
            putExtra(Extra_Message,message)
        }
        startActivity(intent)
    }
    fun takePhoto(view: View){

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        textView2.text = "Success"
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data!!.extras
            val imageBitmap = extras!!.get("data") as Bitmap
            imageView1.setImageBitmap(imageBitmap)
        }
    }
}
