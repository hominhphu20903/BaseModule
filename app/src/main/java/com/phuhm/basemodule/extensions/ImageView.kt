package com.phuhm.basemodule.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.phuhm.basemodule.R

fun ImageView.setImageTint(color: Int) {
    this.setColorFilter(ContextCompat.getColor(context, color))
}

fun ImageView.loadImageUrl(imgUrl: String) {
    Glide.with(context)
        .load(imgUrl)
        .placeholder(R.drawable.img_placeholder)
        .into(this)
}

fun ImageView.loadImageResource(imgResource: Int) {
    Glide.with(context)
        .load(imgResource)
        .placeholder(R.drawable.img_placeholder)
        .into(this)
}