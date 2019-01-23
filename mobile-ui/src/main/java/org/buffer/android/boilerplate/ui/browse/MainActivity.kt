package org.buffer.android.boilerplate.ui.browse

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_browse.*
import org.buffer.android.boilerplate.data.browse.WeatherResponse
import org.buffer.android.boilerplate.ui.R
import org.buffer.android.boilerplate.ui.widget.empty.EmptyListener
import org.buffer.android.boilerplate.ui.widget.error.ErrorListener
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getCurrentScope
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity: AppCompatActivity() {

    val browseAdapter: BrowseAdapter by inject()

    val browseBufferoosViewModel: BrowseBufferoosViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        bindScope(getCurrentScope())

        setupBrowseRecycler()
        setupViewListeners()

        browseBufferoosViewModel.getBufferoos().observe(this,
                Observer<BrowseState> {
                    if (it != null) this.handleDataState(it) })

    }

    private fun setupBrowseRecycler() {
        recycler_browse.layoutManager = LinearLayoutManager(this)
        recycler_browse.adapter = browseAdapter
    }

    private fun handleDataState(browseState: BrowseState) {
        when (browseState) {
            is BrowseState.Loading -> setupScreenForLoadingState()
            is BrowseState.Success -> setupScreenForSuccess(browseState.data)
            is BrowseState.Error -> setupScreenForError(browseState.errorMessage)
        }
    }

    private fun setupScreenForLoadingState() {
        progress.visibility = View.VISIBLE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.GONE
    }

    private fun setupScreenForSuccess(data: WeatherResponse?) {
        view_error.visibility = View.GONE
        progress.visibility = View.GONE
        if (data!= null) {
            updateListView(data)
            recycler_browse.visibility = View.VISIBLE
        } else {
            view_empty.visibility = View.VISIBLE
        }
    }

    private fun updateListView(city: WeatherResponse) {
        browseAdapter.mCities.addAll(city.data.request)
        browseAdapter.notifyDataSetChanged()
    }

    private fun setupScreenForError(message: String?) {
        progress.visibility = View.GONE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.VISIBLE
    }

    private fun setupViewListeners() {
        view_empty.emptyListener = emptyListener
        view_error.errorListener = errorListener
    }

    private val emptyListener = object : EmptyListener {
        override fun onCheckAgainClicked() {
            browseBufferoosViewModel.findCity("London")
        }
    }

    private val errorListener = object : ErrorListener {
        override fun onTryAgainClicked() {
            browseBufferoosViewModel.findCity("London")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.app_bar_search)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?): Boolean {
                searchView.clearFocus()
                browseBufferoosViewModel.findCity(q!!)
                return false
            }

            override fun onQueryTextChange(q: String?): Boolean {
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

}