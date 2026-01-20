package com.sample.hmssample.appauthdemo.model.auth

import android.content.Context
import android.content.Intent
import com.sample.hmssample.appauthdemo.model.auth.appauth.BaseAppAuth
import com.sample.hmssample.appauthdemo.model.auth.appauth.UserInfo

class FacebookAppAuth private constructor() : BaseAppAuth(
    AUTHORIZATION_ENDPOINT_URI,
    TOKEN_ENDPOINT_URI,
    REVOCATION_ENDPOINT_URI,
    "$REDIRECT_URI_SCHEME:$REDIRECT_URI_PATH",
    CLIENT_ID,
    SCOPE,
    REQUEST_CODE
) {
    companion object {
        private const val AUTHORIZATION_ENDPOINT_URI = "https://www.facebook.com/v20.0/dialog/oauth"
        private const val TOKEN_ENDPOINT_URI = "https://graph.facebook.com/v20.0/oauth/access_token"
        private const val REVOCATION_ENDPOINT_URI = "https://graph.facebook.com/v20.0/oauth/revoke"
        private const val REDIRECT_URI_SCHEME = "https"
        private const val REDIRECT_URI_PATH = "//6mu48fn0qdgrv0urzt.api-dra.agconnect.link/facebook-login-redirect"
        private const val CLIENT_ID = "481862137838832"
        private const val SCOPE = "public_profile"
        const val REQUEST_CODE = 400

        private var instance: FacebookAppAuth? = null

        fun getInstance(): FacebookAppAuth {
            instance?.let {
                return it
            }
            synchronized(this) {
                return FacebookAppAuth().also { instance = it }
            }
        }
    }

    override fun decodeIdToken(idToken: String): UserInfo {
        return UserInfo.parseGoogleIdToken(idToken)
    }

    override fun onActivityResult(
        context: Context,
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        data?.let {
            super.onActivityResult(context, requestCode, resultCode, it)
        }
    }
}