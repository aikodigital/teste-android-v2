package com.example.viewtab.network.deserializer

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException

class ServerResponseTypeAdapterFactory: TypeAdapterFactory {

    override fun <T> create(gson: Gson, type: TypeToken<T>?): TypeAdapter<T>? {
        val delegate = gson.getDelegateAdapter(this, type)
        val elementAdapter = gson.getAdapter(JsonElement::class.java)
        return object : TypeAdapter<T>() {
            @Throws(IOException::class)
            override fun write(out: JsonWriter, value: T) {
                delegate.write(out, value)
            }

            @Throws(IOException::class)
            override fun read(`in`: JsonReader): T {
                var jsonElement = elementAdapter.read(`in`)
                if (jsonElement.isJsonObject) {
                    val jsonObject = jsonElement.asJsonObject
                    if (jsonObject.has("result") && jsonObject["result"].isJsonArray) {
                        val jsonArray = jsonObject.getAsJsonArray("result")
                        jsonElement = jsonArray[0]
                        jsonElement = JsonParser().parse(jsonElement.asString).asJsonObject
                    }
                }
                return delegate.fromJsonTree(jsonElement)
            }
        }
    }
}