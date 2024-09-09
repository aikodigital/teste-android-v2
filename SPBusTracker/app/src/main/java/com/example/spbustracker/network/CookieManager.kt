package com.example.spbustracker.network

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class CookieManager(context: Context) : CookieJar {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("CookiePrefs", Context.MODE_PRIVATE)
    private val cookieStore: MutableMap<String, List<Cookie>> = mutableMapOf()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore[url.host()] = cookies

        val editor = prefs.edit()
        cookies.forEach { cookie ->
            editor.putString(url.host(), cookie.toString())
        }
        editor.apply()
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookies = cookieStore[url.host()] ?: emptyList()
        return cookies.ifEmpty {
            loadFromPrefs(url.host())
        }
    }

    private fun loadFromPrefs(host: String): List<Cookie> {
        val cookieString = prefs.getString(host, null)
        return if (cookieString != null) {
            listOf(Cookie.parse(HttpUrl.parse("http://$host")!!, cookieString)!!)
        } else {
            emptyList()
        }
    }

}
