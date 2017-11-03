package ru.ashhs.cardsdisplayingtask.utils

/**
 * Created by AleksanderSh on 03.11.2017.
 *
 * Utilities for String class.
 */
inline fun String.ifIsNotEmpty(method: (String) -> Unit): String {
    if (isEmpty()) method(this)
    return this
}

inline fun String.ifIsEmpty(method: (String) -> Unit): String {
    if (!isEmpty()) method(this)
    return this
}