package com.example.publisher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.publisher.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerViewAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Create a list of NewsArticle items (you can replace this with your actual data)
        val dataList = listOf(
            ArticleItem("Title 1", "Author 1", "Category 1", "Time 1", "Content 1"),
            ArticleItem("Title 2", "Author 2", "Category 2", "Time 2", "Content 2"),
            ArticleItem("Title 3", "Author 3", "Category 3", "Time 3", "Content 3")
            // Add more items as needed
        )

        // Set up the RecyclerView with your data list
        recyclerViewAdapter = HomeAdapter(dataList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter

        return view
    }
}

