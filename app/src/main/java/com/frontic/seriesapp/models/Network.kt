package com.frontic.seriesapp.models

import java.io.Serializable

/**
 * This class represents a network entity.
 *
 * @author Christopher Paulino
 */
data class Network (
    val id: Long,
    val name: String?
) : Serializable