package ru.vssemikoz.pinterestsdk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PinAdapter(private val imageItemList: ArrayList<ImageItem>) : RecyclerView.Adapter<PinAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))
    }

    override fun getItemCount() = imageItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItems(imageItemList[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(imageItem: ImageItem) {
            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            Picasso.with(itemView.context).load(imageItem.imageUrl).into(imageView)
        }
    }

}


