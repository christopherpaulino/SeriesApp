package com.frontic.seriesapp.ui.base

import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    open fun initializeVariables(view: View) {}
}