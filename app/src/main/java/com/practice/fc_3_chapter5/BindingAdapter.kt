package com.practice.fc_3_chapter5

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("visible")
fun View.setVisible(isShow : Boolean) {
    isVisible = isShow
}

@BindingAdapter("image")
fun ImageView.setImage(imageUrl : String?) {
    // coil의 load를 활용하여 이미지 표현
    load(imageUrl) {
        crossfade(300)
    }
}

@BindingAdapter("date")
fun TextView.setDateText(date: Date?) {
    if (date == null) {
        return
    }

    text = SimpleDateFormat("YYYY.MM.dd HH:mm:ss").format(date)
}

@BindingAdapter("favorite")
fun ImageView.setFavorite(isFavorite : Boolean) {
    setImageResource(if (isFavorite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
}