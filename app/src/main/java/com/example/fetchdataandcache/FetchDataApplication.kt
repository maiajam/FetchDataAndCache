package com.example.fetchdataandcache

import android.app.Application
import android.content.pm.PackageManager
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class FetchDataApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        printHashKey()
    }

    //Method that prints hash key.
    fun printHashKey() {
        try {
            val info = packageManager.getPackageInfo(
                "com.c1ctech.sharedatatofacebook",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }
}