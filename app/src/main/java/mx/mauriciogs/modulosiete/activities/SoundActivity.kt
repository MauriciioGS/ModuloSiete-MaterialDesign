package mx.mauriciogs.modulosiete.activities

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import mx.mauriciogs.modulosiete.R
import mx.mauriciogs.modulosiete.adapter.RecipeAdapterSound
import mx.mauriciogs.modulosiete.databinding.ActivitySoundBinding
import mx.mauriciogs.modulosiete.models.SoundModel
import java.io.File

class SoundActivity : AppCompatActivity() {

    private lateinit var archivos: ArrayList<SoundModel>
    private lateinit var binding: ActivitySoundBinding
    private lateinit var adap: RecipeAdapterSound
    private var mediaPlayer: MediaPlayer? = null
    private var indice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        /*
        *   TODO Añadir el pedir permisos de leectura de multimedia
        * */
        val stadoSD: String = Environment.getExternalStorageState()
        if (stadoSD == Environment.MEDIA_MOUNTED) {
            println("sistema=: " +
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).path)
            println("sistema2: " + this.getExternalFilesDir(null))
            val ff =
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).path)
            archivos = ArrayList()
            println(Environment.DIRECTORY_MUSIC)
            val files = ff.listFiles()
            if (files != null) {
                if(files.isEmpty()){
                    Toast.makeText(this, "Sin archivos en: ${
                        Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_MUSIC).path}", Toast.LENGTH_LONG).show()
                } else {
                    ff.listFiles()?.forEach {
                        if (it.isFile) {
                            println("Archivo: ${it.name}, ${it.path}")
                            archivos.add(SoundModel(it.name, R.drawable.musica_img, it.path))
                        }
                    }
                }
            } else
                Toast.makeText(this, "Sin archivos en: ${
                    Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_MUSIC).path}", Toast.LENGTH_LONG).show()

            /*
                    Archivos mp3 de internet
             */
            // archivos.add(Modelo("Magenta_Moon_Part_II.mp3",R.drawable.musica_img,
            //    "https://files.freemusicarchive.org/storage-freemusicarchiveorg/music/no_curator/Line_Noise/Magenta_Moon/Line_Noise_-_01_-_Magenta_Moon_Part_II.mp3"))
        }
        adap = RecipeAdapterSound(this, archivos)
        binding.list.adapter = adap
        binding.play.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(archivos[indice].path)
                    prepare()
                    start()
                }
            }else{
                mediaPlayer!!.start()
            }
        }
        binding.stop.setOnClickListener{
            if (mediaPlayer != null) {
                mediaPlayer!!.release()
                mediaPlayer=null
            }
        }
        binding.pause.setOnClickListener{
            if(mediaPlayer!=null){
                mediaPlayer!!.pause()
            }
        }
        binding.path.setOnClickListener{
            if (archivos.isEmpty())
                Toast.makeText(this, "Sin archivos", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, archivos[indice].path, Toast.LENGTH_SHORT).show()
        }
        binding.list.setOnItemClickListener { parent, view, position, id ->
            val data: SoundModel = archivos[position]
            indice=position
            mediaPlayer = if (mediaPlayer == null) {
                MediaPlayer().apply {
                    setDataSource(data.path)
                    prepare()
                    start()
                }
            } else {
                mediaPlayer!!.release()
                MediaPlayer().apply {
                    setDataSource(data.path)
                    prepare()
                    start()
                }
            }
            Toast.makeText(this, data.namefile, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mediaPlayer?.stop()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
