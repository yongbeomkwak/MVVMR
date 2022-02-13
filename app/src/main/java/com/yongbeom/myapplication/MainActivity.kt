package com.yongbeom.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.yongbeom.myapplication.viewModel.MainViewModel
import com.yongbeom.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        //binding
        //Android ViewModel
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(MainViewModel::class.java)

        //variable 태그 name=viewModel에 viewModel을 할당
        binding.viewModel=viewModel

        binding.lifecycleOwner=this
    }
}