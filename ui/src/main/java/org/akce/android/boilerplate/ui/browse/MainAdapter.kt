package org.akce.android.boilerplate.ui.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.akce.android.boilerplate.data.browse.Request
import org.akce.android.boilerplate.ui.R

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var mCities: MutableList<Request> = mutableListOf()
    lateinit var listener: OnItemClickListener

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
        var nameText: TextView = view.findViewById(R.id.text_name)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(mCities[adapterPosition])
            }
        }

    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(city: Request)
    }

}