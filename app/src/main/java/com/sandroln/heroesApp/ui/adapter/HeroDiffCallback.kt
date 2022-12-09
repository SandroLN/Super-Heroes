package com.sandroln.heroesApp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sandroln.heroesApp.domain.model.HeroModel

class HeroDiffCallback : DiffUtil.ItemCallback<HeroModel>() {
    override fun areItemsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
        return oldItem == newItem
    }
}