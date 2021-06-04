package com.example.baari.mvvmtask.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import com.example.baari.mvvmtask.Repository
import com.example.baari.mvvmtask.retrofit.Interface
import com.example.baari.mvvmtask.room.Dao
import com.example.baari.mvvmtask.room.MyDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DiModules {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideNewsInterface(retrofit: Retrofit): Interface {
        return retrofit.create(Interface::class.java)
    }


    @Singleton
    @Provides
    fun provideRepository(anInterface: Interface, dao: Dao): Repository {
        return Repository(anInterface,dao)
    }


    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): MyDatabase {
        return MyDatabase.getInstance(context)
    }


    @Singleton
    @Provides
    fun provideNewsDao(myDatabase: MyDatabase): Dao {
        return myDatabase.getDao()
    }

}