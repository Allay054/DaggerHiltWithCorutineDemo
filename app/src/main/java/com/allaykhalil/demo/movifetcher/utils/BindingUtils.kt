package com.allaykhalil.demo.movifetcher.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.allaykhalil.demo.movifetcher.interfaces.AdapterUpdateListener


object BindingUtils {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(
        imageView: ImageView,
        url: String
    ) {

    }

    @BindingAdapter("addItems")
    @JvmStatic
    fun <T> addItems(recyclerView: RecyclerView, list: List<T>) {
        Log.d("asad_async_call", "addItems")
        (recyclerView.adapter as AdapterUpdateListener<T>).addItems(list)
    }
}