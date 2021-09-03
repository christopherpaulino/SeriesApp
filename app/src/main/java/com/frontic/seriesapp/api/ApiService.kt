package com.frontic.seriesapp.api

import com.frontic.seriesapp.models.Episode
import com.frontic.seriesapp.models.SearchResponse
import com.frontic.seriesapp.models.Season
import com.frontic.seriesapp.models.Series
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * List of Api Services.
 *
 * @author Christopher Paulino.
 */
interface ApiService {

    /**
     * Retrieve list of series.
     * @param page  Page number to be loaded.
     */
    @GET("shows")
    fun getSeries(
        @Query("page") page: Int
    ): Call<List<Series>>

    /**
     * Retrieve list of series by search.
     * @param criteria  Search criteria.
     */
    @GET("search/shows")
    fun searchSeries(
        @Query("q") criteria: String
    ): Call<List<SearchResponse>>

    /**
     * Retrieve list of seasons by series.
     * @param id  id of series.
     */
    @GET("shows/{id}/seasons")
    fun getSeasonsBySeries(@Path("id") id: Long): Call<List<Season>>

    /**
     * Retrieve list of episodes by season.
     * @param id  id of season.
     */
    @GET("seasons/{id}/episodes")
    suspend fun getEpisodesBySeason(@Path("id") id: Long): List<Episode>
}