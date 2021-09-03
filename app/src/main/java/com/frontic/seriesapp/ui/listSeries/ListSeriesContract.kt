package com.frontic.seriesapp.ui.listSeries

import com.frontic.seriesapp.models.Series
import com.frontic.seriesapp.ui.base.BaseContract

class ListSeriesContract {

    interface Presenter {
        fun getSeries(page: Int)
        fun searchSeries(criteria: String)
        fun destroy()
    }

    interface View : BaseContract.BaseView {
        fun showList(list: List<Series>)
        fun showNoNetworkConnected(t: Boolean)
        fun showSearchResult (list: List<Series>)
        fun goToSeries(series: Series)
    }
}