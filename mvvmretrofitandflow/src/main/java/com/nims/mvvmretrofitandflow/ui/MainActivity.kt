package com.nims.mvvmretrofitandflow.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nims.mvvmretrofitandflow.R
import com.nims.mvvmretrofitandflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var movieAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        binding.toolbar.apply {
            inflateMenu(R.menu.menu_search)

            val searchItem = menu?.findItem(R.id.action_search)
            val searchView = searchItem?.actionView as SearchView

            // Configure the search info and add any event listeners...
            searchView.isSubmitButtonEnabled = true
            searchView.setIconifiedByDefault(true)
            searchView.setOnQueryTextListener(SearchTextListener)
            searchView.setOnCloseListener(SearchOnCloseListener)
        }

        movieAdapter = MainAdapter()
        binding.movieList.layoutManager = LinearLayoutManager(this)
        binding.movieList.adapter = movieAdapter
        observeUiState()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    movieAdapter.submitList(it.movies)
                }
            }
        }
    }

   object SearchTextListener : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            println("Query submitted: $query")
            return true
        }

        override fun onQueryTextChange(query: String?): Boolean {
            return false
        }
    }

    object SearchOnCloseListener : SearchView.OnCloseListener {
        override fun onClose(): Boolean {
            println("Query cleared")
            return false
        }
    }
}