package com.example.baari.mvvmtask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.AndroidEntryPoint
import com.example.baari.mvvmtask.ViewModel
import com.example.baari.mvvmtask.R
import com.example.baari.mvvmtask.adapters.PagingAdapter
import kotlinx.android.synthetic.main.fragment_news.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MyFragment : Fragment() {


    private val viewModel by viewModels<ViewModel>()

    private val newsPagingAdapter = PagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pager.collectLatest {
                newsPagingAdapter.submitData(it)
            }
        }

        view.news_recycler.adapter = newsPagingAdapter

    }

}