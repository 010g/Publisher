package com.example.publisher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.publisher.databinding.ItemHomeBinding

class HomeAdapter(private val dataList: List<ArticleItem>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: ArticleItem) {
            binding.article = dataItem
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}



data class ArticleItem(
    val title: String,
    val author: String,
    val category: String,
    val createdTime: String,
    val content: String
)
