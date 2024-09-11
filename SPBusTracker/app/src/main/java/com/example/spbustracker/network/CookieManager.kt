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
            editor.putString("${url.host()}_${cookie.name()}", cookie.toString()) // Use cookie.name()
        }
        editor.apply()
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore[url.host()] ?: loadFromPrefs(url.host())
    }

    private fun loadFromPrefs(host: String): List<Cookie> {
        val cookies = mutableListOf<Cookie>()

        prefs.all.forEach { (key, value) ->
            if (key.startsWith(host)) {
                val cookieString = value as String
                Cookie.parse(HttpUrl.get("http://$host")!!, cookieString)?.let {
                    cookies.add(it)
                }
            }
        }
        return cookies
    }
}
