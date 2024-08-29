package com.example.viewtab.network.remoteDataSourse

import com.example.viewtab.network.Constants
import com.example.viewtab.network.ServiceGenerator.createService
import com.example.viewtab.network.ServiceGenerator.getGsonInstance
import com.example.viewtab.network.modelNerwork.Message
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.service.AutenticarService
import com.example.viewtab.network.util.NetworkUtils
import com.google.gson.Gson
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseRemoteDataSourse(){

    val DEFAULT_PROTOCOL = "https"

    private var mGson: Gson? = null

    private var address = "aiko-olhovivo-proxy.aikodigital.io"

    protected open fun BaseRemoteDataSource() {
        mGson = getGsonInstance()
    }

    open fun <S> getService(
        service: Class<S>, baseUrl: String
    ): S {
        return getService(service, DEFAULT_PROTOCOL, baseUrl)
    }

    @Throws(IllegalArgumentException::class)
    open fun <S> getService(
        service: Class<S>, protocol: String, baseUrl: String
    ): S {
        return try {
            val url = HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .scheme(protocol)
                //.addQueryParameter("token", Constants.TOKEN)
                .build()
            createService(service, url)
        } catch (e: Exception) {
            throw IllegalArgumentException("O endereço do servidor é inválido.")
        }
    }

    open fun <S> getMainService(service: Class<S>): S {
        return getService(service, address)
    }

    open fun <ResponseType> wrapInErrorResponse(throwable: Throwable?): Response<ResponseType?>? {
        return when(throwable){
            is SocketTimeoutException -> {
                Response.error(NetworkUtils.REQUEST_TIMEOUT, getErrorResponseBody())
            }
            is HttpException ->{
                Response.error(throwable.code(),getErrorResponseBody())
            }
            is IOException -> {
                Response.error(NetworkUtils.CONNECTION_FAILED, getErrorResponseBody())
            }
            else->{
                Response.error(NetworkUtils.UNEXPECTED_ERROR, getErrorResponseBody())
            }
        }
    }

    open fun <DataType> isUnauthorizedResponse(response: Response<DataType?>?,isRepeate:Boolean =false, callback: () -> Resource<DataType?>?): Resource<DataType?>?{
        if(isRepeate) return proccessResponse(response)
        return when(response?.code()){
            NetworkUtils.UNAUTHORIZED -> {
                autenticarValidity{
                    return@autenticarValidity if (it)
                        callback.invoke()
                    else
                        proccessResponse(response)
                }
            }
            else ->{
                proccessResponse(response)
            }
        }
    }

    open fun <DataType> autenticarValidity(callback: (Boolean) -> Resource<DataType?>?): Resource<DataType?>? {
        val mainService: AutenticarService = getMainService(AutenticarService::class.java)

        return mainService.AutenticarValidity()
            .observeOn(Schedulers.computation())
            ?.onErrorReturn { wrapInErrorResponse(it) }
            ?.map { proccessResponseAutenticar(it, callback) }
            ?.blockingGet()
    }

    open fun <DataType> proccessResponseAutenticar(response: Response<Boolean?>, callback: (Boolean) -> Resource<DataType?>?): Resource<DataType?>? {
        return if (response.isSuccessful) {
            callback.invoke(response.body() == true)
        } else {
            Resource.error(NetworkUtils.getErrorMessageByCode(response.code())?: Message.error("Error de objeto nulo "), null)
        }
    }

    open fun getErrorResponseBody(): ResponseBody? {
        return ResponseBody.create(MediaType.parse("application/json"), "")
    }

    open fun <DataType> proccessResponse(response: Response<DataType?>?): Resource<DataType?>? {
        return if (response?.isSuccessful==true) {
            Resource.success(response.body())
        } else {
            Resource.error(NetworkUtils.getErrorMessageByCode(response?.code() ?: NetworkUtils.UNEXPECTED_ERROR)?: Message.error("Error de objeto nulo "), null)
        }
    }


    protected open fun <T> toJson(obj: T): String? {
        return mGson!!.toJson(obj)
    }
}
