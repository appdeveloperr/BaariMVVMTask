package com.example.baari.mvvmtask

import androidx.paging.*
import com.example.baari.mvvmtask.paging.RemoteMediator
import com.example.baari.mvvmtask.retrofit.Interface
import com.example.baari.mvvmtask.retrofit.responce.UserData
import com.example.baari.mvvmtask.room.Dao
import kotlinx.coroutines.flow.Flow

class Repository(
    private val anInterface: Interface,
    private val dao: Dao
) {

    @ExperimentalPagingApi
    fun getAllUsers(): Flow<PagingData<UserData>> = Pager(
        PagingConfig(pageSize = 5),
        remoteMediator = RemoteMediator(dao, anInterface, 1)
    ) {
        dao.getAllUsers()
    }.flow

}