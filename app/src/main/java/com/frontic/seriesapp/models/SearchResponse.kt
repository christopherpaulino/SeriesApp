package com.frontic.seriesapp.models

/**
 * This class represents a search response entity.
 *
 * @author Christopher Paulino
 */
data class SearchResponse(
    val score: Double,
    val show: Series
)
