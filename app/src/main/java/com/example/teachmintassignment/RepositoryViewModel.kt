package com.example.teachmintassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class RepositoryViewModel : ViewModel() {
    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>> get() = _repositories

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val repositoryService = RepositoryService.create() // Initialize your Retrofit service

    fun searchRepositories(query: String) {
        viewModelScope.launch {
            try {
                val response = repositoryService.searchRepositories(query)
                handleResponse(response)
            } catch (e: Exception) {
                _errorMessage.value = e.message // Handle exceptions
            }
        }
    }

    private fun handleResponse(response: Response<GitHubResponse>) {
        if (response.isSuccessful) {
            response.body()?.let {
                _repositories.value = it.items
                _errorMessage.value = null
            }
        } else {
            _errorMessage.value = response.message() // Set error message if response is not successful
        }
    }

    fun clearError() {
        _errorMessage.value = null // Clear error message
    }
}
