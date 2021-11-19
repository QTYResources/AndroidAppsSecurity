package net.zenconsult.android

import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable
import java.util.*

class Token(
    private var refreshToken: String = "",
    private var accessToken: String = "",
    private var expiryDate: Calendar = Calendar.getInstance(),
    private var authCode: String = "",
    private var tokenType: String = "",
    private var name: String = ""
): Serializable {

    constructor(response: JSONObject): this() {
        try {
            setExpiryDate(response.getInt("expires_in"))
        } catch (e: JSONException) {
            setExpiryDate(0)
        }
        tokenType = try {
            response.getString("token_type")
        } catch (e: JSONException) {
            ""
        }
        accessToken = try {
            response.getString("access_token")
        } catch (e: JSONException) {
            ""
        }
        refreshToken = try {
            response.getString("refresh_token")
        } catch (e: JSONException) {
            ""
        }
    }

    public fun buildToken(response: JSONObject) {
        try {
            setExpiryDate(response.getInt("expires_in"))
        } catch (e: JSONException) {
            setExpiryDate(0)
        }
        tokenType = try {
            response.getString("token_type")
        } catch (e: JSONException) {
            ""
        }
        accessToken = try {
            response.getString("access_token")
        } catch (e: JSONException) {
            ""
        }
        refreshToken = try {
            response.getString("refresh_token")
        } catch (e: JSONException) {
            ""
        }
    }

    public fun getExpiryDate(): Calendar {
        return expiryDate
    }

    public fun setExpiryDate(seconds: Int) {
        val now = Calendar.getInstance()
        now.add(Calendar.SECOND, seconds)
        expiryDate = now
    }

    public fun isValidForReq(): Boolean {
        return (accessToken != null && accessToken != "")
    }

    public fun isExpired(): Boolean {
        return Calendar.getInstance().after(expiryDate)
    }

    public fun getRefreshToken(): String {
        return refreshToken
    }

    public fun setRefreshToken(refreshToken: String?) {
        this.refreshToken = refreshToken ?: ""
    }

    public fun getAccessToken(): String {
        return accessToken
    }

    public fun setAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }

    public fun getAuthCode(): String {
        return authCode
    }

    public fun setAuthCode(authCode: String?) {
        this.authCode = authCode ?: ""
    }

    public fun getTokenType(): String {
        return tokenType
    }

    public fun setTokenType(tokenType: String?) {
        this.tokenType = tokenType ?: ""
    }

    public fun getName(): String {
        return name
    }

    public fun setName(name: String) {
        this.name = name
    }

    companion object {
        const val serialVersionUID = 6534067628631656760L
    }
}