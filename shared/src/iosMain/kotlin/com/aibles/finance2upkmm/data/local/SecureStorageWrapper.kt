package com.aibles.finance2upkmm.data.local

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.sizeOf
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.value

actual class SecureStorageWrapperImpl: SecureStorageWrapper {

    override fun saveValue(key: String, value: String) {
        set(key, value)
    }

    override fun getValue(key: String): String {
        return string(key).orEmpty()
    }

    private val serviceName = "finance2up_secure_storage_server"
    /**
     * Saves a string value in the Keychain.
     * @param key The key to store
     * @param stringValue The value to store
     * @return True or false, depending on whether the value has been stored in the Keychain
     */
    fun set(key: String, stringValue: String): Boolean = addOrUpdate(key, stringValue.toNSData())

    /**
     * Returns the string value of an object in the Keychain.
     * @param forKey The key to query
     * @return The stored string value, or null if it is missing
     */
    fun string(forKey: String): String? = value(forKey)?.string()

    private fun existsObject(forKey: String): Boolean = context(forKey) { (account) ->
        val query = query(
            kSecClass to kSecClassGenericPassword,
            kSecAttrAccount to account,
            kSecReturnData to kCFBooleanFalse
        )

        SecItemCopyMatching(query, null)
            .validate()
    }

    private fun addOrUpdate(key: String, value: NSData?): Boolean {
        return if (existsObject(key)) {
            update(key, value)
        } else {
            add(key, value)
        }
    }

    private fun add(key: String, value: NSData?): Boolean = context(key, value) { (account, data) ->
        val query = query(
            kSecClass to kSecClassGenericPassword,
            kSecAttrAccount to account,
            kSecValueData to data
        )

        SecItemAdd(query, null)
            .validate()
    }

    private fun update(key: String, value: Any?): Boolean = context(key, value) { (account, data) ->
        val query = query(
            kSecClass to kSecClassGenericPassword,
            kSecAttrAccount to account,
            kSecReturnData to kCFBooleanFalse
        )

        val updateQuery = query(
            kSecValueData to data
        )

        SecItemUpdate(query, updateQuery)
            .validate()
    }

    private fun value(forKey: String): NSData? = context(forKey) { (account) ->
        val query = query(
            kSecClass to kSecClassGenericPassword,
            kSecAttrAccount to account,
            kSecReturnData to kCFBooleanTrue,
            kSecMatchLimit to kSecMatchLimitOne
        )

        memScoped {
            val result = alloc<CFTypeRefVar>()
            SecItemCopyMatching(query, result.ptr)
            CFBridgingRelease(result.value) as? NSData
        }
    }

    private class Context(val refs: Map<CFStringRef?, CFTypeRef?>) {
        fun query(vararg pairs: Pair<CFStringRef?, CFTypeRef?>): CFDictionaryRef? {
            val map = mapOf(*pairs).plus(refs.filter { it.value != null })
            return CFDictionaryCreateMutable(
                null, map.size.convert(), null, null
            ).apply {
                map.entries.forEach { CFDictionaryAddValue(this, it.key, it.value) }
            }.apply {
                CFAutorelease(this)
            }
        }
    }

    private fun <T> context(vararg values: Any?, block: Context.(List<CFTypeRef?>) -> T): T {
        val standard = mapOf(
            kSecAttrService to CFBridgingRetain(serviceName)
        )
        val custom = arrayOf(*values).map { CFBridgingRetain(it) }
        return block.invoke(Context(standard), custom).apply {
            standard.values.plus(custom).forEach { CFBridgingRelease(it) }
        }
    }

    private fun OSStatus.validate(): Boolean = toUInt() == platform.darwin.noErr
}

internal fun NSData.string(): String? =
    NSString.create(data = this, encoding = NSUTF8StringEncoding)?.toString()

internal fun String.toNSData(): NSData? =
    NSString.create(string = this).dataUsingEncoding(NSUTF8StringEncoding)

//private fun String.toNSData(): NSData = this.encodeToByteArray().toNSData()

private fun ByteArray.toNSData(): NSData = NSMutableData().apply {
    this@toNSData.usePinned { pinned ->
        appendBytes(pinned.addressOf(0), (sizeOf<ByteVar>() * size).toULong())
    }
}