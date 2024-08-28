package com.example.viewtab.network.modelNerwork

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class Resource<T>(
    @SerializedName("State")
    @NonNull val status: Status,

    @SerializedName("Data")
    @Nullable val data: T?,

    @SerializedName("Message")
    @NonNull val message: Message
) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, Message.empty())
        }

        fun <T> error(message: Message, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(Status.ERROR, null, Message.error(throwable.message))
        }

        fun <T> unexpected(): Resource<T> {
            return Resource(Status.ERROR, null, Message.error("Ocorreu um erro inesperado."))
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, Message.empty())
        }

        fun <T, R> refresh(resourceOld: Resource<T>, data: R): Resource<R> {
            return when (resourceOld.status) {
                Status.LOADING -> loading(data)
                Status.ERROR -> error(resourceOld.message, data)
                Status.SUCCESS -> success(data)
            }
        }
    }
}

