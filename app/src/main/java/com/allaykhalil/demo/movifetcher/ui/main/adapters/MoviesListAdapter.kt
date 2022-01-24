package com.allaykhalil.demo.movifetcher.ui.main.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.allaykhalil.demo.movifetcher.databinding.ItemMoviesBinding
import com.allaykhalil.demo.movifetcher.interfaces.AdapterUpdateListener
import com.allaykhalil.demo.movifetcher.model.receiveModels.MoviesList
import com.allaykhalil.demo.movifetcher.ui.base.BaseViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import javax.inject.Inject

class MoviesListAdapter @Inject constructor() :
    RecyclerView.Adapter<MoviesListAdapter.ItemViewHolder>(), AdapterUpdateListener<MoviesList> {
    val contactList = ArrayList<MoviesList>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(position)
      /*  val strImgUrl =
            "http://image.tmdb.org/t/p/w185${contactList[position].poster_path}"

        Log.d("=>", strImgUrl)
        Glide.with(holder.binding.imageView.context)
            .load(strImgUrl).into(holder.binding.imageView)*/

        //   loadImage(contactList[position].poster_path.toString(), holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun clearItems() {
        contactList.clear()
        notifyDataSetChanged()
    }

    override fun addItems(items: List<MoviesList>, isLoadMore: Boolean) {
        if (!isLoadMore) {
            clearItems()
            contactList.addAll(items as Collection<MoviesList>)
            notifyDataSetChanged()
        }
    }

    inner class ItemViewHolder(val binding: ItemMoviesBinding) : BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.viewModel = MoviesItemViewModel(contactList[position])
            binding.executePendingBindings()

        }
    }

    companion object {

        fun loadImage(strImgUrl: String, imageView: ImageView) {
            Log.d("=>", "http://image.tmdb.org/t/p/w185$strImgUrl")
            Glide.with(imageView.context)

                .asBitmap()
                .load("http://image.tmdb.org/t/p/w185$strImgUrl")
                .into(object : CustomTarget<Bitmap>() {

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                    ) {
                        imageView.setImageBitmap(resource)
                    }
                })
        }
    }
}