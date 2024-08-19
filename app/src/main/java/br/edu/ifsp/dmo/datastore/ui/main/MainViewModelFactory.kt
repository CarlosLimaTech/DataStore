package br.edu.ifsp.dmo.datastore.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.datastore.data.repository.userRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val userRepository: userRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(userRepository) as T
        }
        throw IllegalArgumentException("View Model Desconhecida!")
    }
}