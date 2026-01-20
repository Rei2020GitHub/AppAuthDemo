package com.sample.hmssample.appauthdemo.model.auth.appauth

import com.auth0.android.jwt.JWT
import com.huawei.hms.support.hwid.result.AuthHuaweiId
import org.json.JSONObject

class UserInfo {
    companion object {
        const val KEY_ID = "id"
        const val KEY_NAME = "name"
        const val KEY_EMAIL = "email"
        const val KEY_PICTURE = "picture"
        private const val KEY_DATA = "data"
        private const val KEY_URL = "url"

        fun parseGoogleIdToken(idToken: String): UserInfo {
            val jwt = JWT(idToken)
            return UserInfo().apply {
                this.openId = jwt.subject
                this.name = jwt.claims["name"]?.asString()
                this.familyName = jwt.claims["family_name"]?.asString()
                this.givenName = jwt.claims["given_name"]?.asString()
                this.pictureUrl = jwt.claims["picture"]?.asString()
                this.email = jwt.claims["email"]?.asString()
                this.emailVerified = jwt.claims["email_verified"]?.asBoolean()
            }
        }

        fun parseHuaweiIdToken(idToken: String): UserInfo {
            val jwt = JWT(idToken)
            return UserInfo().apply {
                this.openId = jwt.subject
                this.name = jwt.claims["display_name"]?.asString()
                this.pictureUrl = jwt.claims["picture"]?.asString()
                this.email = jwt.claims["email"]?.asString()
                this.emailVerified = jwt.claims["email_verified"]?.asBoolean()
            }
        }

        fun parseAuthHuaweiId(authHuaweiId: AuthHuaweiId): UserInfo {
            return UserInfo().apply {
                this.openId = authHuaweiId.openId
                this.name = authHuaweiId.displayName
                this.familyName = authHuaweiId.familyName
                this.givenName = authHuaweiId.givenName
                this.pictureUrl = authHuaweiId.avatarUriString
                this.email = authHuaweiId.email
            }
        }
    }

    var openId: String? = null
    var name: String? = null
    var familyName: String? = null
    var givenName: String? = null
    var pictureUrl: String? = null
    var email: String? = null
    var emailVerified: Boolean? = null
}