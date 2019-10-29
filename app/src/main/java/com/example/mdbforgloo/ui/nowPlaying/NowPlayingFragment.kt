package com.example.mdbforgloo.ui.nowPlaying

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mdbforgloo.R
import kotlinx.android.synthetic.main.fragment_top_rated.*

class NowPlayingFragment : Fragment() {

    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private lateinit var adapter: NowPlayingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nowPlayingViewModel = ViewModelProviders.of(this).get(NowPlayingViewModel::class.java)
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManager
        adapter = NowPlayingAdapter(activity as Activity)
        recyclerView.adapter = adapter
        subscribeUi()
    }

    private fun subscribeUi() {
        nowPlayingViewModel.nowPlaying.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.results)
        })
    }
}