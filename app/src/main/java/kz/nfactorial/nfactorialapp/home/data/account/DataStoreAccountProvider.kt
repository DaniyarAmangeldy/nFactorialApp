package kz.nfactorial.nfactorialapp.home.data.account

import android.content.Context
import android.net.Uri
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kz.nfactorial.nfactorialapp.home.data.model.Account

class DataStoreAccountProvider(
    context: Context,
): AccountProvider {

    private val dataStore = context.accountDataStore

    override fun getAccount(): Flow<Account?> {
        return dataStore.data.mapNotNull { preferences ->
            val name = preferences[stringPreferencesKey(KEY_NAME)] ?: return@mapNotNull null
            val size = preferences[intPreferencesKey(KEY_SIZE)]?.takeIf { it >= 0 }
            val image = preferences[stringPreferencesKey(KEY_IMAGE)]?.takeIf { it.isNotEmpty() }
            Account(name, size, Uri.parse(image))
        }
    }

    override suspend fun setName(name: String) {
        dataStore.edit { settings -> settings[stringPreferencesKey(KEY_NAME)] = name }
    }

    override suspend fun setSize(size: Int?) {
        dataStore.edit { settings -> settings[intPreferencesKey(KEY_SIZE)] = size ?: -1 }
    }

    override suspend fun setImage(image: Uri?) {
        dataStore.edit { settings -> settings[stringPreferencesKey(KEY_IMAGE)] = image?.toString() ?: "" }
    }

    private companion object {

        const val KEY_NAME = "key_account_name"
        const val KEY_SIZE = "key_account_size"
        const val KEY_IMAGE = "key_account_image"
    }
}