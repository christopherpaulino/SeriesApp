package com.frontic.seriesapp.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE

/**
 * This class allows to manage the scroll action in a recycler view for implementing paging.
 */
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

            val visibleItemCount = layoutManager!!.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition =
                (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

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