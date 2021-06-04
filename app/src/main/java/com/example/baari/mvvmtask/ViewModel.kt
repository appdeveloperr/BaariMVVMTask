package com.example.baari.mvvmtask

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi

class ViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    @ExperimentalPagingApi
    val pager = repository.getAllUsers()

}