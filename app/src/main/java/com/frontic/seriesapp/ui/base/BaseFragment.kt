package com.frontic.seriesapp.ui.base

import android.app.AlertDialog
import android.view.View
import androidx.fragment.app.Fragment
import com.frontic.seriesapp.R

abstract class BaseFragment : Fragment() {

    abstract fun initializeVariables(view: View)

    /**
     * Show an Alert Dialog.
     */
    open fun showDialog(message : String) {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.alert))
            .setMessage(message)
            .create()

        alertDialog.show()
    }

}