package com.frontic.seriesapp.models

import java.io.Serializable

/**
 * This class represents a schedule entity.
 *
 * @author Christopher Paulino
 */
data class Schedule (
    val time: String?,
    val days: List<String>?
):Serializable

