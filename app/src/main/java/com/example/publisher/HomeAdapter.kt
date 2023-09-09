package com.example.publisher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.publisher.databinding.ItemHomeBinding

class HomeAdapter() :
    ListAdapter<ArticleItem, RecyclerView.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: ArticleItem) {
            binding.articleTitle.text = dataItem.title
            binding.articleContent.text = dataItem.content
            binding.authorName.text = dataItem.author
            binding.categoty.text = dataItem.category
            binding.createdTime.text = dataItem.createdTime

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ViewHolder).bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ArticleItem>() {
        override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
            return oldItem == newItem
        }
    }
}



data class ArticleItem(
    val title: String,
    val author: String,
    val category: String,
    val createdTime: String,
    val content: String
)
