package com.frontic.seriesapp.models

import java.io.Serializable

/**
 * This class represents a series entity.
 *
 * @author Christopher Paulino
 */

data class Series(
    val id: Long,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>?,
    val status: String,
    val runtime: Long,
    val averageRuntime: Long,
    val premiered: String,
    val officialSite: String,
    val schedule: Schedule,
    val network: Network?,
    val webChannel: Network?,
    val image: Image?,
    val summary: String,
    val updated: Long,
) : Serializable {

    /**
     * Returns the network name and using the webChannel if is null.
     */
    fun getNetworkName(): String {
        return (network?.name) ?: webChannel?.name ?: ""
    }

    /**
     * Returns the genres list as a String separated by commas.
     */
    fun getGenresListAsString(): String {
        return genres?.joinToString() ?: ""
    }

    /**
     * Returns the schedule days list as a String separated by commas.
     */
    fun getScheduleDaysAsString() : String{
        return schedule.days?.joinToString() ?: ""
    }
}
