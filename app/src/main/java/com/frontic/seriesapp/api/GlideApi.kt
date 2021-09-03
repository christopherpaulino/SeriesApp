package com.frontic.seriesapp.api

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.Spinner
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.ViewTarget
import com.frontic.seriesapp.R
import java.io.File

/**
 * This class manage all the loads of images using Glide, allows you to display images from
 * different sources such as: URL, FILE o just return the Bitmap value from a URL.
 *
 * @param  context Application Context.
 * @author Christopher Paulino.
 */
class GlideApi(private val context: Context) {

    /**
     * Allows to load an image from a URL.
     *
     * @param url           image url that is a reference of file location.
     * @param view          View where the image will be loaded.
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    @Suppress("DEPRECATION")
    fun loadImageFromUrl(
        url: String,
        view: ImageView
    ): ViewTarget<ImageView, Drawable> {

        return Glide.with(context)
            .load(url)
            .fitCenter()
            .placeholder(R.drawable.loading_animation)
            .error(R.mipmap.no_poster_foreground)
            .into(view)
    }

}