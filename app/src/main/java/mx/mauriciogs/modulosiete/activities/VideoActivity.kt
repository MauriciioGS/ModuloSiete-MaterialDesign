package mx.mauriciogs.modulosiete.activities


import android.os.Bundle
import android.widget.AdapterView
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import mx.mauriciogs.modulosiete.R
import mx.mauriciogs.modulosiete.adapter.RecipeAdapter
import mx.mauriciogs.modulosiete.databinding.ActivityVideoBinding
import mx.mauriciogs.modulosiete.models.VideoModel

class VideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoBinding
    private lateinit var model: ArrayList<VideoModel>
    private lateinit var adap: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val controller = MediaController(this)
        binding.surface.setMediaController(controller)
        controller.setAnchorView(binding.surface)
        fillList()
        binding.list.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val data: VideoModel = model[position]
                var ruta = ""
                when (data.type) {
                    1 -> {
                        ruta =
                            "android.resource://$packageName/raw/" + data.namefile.removeRange(
                                data.namefile.indexOf('.'),
                                data.namefile.length
                            )
                    }
                    2 -> {
                        ruta = data.namefile
                    }
                }
                val rutaUri = ruta.toUri()
                binding.surface.setVideoURI(rutaUri)
                binding.surface.start()
                Toast.makeText(this, data.namefile, Toast.LENGTH_SHORT).show()
            }
    }

    private fun fillList() {
        model = ArrayList()
        model.add(VideoModel("video.3gp", R.drawable.video_uno,1))
        model.add(
            VideoModel(
                "https://archive.org/download/ElephantsDream/ed_hd.mp4",
                R.drawable.video_dos,2
            )
        )
        adap = RecipeAdapter(this, model)
        binding.list.adapter = adap
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
