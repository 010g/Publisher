package com.example.publisher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.publisher.databinding.FragmentPublishBinding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels

class PublishFragment : Fragment() {

    private lateinit var binding: FragmentPublishBinding
    private val viewModel: PublishViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_publish, container, false)
        binding.viewModel = viewModel
        val view = binding.root

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.lifecycleOwner = viewLifecycleOwner // Set the lifecycle owner to observe LiveData

        binding.publishButton.setOnClickListener{
            viewModel.publishArticle()
        }

        return view
    }
}
