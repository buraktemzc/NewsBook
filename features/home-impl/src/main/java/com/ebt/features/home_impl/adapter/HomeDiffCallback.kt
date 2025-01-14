package com.ebt.features.home_impl.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ebt.features.home_impl.model.NewsUIModel

class HomeDiffCallback : DiffUtil.ItemCallback<NewsUIModel>() {
    override fun areItemsTheSame(oldItem: NewsUIModel, newItem: NewsUIModel): Boolean =
        (oldItem.id == newItem.id) && (oldItem.userId == newItem.userId)

    override fun areContentsTheSame(oldItem: NewsUIModel, newItem: NewsUIModel): Boolean =
        oldItem == newItem
}