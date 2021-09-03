package com.frontic.seriesapp.ui.base

import com.frontic.seriesapp.models.Series

class BaseContract {

    interface BaseView{
        fun showMessage(message: String)
        fun showLoading(show: Boolean)
    }
}