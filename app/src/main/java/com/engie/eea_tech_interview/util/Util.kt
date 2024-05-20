package com.engie.eea_tech_interview.util

import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

fun showSnackWithAction(
    message: String,
    view: View,
    lengthLong: Boolean = true,
    actionMessage: String,
    action: (v: View) -> Unit
) {
    val length = Snackbar.LENGTH_INDEFINITE
    Snackbar.make(view, message, length)
        .setActionTextColor(ContextCompat.getColor(view.context, android.R.color.holo_red_dark))
        .setAction(actionMessage, action)
        .show()
}

fun NavController.safelyNavigate(resId: NavDirections, extras:  FragmentNavigator.Extras) =
    try { navigate(resId, extras) }
    catch (e: Exception) { Timber.tag("naverror").d(e.message!!) }