package com.frontic.seriesapp.models

import java.io.Serializable

/**
 * This class represents a episode entity.
 *
 * @author Christopher Paulino
 */
data class Episode(
    val id: Long,
    val url: String,
    val name: String,
    val season: Long,
    val number: Long,
    val type: String,
    val airdate: String,
    val airtime: String,
    val airstamp: String,
    val runtime: Long,
    val image: Image?,
    val summary: String,
): Serializable
