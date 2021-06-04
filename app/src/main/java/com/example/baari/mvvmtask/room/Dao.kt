package com.example.baari.mvvmtask.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.baari.mvvmtask.retrofit.responce.UserData

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(list: List<UserData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersingle(article: UserData)

    @Query("SELECT * FROM users ")
    fun getAllUsers(): PagingSource<Int, UserData>

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(list: List<RemoteKey>)


    @Query("SELECT * FROM RemoteKey WHERE id = :id")
    suspend fun getAllREmoteKey(id: String): RemoteKey?

    @Query("DELETE FROM RemoteKey")
    suspend fun deleteAllRemoteKeys()

}