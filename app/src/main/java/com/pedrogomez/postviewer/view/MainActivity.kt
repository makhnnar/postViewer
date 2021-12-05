package com.pedrogomez.postviewer.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pedrogomez.postviewer.databinding.ActivityMainBinding
import com.pedrogomez.postviewer.view.viewmodel.UsersViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val usersViewModel : UsersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}