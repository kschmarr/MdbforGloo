package com.example.mdbforgloo.ui.upcoming


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
import com.example.mdbforgloo.data.UpcomingResult
import com.example.mdbforgloo.ui.movie.MovieFragment

class UpcomingAdapter(private val activity: Activity) :
    ListAdapter<UpcomingResult, UpcomingAdapter.UpcomingViewHolder>(UpcomingDiffCallback()) {

    class UpcomingViewHolder(layout: View) : RecyclerView.ViewHolder(layout)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): UpcomingViewHolder {
        // create a new view
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.upcoming_item, parent, false)
        return UpcomingViewHolder(layout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val data = getItem(position)
        holder.itemView.findViewById<TextView>(R.id.movieTitle).text = data.title
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

private class UpcomingDiffCallback : DiffUtil.ItemCallback<UpcomingResult>() {

    override fun areItemsTheSame(
        oldItem: UpcomingResult,
        newItem: UpcomingResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UpcomingResult,
        newItem: UpcomingResult): Boolean {
        return oldItem == newItem
    }
}