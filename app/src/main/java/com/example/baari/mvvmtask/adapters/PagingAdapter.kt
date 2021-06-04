package com.example.baari.mvvmtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baari.mvvmtask.databinding.ListItemBinding
import com.example.baari.mvvmtask.retrofit.responce.UserData
import kotlinx.android.synthetic.main.list_item.view.*

class PagingAdapter() :
    PagingDataAdapter<UserData, PagingAdapter.MyViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<UserData>() {
            override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
                return oldItem.first_name == newItem.first_name
            }
        }
    }


    inner class MyViewHolder(val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)


    override fun onBindViewHolder(holder: PagingAdapter.MyViewHolder, position: Int) {

        val item = getItem(position)

        holder.viewDataBinding.setVariable(BR.user, item)

        Glide.with(holder.viewDataBinding.root).load(item!!.avatar)
            .into(holder.viewDataBinding.root.image_list_item)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagingAdapter.MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}