package com.nims.mvvmretrofitandflow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nims.mvvmretrofitandflow.data.Movie
import com.nims.mvvmretrofitandflow.databinding.ListItemMovieBinding

class MainAdapter : ListAdapter<Movie, MainAdapter.MovieViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    class MovieViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                tvTitle.text = movie.title
                tvTitle.text = movie.title
                rvYear.text = "Year : ${movie.year}"
                rvRating.text = "Rating : ${movie.imDbRating}"
                movieCard.setOnClickListener {

                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): MovieViewHolder {
                return MovieViewHolder(
                    ListItemMovieBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    ),
                )
            }
        }
    }
}