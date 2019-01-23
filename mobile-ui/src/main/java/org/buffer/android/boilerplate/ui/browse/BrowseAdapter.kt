package org.buffer.android.boilerplate.ui.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.buffer.android.boilerplate.data.browse.Request
import org.buffer.android.boilerplate.ui.R

class BrowseAdapter : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var mCities: MutableList<Request> = mutableListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bufferoo = mCities[position]
        holder.nameText.text = bufferoo.query
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_bufferoo, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mCities.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatarImage: ImageView
        var nameText: TextView
        var titleText: TextView

        init {
            avatarImage = view.findViewById(R.id.image_avatar)
            nameText = view.findViewById(R.id.text_name)
            titleText = view.findViewById(R.id.text_title)
        }
    }

}