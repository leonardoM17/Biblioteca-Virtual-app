package com.example.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: LibraryViewModel
    private lateinit var libroAdapter: LibroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(LibraryViewModel::class.java)
        libroAdapter = LibroAdapter()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = libroAdapter

        val searchView = findViewById<SearchView>(R.id.searchView)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val txtNoResult = findViewById<TextView>(R.id.txtNoResult)

        searchButton.setOnClickListener {
            val query = searchView.query.toString()
            if (query.isNotBlank()) {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.INVISIBLE
                searchButton.isEnabled = false

                viewModel.fetchItems(1, "F", query).observe(this) { libros ->
                    libroAdapter.submitList(libros)

                    if (libros.isEmpty()) {
                        progressBar.visibility = View.INVISIBLE
                        recyclerView.visibility = View.INVISIBLE
                        txtNoResult.visibility = View.VISIBLE
                        searchButton.isEnabled = true
                    } else {
                        progressBar.visibility = View.INVISIBLE
                        recyclerView.visibility = View.VISIBLE
                        txtNoResult.visibility = View.INVISIBLE
                        searchButton.isEnabled = true

                    }



                }
            }
        }
    }
}

