package mx.mauriciogs.modulosiete.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.mauriciogs.modulosiete.models.Card
import mx.mauriciogs.modulosiete.adapter.MaterialAdapter
import mx.mauriciogs.modulosiete.R

class NestedScrollActivity : AppCompatActivity() {
    private lateinit var lenguajes:Array<String?>
    private lateinit var colores:IntArray
    private lateinit var recyclerView: RecyclerView
    private var adapter: MaterialAdapter?=null
    private var listaTarjetas=ArrayList<Card>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_scroll)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        lenguajes=resources.getStringArray(R.array.lenguajes)
        colores=resources.getIntArray(R.array.inicio_colores)
        iniciarTarjetas()
        if(adapter==null) run {
            adapter = MaterialAdapter(this, listaTarjetas)
        }
        recyclerView=findViewById(R.id.recycler_view)
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(this)
    }

    private fun iniciarTarjetas(){
        for (i in lenguajes.indices){
            val card= Card()
            card.id=i.toLong()
            card.nombre=lenguajes[i]
            card.color_recurso=colores[i]
            listaTarjetas.add(card)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}