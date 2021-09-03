package com.frontic.seriesapp.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE

abstract class RecyclerViewPagination(recyclerView: RecyclerView) :
    RecyclerView.OnScrollListener() {

    private val layoutManager: LayoutManager?

    init {
        recyclerView.addOnScrollListener(this)
        this.layoutManager = recyclerView.layoutManager
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == SCROLL_STATE_IDLE) {

            val visibleItemCount = layoutManager!!.childCount //4
            val totalItemCount = layoutManager.itemCount //245
            var firstVisibleItemPosition =
                (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() //3 (index)

            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {

                if (!isLoading() && !isLastPage()) {
                    loadMore()
                }
            }
        }
    }

    abstract fun loadMore()
    abstract fun isLoading(): Boolean
    abstract fun isLastPage(): Boolean
}