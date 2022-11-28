package mx.mauriciogs.modulosiete.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import mx.mauriciogs.modulosiete.adapter.AdapterViewHolder
import mx.mauriciogs.modulosiete.databinding.ActivityRecylerViewBinding
import mx.mauriciogs.modulosiete.models.Source

class RecylerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecylerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecylerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        initItemp()
    }
    private fun initItemp(){
        val recyclerView=binding.data
        recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter= AdapterViewHolder(this, Source.dataList)
        recyclerView.adapter=adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
