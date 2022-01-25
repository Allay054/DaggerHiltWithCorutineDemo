package com.allaykhalil.demo.movifetcher.ui.main.adapters

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
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
import androidx.appcompat.app.AppCompatActivity
import com.allaykhalil.demo.movifetcher.ui.movieDetail.SingleMovieDetailActivity
import com.allaykhalil.demo.movifetcher.utils.GlobalData


class MoviesListAdapter @Inject constructor() :
    RecyclerView.Adapter<MoviesListAdapter.ItemViewHolder>(), AdapterUpdateListener<MoviesList> {
    val moviesList = ArrayList<MoviesList>()
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
        holder.binding.laData.setOnClickListener {
            GlobalData.selectedMovieId = moviesList[position].id
            val intent = Intent(it.context, SingleMovieDetailActivity::class.java)
            it.context.startActivity(intent)
            (it.context as AppCompatActivity).finish()
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun clearItems() {
        moviesList.clear()
        notifyDataSetChanged()
    }

    override fun addItems(items: List<MoviesList>, isLoadMore: Boolean) {
        if (!isLoadMore) {
            clearItems()
            moviesList.addAll(items as Collection<MoviesList>)
            notifyDataSetChanged()
        }
    }

    inner class ItemViewHolder(val binding: ItemMoviesBinding) : BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.viewModel = MoviesItemViewModel(moviesList[position])

            binding.executePendingBindings()


        }
    }

    companion object {

        fun loadImage(strImgUrl: String, imageView: ImageView) {

            val strImg =
                "http://image.tmdb.org/t/p/w185$strImgUrl"
            // Log.d("=>", strImg)
            Glide.with(imageView.context)

                .asBitmap()
                .load(strImg)
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