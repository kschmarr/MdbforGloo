package com.example.mdbforgloo.ui.nowPlaying

import com.example.mdbforgloo.ui.nowPlaying.NowPlayingAdapter
import com.example.mdbforgloo.ui.nowPlaying.NowPlayingViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdbforgloo.R
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
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

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
            HorizontalDividerItemDecoration.Builder(activity)
                .colorResId(R.color.grey_light)
                .sizeResId(R.dimen.divider_height)
                .marginResId(R.dimen.divider_left_margin, R.dimen.divider_right_margin).build())
        adapter = NowPlayingAdapter()
        recyclerView.adapter = adapter
        subscribeUi()
    }

    private fun subscribeUi() {
        nowPlayingViewModel.nowPlaying.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.results)
        })
    }
}