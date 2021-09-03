package com.frontic.seriesapp.ui.detailsSeries

import android.content.Context
import com.frontic.seriesapp.models.SeasonAdapterItem

class DetailsSeriesContract {

    interface Presenter {
        fun getSeasonsBySeries(seriesId: Long)
        fun destroy()
    }

    interface View {
        fun showSeasons(list: List<SeasonAdapterItem>)
        fun showLoading(show: Boolean)
    }

}