package com.app.loginpagedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.loginpagedemo.models.Data
import com.app.loginpagedemo.network.ProjectRepository

class MainVM : ViewModel() {
    val postsLiveData = MutableLiveData<List<Data>>()

    fun getPostsFromServer(): LiveData<List<Data>> {
        return ProjectRepository.getAllPosts()
    }
}