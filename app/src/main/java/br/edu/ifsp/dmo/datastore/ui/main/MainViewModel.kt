package br.edu.ifsp.dmo.datastore.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.datastore.data.repository.userRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: userRepository): ViewModel() {

    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    init{
        viewModelScope.launch {
            userRepository.usernameFlow.collect{
                _username.value = it
            }
        }
    }

    fun save(username: String){
        viewModelScope.launch {
            userRepository.saveUsername(username)
        }
    }

}