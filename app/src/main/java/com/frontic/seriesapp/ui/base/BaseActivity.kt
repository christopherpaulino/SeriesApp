package com.frontic.seriesapp.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    abstract fun initializeVariable()

    abstract fun loadData()
}