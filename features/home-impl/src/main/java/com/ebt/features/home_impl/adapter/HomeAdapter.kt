package com.ebt.features.home_impl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ebt.features.home_impl.databinding.ItemNewsBinding
import com.ebt.features.home_impl.model.NewsUIModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class HomeAdapter @AssistedInject constructor(
    @Assisted private val onItemClickListener: (rowId: Long) -> Unit
) : ListAdapter<NewsUIModel, HomeAdapter.NewsViewHolder>(HomeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    internal inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsUIModel) {
            with(binding) {
                imageView.load(item.userImageURL) { transformations(CircleCropTransformation()) }
                updatedTextView.isVisible = item.updated
                titleTextView.text = item.title
                descriptionTextView.text = item.description
                root.setOnClickListener {
                    onItemClickListener(getItem(absoluteAdapterPosition).rowId)
                }
            }
        }
    }

    @AssistedFactory
    internal interface HomeAdapterFactory {
        fun create(
            onItemClickListener: (rowId: Long) -> Unit
        ): HomeAdapter
    }
}