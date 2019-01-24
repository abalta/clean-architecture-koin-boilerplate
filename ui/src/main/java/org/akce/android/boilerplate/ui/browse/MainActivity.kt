package org.akce.android.boilerplate.ui.browse

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_browse.*
import org.akce.android.boilerplate.data.browse.Request
import org.akce.android.boilerplate.data.browse.WeatherResponse
import org.akce.android.boilerplate.ui.BaseActivity
import org.akce.android.boilerplate.ui.R
import org.akce.android.boilerplate.ui.weather.WeatherActivity
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getCurrentScope
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), MainAdapter.OnItemClickListener {

    val mMainAdapter: MainAdapter by inject()

    val mMainViewModel: MainViewModel by viewModel()

    companion object {
        const val EXTRA_QUERY = "extra_query"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        bindScope(getCurrentScope())

        setupBrowseRecycler()

        mMainViewModel.getCityLiveData().observe(this,
                Observer<BrowseState> {
                    if (it != null) this.handleDataState(it)
                })


        mMainViewModel.getSavedLiveData().observe(this, Observer<List<Request>> {
            it?.let {
                iterator ->
                mMainAdapter.mCities.clear()
                mMainAdapter.mCities.addAll(iterator)
                if (it.isNotEmpty()) {
                    mMainAdapter.notifyDataSetChanged()
                    view_empty.visibility = View.GONE
                } else {
                    view_empty.visibility = View.VISIBLE
                    recycler_browse.visibility = View.GONE
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        mMainViewModel.getSavedCities()
    }

    override fun setupScreenForLoadingState() {
        progress.visibility = View.VISIBLE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.GONE
    }

    override fun setupScreenForSuccess(data: WeatherResponse?) {
        view_error.visibility = View.GONE
        progress.visibility = View.GONE
        if (data!= null) {
            updateListView(data)
            recycler_browse.visibility = View.VISIBLE
        } else {
            view_empty.visibility = View.VISIBLE
        }
    }

    override fun setupScreenForError(message: String?) {
        progress.visibility = View.GONE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.VISIBLE
    }

    private fun setupBrowseRecycler() {
        recycler_browse.layoutManager = LinearLayoutManager(this)
        mMainAdapter.listener = this
        recycler_browse.adapter = mMainAdapter
    }

    override fun updateListView(city: WeatherResponse) {
        mMainAdapter.mCities.addAll(city.data.request)
        mMainAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.app_bar_search)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?): Boolean {
                searchView.clearFocus()
                mMainViewModel.findCity(q!!)
                return false
            }

            override fun onQueryTextChange(q: String?): Boolean {
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onItemClick(city: Request) {
        startActivity(Intent(this, WeatherActivity::class.java).putExtra(EXTRA_QUERY, city.query))
    }

}