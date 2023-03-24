package com.example.samparksolutions.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samparksolutions.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: MyViewModel
    private lateinit var adapterA: AlphabetAdapter
    private lateinit var adapterB: AlphabetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout and obtain an instance of the binding class
        binding = MainActivityBinding.inflate(layoutInflater)

        // Set the content view to the root view of the binding
        setContentView(binding.root)

        // Obtain an instance of the view model
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        // Set up the RecyclerViews and their adapters
        setUpRecyclerViews()

        // Set up the observers for the live data in the view model
        setUpObservers()

        // Set up the click listeners for the move buttons
        setUpClickListeners()
    }

    private fun setUpRecyclerViews() {
        // Set the layout manager for the RecyclerViews
        binding.recyclerViewA.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewB.layoutManager = LinearLayoutManager(this)

        // Create the adapters for the RecyclerViews
        adapterA =
            AlphabetAdapter(viewModel.listA.value!!.toMutableList())
        adapterB =
            AlphabetAdapter(viewModel.listB.value!!.toMutableList())

        // Set the adapters for the RecyclerViews
        binding.recyclerViewA.adapter = adapterA
        binding.recyclerViewB.adapter = adapterB
    }

    private fun setUpObservers() {
        // Observe changes to the live data for the lists in the view model
        viewModel.listA.observe(this, Observer {
            adapterA.updateData(it.toMutableList())
        })

        viewModel.listB.observe(this, Observer {
            adapterB.updateData(it.toMutableList())
        })
    }

    private fun setUpClickListeners() {
        // Set up the click listeners for the move buttons
        binding.moveToRightButton.setOnClickListener {
            viewModel.moveSelectedItemsToRight()
        }

        binding.moveToLeftButton.setOnClickListener {
            viewModel.moveSelectedItemsToLeft()
        }
    }
}
