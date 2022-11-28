package mx.mauriciogs.modulosiete.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import mx.mauriciogs.modulosiete.R
import mx.mauriciogs.modulosiete.models.VideoModel

class RecipeAdapter(context: Context, private val list:ArrayList<VideoModel>): BaseAdapter() {
    private var inflter: LayoutInflater
    init {
        inflter= LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return list.size
    }
    override fun getItem(p0: Int): Any? {
        return null
    }
    override fun getItemId(p0: Int): Long {
        return 1
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = inflter.inflate(R.layout.video_list_item,null)
        val file: TextView = view.findViewById(R.id.title)
        val image: ImageView = view.findViewById(R.id.image_pel)
        file.text=list[p0].namefile
        image.setImageResource(list[p0].nameImage)
        return view
    }
}