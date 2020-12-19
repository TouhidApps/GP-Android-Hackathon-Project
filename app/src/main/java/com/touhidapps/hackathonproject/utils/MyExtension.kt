package com.touhidapps.hackathonproject.utils

import android.util.Base64
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import coil.ImageLoader
import coil.request.CachePolicy
import coil.request.LoadRequest
import java.nio.charset.StandardCharsets
import java.util.*


fun String.toBase64Decode(): String {
    return String(Base64.decode(this, Base64.DEFAULT), StandardCharsets.UTF_8)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}


fun AppCompatImageView.loadMyImage(myUrl: String?, cache: Boolean) {

    myUrl?.let {

        val imageLoader = ImageLoader(context)

        val request = LoadRequest.Builder(context).apply {

            if (cache) {
                memoryCachePolicy(CachePolicy.ENABLED)
            } else {
                memoryCachePolicy(CachePolicy.DISABLED)
            }

        }.target(this).data("$it").build()

        imageLoader.execute(request)

    }

} // loadSvgOrOther
