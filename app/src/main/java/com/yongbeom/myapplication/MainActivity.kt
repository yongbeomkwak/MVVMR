package com.yongbeom.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yongbeom.myapplication.Adapter.UserListAdapter
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

        val mAdapter=UserListAdapter(this)
        binding.recyclerview.apply {
            adapter=mAdapter
            layoutManager=LinearLayoutManager(applicationContext)
        }

        viewModel.allUsers.observe(this, Observer { users->
            users?.let {
                mAdapter.setUsers(it)
            }
        })

        binding.btnAdd.setOnClickListener{
            val dlg = UserDialog(this)
            dlg.show()
        }

    }
}