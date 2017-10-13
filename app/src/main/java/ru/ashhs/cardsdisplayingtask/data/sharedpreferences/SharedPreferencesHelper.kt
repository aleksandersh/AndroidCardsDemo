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

    fun saveLastLoadedPostId(id: Long) {
        sharedPreferences.edit()
                .putLong(KEY_LAST_LOADED_POST_ID, id)
                .apply()
    }

    fun getLastLoadedPostId(): Long? {
        return when (sharedPreferences.contains(KEY_LAST_LOADED_POST_ID)) {
            true -> sharedPreferences.getLong(KEY_LAST_LOADED_POST_ID, 0)
            false -> null
        }
    }

    fun saveLastLoadedCommentId(id: Long) {
        sharedPreferences.edit()
                .putLong(KEY_LAST_LOADED_COMMENT_ID, id)
                .apply()
    }

    fun getLastLoadedCommentId(): Long? {
        return when (sharedPreferences.contains(KEY_LAST_LOADED_COMMENT_ID)) {
            true -> sharedPreferences.getLong(KEY_LAST_LOADED_COMMENT_ID, 0)
            false -> null
        }
    }

    fun saveLastLoadedTaskId(id: Long) {
        sharedPreferences.edit()
                .putLong(KEY_LAST_LOADED_TASK_ID, id)
                .apply()
    }

    fun getLastLoadedTaskId(): Long? {
        return when (sharedPreferences.contains(KEY_LAST_LOADED_TASK_ID)) {
            true -> sharedPreferences.getLong(KEY_LAST_LOADED_TASK_ID, 0)
            false -> null
        }
    }
}