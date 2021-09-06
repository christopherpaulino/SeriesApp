package com.frontic.seriesapp.ui.listSeries

import android.content.Context
import com.frontic.seriesapp.api.ApiClient
import com.frontic.seriesapp.models.SearchResponse
import com.frontic.seriesapp.models.Series
import com.frontic.seriesapp.utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This class represent the Presenter in List Series functionality.
 *
 * @author Christopher Paulino
 */
class ListSeriesPresenter(val view: ListSeriesContract.View, val context: Context) :
    ListSeriesContract.Presenter {

    override fun getSeries(page: Int) {
        view.showLoading(true)
        if (isNetworkConnected()) {

            ApiClient.create().getSeries(page)
                .enqueue(object : Callback<List<Series>> {
                    override fun onResponse(
                        call: Call<List<Series>>,
                        response: Response<List<Series>>
                    ) {
                        if (response.isSuccessful) {

                            response.body()?.let { view.showList(it) }
                            view.showLoading(false)
                        }
                    }

                    override fun onFailure(call: Call<List<Series>>, t: Throwable) {
                        view.showLoading(false)
                        view.showMessage("${t.message}")
                    }
                })
        }
    }

    override fun searchSeries(criteria: String) {
        view.showLoading(true)
        if (isNetworkConnected()) {

            ApiClient.create().searchSeries(criteria)
                .enqueue(object : Callback<List<SearchResponse>> {
                    override fun onResponse(
                        call: Call<List<SearchResponse>>,
                        response: Response<List<SearchResponse>>
                    ) {
                        if (response.isSuccessful) {
                            val listSeries = ArrayList<Series>()
                            response.body()?.let { searchResponse ->
                                searchResponse.forEach { listSeries.add(it.show) }
                            }
                            view.showSearchResult(listSeries)
                            view.showLoading(false)
                        }
                    }

                    override fun onFailure(call: Call<List<SearchResponse>>, t: Throwable) {
                        view.showLoading(false)
                        view.showMessage("${t.message}")
                    }
                })
        }
    }

    /**
     * Validate is the device is connected to a network
     */
    private fun isNetworkConnected(): Boolean {
        return if (NetworkUtils.isConnected(context)) {
            view.showNoNetworkConnected(false)
            true
        } else {
            view.showLoading(false)
            view.showNoNetworkConnected(true)
            false
        }
    }
}