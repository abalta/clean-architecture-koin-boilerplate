package org.akce.android.boilerplate.ui.weather

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_weather.*
import org.akce.android.boilerplate.data.browse.WeatherResponse
import org.akce.android.boilerplate.ui.BaseActivity
import org.akce.android.boilerplate.ui.R
import org.akce.android.boilerplate.ui.browse.BrowseState
import org.akce.android.boilerplate.ui.browse.MainActivity
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getCurrentScope
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherActivity : BaseActivity() {

    val weatherAdapter: WeatherAdapter by inject()

    val weatherViewModel: WeatherViewModel by viewModel()

    private lateinit var cityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        bindScope(getCurrentScope())

        cityName = intent.getStringExtra(MainActivity.EXTRA_QUERY)

        setupBrowseRecycler()
        setupViewListeners()

        weatherViewModel.getWeather(cityName)

        weatherViewModel.getWeatherLiveData().observe(this,
                Observer<BrowseState> {
                    if (it != null) this.handleDataState(it)
                })

        weatherViewModel.getCityLiveData().observe(this, Observer<Boolean> {
            if (it != null && it)
                Toast.makeText(this@WeatherActivity, getString(R.string.save_success), Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this@WeatherActivity, getString(R.string.save_failed), Toast.LENGTH_SHORT).show()
        })

        weatherViewModel.getRemoveAllLiveData().observe(this, Observer<Boolean> {
            if (it != null && it)
                Toast.makeText(this@WeatherActivity, getString(R.string.remove_success), Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this@WeatherActivity, getString(R.string.remove_failed), Toast.LENGTH_SHORT).show()
        })

    }

    private fun setupBrowseRecycler() {
        recycler_weather.layoutManager = LinearLayoutManager(this)
        recycler_weather.adapter = weatherAdapter
    }

    private fun setupViewListeners() {
        add_city.setOnClickListener {
            weatherViewModel.saveCity(cityName)
        }

        remove_all.setOnClickListener {
            weatherViewModel.removeAll()
        }
    }


    override fun updateListView(city: WeatherResponse) {
        weatherAdapter.weatherList.addAll(city.data.weather)
        weatherAdapter.notifyDataSetChanged()
    }

    override fun setupScreenForLoadingState() {
        progress.visibility = View.VISIBLE
        recycler_weather.visibility = View.GONE
        view_error.visibility = View.GONE
    }

    override fun setupScreenForSuccess(data: WeatherResponse?) {
        view_error.visibility = View.GONE
        progress.visibility = View.GONE
        if (data!= null) {
            updateListView(data)
            recycler_weather.visibility = View.VISIBLE
        }
    }

    override fun setupScreenForError(message: String?) {
        progress.visibility = View.GONE
        recycler_weather.visibility = View.GONE
        view_error.visibility = View.VISIBLE
    }

}