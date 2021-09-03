package com.frontic.seriesapp.models

import java.io.Serializable

/**
 * This class represents a image entity.
 *
 * @author Christopher Paulino
 */
data class Image(
    val medium: String?,
    val original: String?
) : Serializable
