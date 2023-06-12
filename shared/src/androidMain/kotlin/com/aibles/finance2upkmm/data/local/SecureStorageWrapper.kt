package com.aibles.finance2upkmm.data.local

import com.orhanobut.hawk.Hawk

actual class SecureStorageWrapperImpl : SecureStorageWrapper {

    override fun saveValue(key: String, value: String) {
        Hawk.put(key, value)
    }

    override fun getValue(key: String): String? {
        return Hawk.get(key, SecureStorageConst.DEFAULT_VALUE)
    }
}