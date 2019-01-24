package org.akce.android.boilerplate.ui.weather

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.akce.android.boilerplate.data.browse.Weather
import org.akce.android.boilerplate.ui.R

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    var weatherList: MutableList<Weather> = mutableListOf()
    lateinit var context: Context

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.dayText.text = weather.date
        holder.weatherText.text = context.getString(R.string.label_weather, weather.mintempC, weather.maxtempC)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater
                .from(context)
                .inflate(R.layout.item_weather, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var dayText: TextView = view.findViewById(R.id.text_day)
        var weatherText: TextView = view.findViewById(R.id.text_weather)
    }
}