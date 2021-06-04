package com.example.baari.mvvmtask.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.baari.mvvmtask.retrofit.Interface
import com.example.baari.mvvmtask.retrofit.responce.UserData
import com.example.baari.mvvmtask.room.RemoteKey
import com.example.baari.mvvmtask.room.Dao
import java.io.InvalidObjectException

@ExperimentalPagingApi
class RemoteMediator(
    private val dao: Dao,
    private val anInterface: Interface,
    private val initialPage: Int = 1
) : RemoteMediator<Int, UserData>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserData>
    ): MediatorResult {

        return try {

            // Judging the page number
            val page = when (loadType) {
                LoadType.APPEND -> {

                    val remoteKey =
                        getLastRemoteKey(state) ?: throw InvalidObjectException("Inafjklg")
                    remoteKey.next ?: return MediatorResult.Success(endOfPaginationReached = true)

                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.REFRESH -> {
                    val remoteKey = getClosestRemoteKeys(state)
                    remoteKey?.next?.minus(1) ?: initialPage
                }
            }

            // make network request
            val response = anInterface.getAllUsers(
                page
            )
            val endOfPagination = response.data.size < state.config.pageSize

            if (response.data.size!=0) {

                response.data.let {

                    // flush our data
                    if (loadType == LoadType.REFRESH) {
                        dao.deleteAllUsers()
                        dao.deleteAllRemoteKeys()
                    }


                    val prev = if (page == initialPage) null else page - 1
                    val next = if (endOfPagination) null else page + 1


                    val list = response.data.map {
                        RemoteKey(it.first_name, prev, next)
                    }


                    // make list of remote keys

                    dao.insertAllRemoteKeys(list)

                    // insert to the room
                    dao.insertUsers(it)


                }
                MediatorResult.Success(endOfPagination)
            } else {
                MediatorResult.Success(endOfPaginationReached = true)
            }


        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

    private suspend fun getClosestRemoteKeys(state: PagingState<Int, UserData>): RemoteKey? {

        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                dao.getAllREmoteKey(it.first_name)
            }
        }

    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, UserData>): RemoteKey? {
        return state.lastItemOrNull()?.let {
            dao.getAllREmoteKey(it.first_name)
        }
    }

}