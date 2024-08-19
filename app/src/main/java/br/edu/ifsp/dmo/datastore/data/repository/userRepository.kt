package br.edu.ifsp.dmo.datastore.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore(name = "file_datastore")

class userRepository(context: Context) {

    companion object{
        val ATTR_USERNAME = stringPreferencesKey(name = "username")
    }

    private val dataStore = context.dataStore

    val usernameFlow: Flow<String?> = dataStore.data.catch { exception ->
        if( exception is IOException){
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map {
        it.get(ATTR_USERNAME)
    }

    suspend fun saveUsername(username: String){
        dataStore.edit {
            it[ATTR_USERNAME] = username
        }
    }
}