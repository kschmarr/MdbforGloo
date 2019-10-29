package com.example.mdbforgloo.ui.upcoming

import android.app.Activity
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

class UpcomingFragment : Fragment() {

    private lateinit var upcomingViewModel: UpcomingViewModel
    private lateinit var adapter: UpcomingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        upcomingViewModel = ViewModelProviders.of(this).get(UpcomingViewModel::class.java)
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
        adapter = UpcomingAdapter(activity as Activity)
        recyclerView.adapter = adapter
        subscribeUi()
    }

    private fun subscribeUi() {
        upcomingViewModel.upcoming.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.results)
        })
    }
}