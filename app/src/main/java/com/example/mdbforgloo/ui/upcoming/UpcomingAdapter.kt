package com.example.mdbforgloo.ui.upcoming


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mdbforgloo.R
import com.example.mdbforgloo.data.UpcomingResult

class UpcomingAdapter :
    ListAdapter<UpcomingResult, UpcomingAdapter.UpcomingViewHolder>(UpcomingDiffCallback()) {

    class UpcomingViewHolder(layout: View) : RecyclerView.ViewHolder(layout)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): UpcomingViewHolder {
        // create a new view
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_rated_item, parent, false)
        return UpcomingViewHolder(layout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val data = getItem(position)
        val voteText = "Average Rating: ${data.vote_average}"
        holder.itemView.findViewById<TextView>(R.id.movieTitle).text = data.title
        holder.itemView.findViewById<TextView>(R.id.movieAverageVote).text = voteText
        val moviePoster = holder.itemView.findViewById<ImageView>(R.id.moviePoster)
        val baseURL = "https://image.tmdb.org/t/p/w185"
        Glide.with(holder.itemView.context).load("$baseURL${data.poster_path}").fitCenter().into(moviePoster)
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