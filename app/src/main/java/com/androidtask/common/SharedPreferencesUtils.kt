package com.androidtask.common

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson

object SharedPreferencesUtils {
    private const val DARK_THEME: String = "DARK_THEME"

    private const val FILE_NAME = "PrefFile"
    private const val NOT_INITIALIZED_ERROR = "Shared Preferences is not initialized"

    private var preferences: SharedPreferences? = null
    private var instance: SharedPreferencesUtils? = null


    fun getInstance(): SharedPreferencesUtils {
        if (instance == null) {
            throw IllegalStateException(NOT_INITIALIZED_ERROR)
        }
        return instance!!
    }

    fun initializeSharedPrefsService(context: Context) {
        instance = SharedPreferencesUtils
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveString(key: String, value: String) {
        val sharedPreferencesEditor = preferences?.edit()
        sharedPreferencesEditor?.putString(key, value)
        sharedPreferencesEditor?.apply()
    }

    fun getStringValue(key: String, defaultValue: String?): String {
        return preferences!!.getString(key, defaultValue!!)!!
    }

    fun saveBoolean(key: String, value: Boolean) {
        try {
            val sharedPreferencesEditor = preferences?.edit()
            sharedPreferencesEditor?.putBoolean(key, value)
            sharedPreferencesEditor?.apply()
        } catch (e: Exception) {
            val s = e.message
        }
    }

    fun getBooleanValue(key: String, defaultValue: Boolean?): Boolean {
        return preferences!!.getBoolean(key, defaultValue!!)
    }

    fun saveLanguage(language: String) {
        saveString("language", language)
    }

    fun getLanguage(): String {
        return getStringValue("language", "en")
    }


    fun saveObject(key: String, value: Any) {
        val sharedPreferencesEditor = preferences?.edit()
        val gson = Gson()
        val json = gson.toJson(value)
        sharedPreferencesEditor?.putString(key, json)
        sharedPreferencesEditor?.apply()
    }

    fun <GenericClass> getObject(key: String, classType: Class<GenericClass>): GenericClass? {
        val gson = Gson()
        val json = preferences?.getString(key, "")
        return gson.fromJson(json, classType)
    }

    fun saveStringToSharedPreference(
        context: Context,
        fileName: String,
        serializedObjectKey: String,
        string: String
    ) {
        val sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putString(serializedObjectKey, string)
        sharedPreferencesEditor.apply()
    }

    fun getStringFromPreference(context: Context, fileName: String, preferenceKey: String): String {
        val sharedPreferences = context.getSharedPreferences(fileName, 0)
        if (sharedPreferences.contains(preferenceKey)) {
            return sharedPreferences.getString(preferenceKey, "")!!
        }
        return ""
    }

    fun saveBooleanToSharedPreference(
        context: Context,
        fileName: String,
        serializedObjectKey: String,
        boolean: Boolean
    ) {
        val sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putBoolean(serializedObjectKey, boolean)
        sharedPreferencesEditor.apply()
    }

    fun getBooleanFromPreference(
        context: Context,
        fileName: String,
        preferenceKey: String
    ): Boolean {
        val sharedPreferences = context.getSharedPreferences(fileName, 0)
        if (sharedPreferences.contains(preferenceKey)) {
            return sharedPreferences.getBoolean(preferenceKey, false)
        }
        return true
    }

    fun putStringValue(key: String, value: String) {
        preferences!!.edit()
            .putString(key, value)
            .apply()
    }

    fun getIntegerValue(key: String, defaultValue: Int?): Int {
        return preferences!!.getInt(key, defaultValue!!)
    }


    fun putIntegerValue(key: String, value: Int) {
        preferences!!.edit()
            .putInt(key, value)
            .apply()
    }

    fun emptyAllCache(context: Context) {
        val pref = PreferenceManager.getDefaultSharedPreferences(context).edit()
        val lang = getLanguage()
        pref.clear()
        pref.apply()
        saveLanguage(lang)
    }

    fun applyDarkTheme(isDarkTheme: Boolean) {
        saveBoolean(DARK_THEME, isDarkTheme)
    }

    fun isDarkTheme(): Boolean {
        return getBooleanValue(DARK_THEME, false)
    }
}