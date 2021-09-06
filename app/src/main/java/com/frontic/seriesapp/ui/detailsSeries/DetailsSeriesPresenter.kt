package com.frontic.seriesapp.ui.detailsSeries

import android.app.Activity
import com.frontic.seriesapp.api.ApiClient
import com.frontic.seriesapp.models.Episode
import com.frontic.seriesapp.models.Season
import com.frontic.seriesapp.models.SeasonAdapterItem
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This class represent the Presenter in Details Series functionality.
 *
 * @author Christopher Paulino
 */
class DetailsSeriesPresenter(private val view: DetailsSeriesContract.View) :
    DetailsSeriesContract.Presenter {

    private val context = (view as Activity)

    private val job = Job()
    private val scopeIO = CoroutineScope(job + Dispatchers.IO)
    private val mainThreadScope = CoroutineScope(job + Dispatchers.Main)

    override fun destroy() {
        job.cancel()
    }

    override fun getSeasonsBySeries(seriesId: Long) {
        view.showLoading(true)
        ApiClient.create().getSeasonsBySeries(seriesId).enqueue(object : Callback<List<Season>> {
            override fun onResponse(call: Call<List<Season>>, response: Response<List<Season>>) {

                if (response.isSuccessful) {
                    val list = ArrayList<SeasonAdapterItem>()
                    response.body()?.let {
                        mainThreadScope.launch {
                            it.forEach { item ->
                                run {
                                    val episodes = getEpisodesBySeason(item.id)
                                    list.add(
                                        SeasonAdapterItem(
                                            name = item.getSeasonName(context),
                                            episodes
                                        )
                                    )
                                }
                            }
                            view.apply {
                                showLoading(false)
                                showSeasons(list)
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Season>>, t: Throwable) {
                //TODO: Implement failure action
            }
        })
    }

    private suspend fun getEpisodesBySeason(id: Long): List<Episode> {
        return withContext(Dispatchers.IO) {
            return@withContext ApiClient.create().getEpisodesBySeason(id)
        }
    }
}