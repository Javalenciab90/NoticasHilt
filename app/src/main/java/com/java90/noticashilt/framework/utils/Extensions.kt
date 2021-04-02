package com.java90.noticashilt.framework.utils

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.showProgressBar() {
    this.visibility = View.VISIBLE
}

fun ProgressBar.hideProgressBar() {
    this.visibility = View.GONE
}