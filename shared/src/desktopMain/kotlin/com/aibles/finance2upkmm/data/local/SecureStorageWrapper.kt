package com.aibles.finance2upkmm.data.local

import com.microsoft.credentialstorage.StorageProvider
import com.microsoft.credentialstorage.model.StoredToken
import com.microsoft.credentialstorage.model.StoredTokenType

actual class SecureStorageWrapperImpl : SecureStorageWrapper {
    private val credentialStorage = StorageProvider.getTokenStorage(true, StorageProvider.SecureOption.REQUIRED)

    override fun saveValue(key: String, value: String) {
        credentialStorage.add(key, StoredToken(value.toCharArray(), StoredTokenType.PERSONAL))
    }

    override fun getValue(key: String): String? {
        val storedToken: StoredToken? = credentialStorage.get(key)
        return storedToken?.value?.let { String(it) }
    }

    fun delete(key: String) {
        credentialStorage.delete(key)
    }
}
