package com.example.mdbforgloo.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.mdbforgloo.R
import com.example.mdbforgloo.data.Movie
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeUi()
        arguments?.let { bundle ->
            val movieId = bundle.getInt(ARG_MOVIE_ID, 0)
            movieViewModel.setMovieId(movieId)
        }
    }

    private fun subscribeUi() {
        movieViewModel._movie.observe(viewLifecycleOwner, Observer {
            it?.let {
                initUi(it)
            }
        })

    }
    private fun initUi(movie: Movie) {
        movieTitle.text = movie.title
        movieOverview.text = movie.overview
        movieGenre.text = "Genre(s): ${movie.genres.joinToString(separator = ", ") { it.name }}"
        movieLang.text = "Original Language: ${movie.original_language}"
        val baseURL = "https://image.tmdb.org/t/p/w185"
        context?.let {
            Glide.with(it).load("$baseURL${movie.poster_path}").fitCenter().into(moviePoster)
        }
    }

    companion object {
        const val ARG_MOVIE_ID = "movieId"
    }
}