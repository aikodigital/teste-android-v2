package com.matreis.teste.sptrans.data.api

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return null
    }

}