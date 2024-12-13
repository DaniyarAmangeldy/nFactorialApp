package kz.nfactorial.nfactorialapp.home.data.account

import android.content.Context
import kz.nfactorial.nfactorialapp.home.data.model.Account

class SharedPreferencesAccountProvider(
    context: Context,
): AccountProvider {

    private val sharedPreferences = context.getSharedPreferences(PREFERENCES_ACCOUNT, Context.MODE_PRIVATE)

    override suspend fun getAccount(): Account? {
        val name = sharedPreferences.getString(KEY_NAME, null) ?: return null
        val size = sharedPreferences.getInt(KEY_SIZE, -1).takeIf { it >= 0 }

        return Account(name = name, size = size)
    }

    override suspend fun setName(name: String) {
        sharedPreferences.edit().putString(KEY_NAME, name).apply()
    }

    override suspend fun setSize(size: Int?) {
        sharedPreferences.edit().putInt(KEY_SIZE, size ?: -1).apply()
    }

    private companion object {

        const val PREFERENCES_ACCOUNT = "preferences_account"
        const val KEY_NAME = "key_account_name"
        const val KEY_SIZE = "key_account_size"
    }
}