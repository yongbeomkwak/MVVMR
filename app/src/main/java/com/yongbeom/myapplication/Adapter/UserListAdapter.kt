package com.yongbeom.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yongbeom.myapplication.databinding.RecyclerItemBinding
import com.yongbeom.myapplication.model.db.Entity.UserEntity

class UserListAdapter internal constructor(context: Context):RecyclerView.Adapter<UserListAdapter.UserViewHolder>(){
    private var mBindnig: RecyclerItemBinding? = null
    private val binding get() = mBindnig!!
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var users = emptyList<UserEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       mBindnig= RecyclerItemBinding.inflate(inflater,parent,false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder)
        {
            with(users[position])
            {
                binding.userName.text=name
                binding.birth.text=birth
                binding.gender.text=gender
            }
        }
    }

    internal fun setUsers(users: List<UserEntity>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount()=users.size

    inner class UserViewHolder(binding:RecyclerItemBinding):RecyclerView.ViewHolder(binding.root)
    {

    }

}