package ru.ashhs.cardsdisplayingtask.data.sharedpreferences

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by AleksanderSh on 13.10.2017.
 *
 * Shared preferences helper class.
 */
class SharedPreferencesHelper
@Inject
constructor(private val sharedPreferences: SharedPreferences) {
    companion object {
        private const val KEY_LAST_LOADED_POST_ID = "last_loaded_post_id"
        private const val KEY_LAST_LOADED_COMMENT_ID = "last_loaded_comment_id"
        private const val KEY_LAST_LOADED_TASK_ID = "last_loaded_task_id"
    }

    fun saveLastLoadedPostId(id: String) {
        sharedPreferences.edit()
                .putString(KEY_LAST_LOADED_POST_ID, id)
                .apply()
    }

    fun getLastLoadedPostId(): String {
        return sharedPreferences.getString(KEY_LAST_LOADED_POST_ID, "")
    }

    fun saveLastLoadedCommentId(id: String) {
        sharedPreferences.edit()
                .putString(KEY_LAST_LOADED_COMMENT_ID, id)
                .apply()
    }

    fun getLastLoadedCommentId(): String {
        return sharedPreferences.getString(KEY_LAST_LOADED_COMMENT_ID, "")
    }

    fun saveLastLoadedTaskId(id: String) {
        sharedPreferences.edit()
                .putString(KEY_LAST_LOADED_TASK_ID, id)
                .apply()
    }

    fun getLastLoadedTaskId(): String {
        return sharedPreferences.getString(KEY_LAST_LOADED_TASK_ID, "")
    }
}