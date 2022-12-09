package com.sandroln.heroesApp.ui.adapter

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.sandroln.heroesApp.R
import com.sandroln.heroesApp.databinding.ItemHeroBinding
import com.sandroln.heroesApp.domain.model.HeroModel


class HeroesAdapter(private val listener: (Long, Int, String) -> Unit) :
    ListAdapter<HeroModel, HeroesAdapter.HeroViewHolder>(HeroDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding = ItemHeroBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeroViewHolder(binding = binding, listener = listener)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HeroViewHolder(
        private val binding: ItemHeroBinding,
        private val listener: (Long, Int, String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: HeroModel) = with(itemView) {
            binding.heroName.text = hero.name
            binding.heroImage.loadImage(hero.images)
            itemView.setOnClickListener {
                listener.invoke(
                    hero.id,
                    (binding.linearLayout.background as ColorDrawable).color,
                    hero.images
                )
            }
        }

        private fun ImageView.loadImage(url: String) {
            Glide.with(context)
                .load(url)
                .error(R.drawable.ic_baseline_error_24)
                .listener(
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.d("TAG", "OnLoadFailed")
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            resource?.let {
                                Palette.from(it.toBitmap()).generate() { palette ->
                                    palette?.let {
                                        val intColor = it.vibrantSwatch?.rgb ?: 0
                                        binding.linearLayout.setBackgroundColor(intColor)
                                    }
                                }
                            }
                            return false
                        }
                    }
                ).into(this)
        }
    }
}