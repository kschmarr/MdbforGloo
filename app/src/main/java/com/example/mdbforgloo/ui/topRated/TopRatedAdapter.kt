package com.example.mdbforgloo.ui.topRated

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mdbforgloo.R
import com.example.mdbforgloo.data.TopRatedResult
import com.example.mdbforgloo.ui.movie.MovieFragment

class TopRatedAdapter(private val activity: Activity) :
    ListAdapter<TopRatedResult, TopRatedAdapter.TopRatedViewHolder>(TopRatedDiffCallback()) {

    class TopRatedViewHolder(layout: View) : RecyclerView.ViewHolder(layout)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): TopRatedViewHolder {
        // create a new view
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_rated_item, parent, false)
        return TopRatedViewHolder(layout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        val data = getItem(position)
        val voteText = "Average Rating: ${data.vote_average}"
        holder.itemView.findViewById<TextView>(R.id.movieTitle).text = data.title
        holder.itemView.findViewById<TextView>(R.id.movieAverageVote).text = voteText
        val moviePoster = holder.itemView.findViewById<ImageView>(R.id.moviePoster)
        val baseURL = "https://image.tmdb.org/t/p/w185"
        Glide.with(holder.itemView.context).load("$baseURL${data.poster_path}").fitCenter().into(moviePoster)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(MovieFragment.ARG_MOVIE_ID, data.id)
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.movie_fragment, bundle)
        }
    }

}

private class TopRatedDiffCallback : DiffUtil.ItemCallback<TopRatedResult>() {

    override fun areItemsTheSame(
        oldItem: TopRatedResult,
        newItem: TopRatedResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TopRatedResult,
        newItem: TopRatedResult): Boolean {
        return oldItem == newItem
    }
}