package com.example.publisher

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.publisher.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerViewAdapter: HomeAdapter

    private val viewModel: PublishViewModel by viewModels()

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.fetchArticles()

        // Initialize SwipeRefreshLayout
        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)

        // Set up a refresh listener
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchArticles()
        }

        recyclerViewAdapter = HomeAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter

        // Set up the RecyclerView with your data list
        viewModel.articleItems.observe(viewLifecycleOwner) { articleItems ->
            recyclerViewAdapter.submitList(articleItems)

            // Stop the refreshing animation when data is loaded
            swipeRefreshLayout.isRefreshing = false
        }


        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_publish_fragment)
        }

        return view
    }
}

