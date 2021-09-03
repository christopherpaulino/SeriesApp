package com.frontic.seriesapp.models

/**
 * This class represents season adapter item to be loaded in the Seasons recycler view.
 *
 * @author Christopher Paulino
 */
data class SeasonAdapterItem(
    val name: String,
    val episodes: List<Episode>?
)
