package kz.nfactorial.nfactorialapp.home.data.account

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kz.nfactorial.nfactorialapp.home.data.model.Account

class SharedPreferencesAccountProvider(
    context: Context,
): AccountProvider {

    private val sharedPreferences = context.getSharedPreferences(PREFERENCES_ACCOUNT, Context.MODE_PRIVATE)

    override fun getAccount(): Flow<Account?> {
        val name = sharedPreferences.getString(KEY_NAME, null) ?: return flowOf(null)
        val size = sharedPreferences.getInt(KEY_SIZE, -1).takeIf { it >= 0 }
        val image = sharedPreferences.getString(KEY_IMAGE, null).takeIf { !it.isNullOrEmpty() }

        return flowOf(Account(name = name, size = size, image = Uri.parse(image)))
    }

    override suspend fun setName(name: String) {
        sharedPreferences.edit().putString(KEY_NAME, name).apply()
    }

    override suspend fun setSize(size: Int?) {
        sharedPreferences.edit().putInt(KEY_SIZE, size ?: -1).apply()
    }

    override suspend fun setImage(image: Uri?) {
        sharedPreferences.edit().putString(KEY_IMAGE, image?.toString()).apply()
    }

    private companion object {

        const val PREFERENCES_ACCOUNT = "preferences_account"
        const val KEY_NAME = "key_account_name"
        const val KEY_SIZE = "key_account_size"
        const val KEY_IMAGE = "key_account_image"
    }
}