package com.example.marvelapplication.data.network

import com.example.marvelapplication.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.APIKEY)
            .addQueryParameter("hash", md5("1" + BuildConfig.HASH + BuildConfig.APIKEY))
            .addQueryParameter("ts", "1")
            .build()

        val requestBuilder: Request.Builder = original.newBuilder().url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun md5(s: String?): String? {
        if (!s.isNullOrEmpty()) {
            val md5 = "MD5"
            try { // Create MD5 Hash
                val digest = MessageDigest.getInstance(md5)
                digest.update(s.toByteArray())
                val messageDigest = digest.digest()
                // Create Hex String
                val hexString = StringBuilder()
                for (aMessageDigest in messageDigest) {
                    var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                    while (h.length < 2) h = "0$h"
                    hexString.append(h)
                }
                return hexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
        }
        return ""
    }
}