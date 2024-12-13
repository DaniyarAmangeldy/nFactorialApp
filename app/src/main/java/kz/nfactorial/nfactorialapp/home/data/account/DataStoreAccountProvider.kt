package kz.nfactorial.nfactorialapp.home.data.account

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kz.nfactorial.nfactorialapp.home.data.model.Account

class DataStoreAccountProvider(
    context: Context,
): AccountProvider {

    private val dataStore = context.accountDataStore

    override suspend fun getAccount(): Account? {
        val data = runBlocking { dataStore.data.first() }
        val name = data[stringPreferencesKey(KEY_NAME)] ?: return null
        val size = data[intPreferencesKey(KEY_SIZE)]?.takeIf { it >= 0 }

        return Account(name, size)
    }

    override suspend fun setName(name: String) {
        runBlocking {
            dataStore.edit { settings -> settings[stringPreferencesKey(KEY_NAME)] = name }
        }
    }

    override suspend fun setSize(size: Int?) {
        runBlocking {
            dataStore.edit { settings -> settings[intPreferencesKey(KEY_SIZE)] = size ?: -1 }
        }
    }

    private companion object {

        const val KEY_NAME = "key_account_name"
        const val KEY_SIZE = "key_account_size"
    }
}