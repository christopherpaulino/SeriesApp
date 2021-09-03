package com.frontic.seriesapp.models

import android.content.Context
import com.frontic.seriesapp.R

/**
 * This class represents a season entity.
 *
 * @author Christopher Paulino
 */
data class Season(
    val id: Long,
    val url: String,
    val number: Long,
    val name: String,
    val episodeOrder: Long,
    val premiereDate: String,
    val endDate: String,
    val network: Network,
    val webChannel: Any? = null,
    val image: Image,
    val summary: String,
) {

    /**
     * Return the season name in case that exist, if not the season number.
     */
    fun getSeasonName(context: Context): String {
        return if (name.isNotEmpty()) name else "${context.getString(R.string.season)}: $number"
    }
}
