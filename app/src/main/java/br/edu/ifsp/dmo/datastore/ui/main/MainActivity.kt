package br.edu.ifsp.dmo.datastore.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import br.edu.ifsp.dmo.datastore.R
import br.edu.ifsp.dmo.datastore.data.repository.userRepository
import br.edu.ifsp.dmo.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = userRepository(applicationContext)
        val myfactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, myfactory).get(MainViewModel::class.java)

        setupObservers()
        setupListeners()
    }

    private fun setupListeners(){
        binding.buttonSave.setOnClickListener{
            val str = binding.editName.text.toString()
            viewModel.save(str)
        }
    }

    private fun setupObservers(){
        lifecycleScope.launch {
            viewModel.username.collect{
                if(it.isNullOrBlank()){
                    binding.textMessage.text = "Bem Vindo, usuário!"
                    binding.buttonSave.text = "Salvar"
                } else {
                    binding.textMessage.text = "Bem Vindo, de volta $it!"
                    binding.buttonSave.text = "Alterar"
                }
            }
        }
    }
}