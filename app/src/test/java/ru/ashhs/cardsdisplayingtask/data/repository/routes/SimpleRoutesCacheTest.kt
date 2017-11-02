package ru.ashhs.cardsdisplayingtask.data.repository.routes

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.ashhs.cardsdisplayingtask.data.network.dto.CommentDto

class SimpleRoutesCacheTest {

    private lateinit var instance: SimpleRoutesCache

    @Before
    fun setUp() {
        instance = SimpleRoutesCache()
    }

    @Test
    fun simpleTest() {
        val commentClazz = CommentDto::class.java
        val commentDto = CommentDto(1, 2, "comment", "email", "text")

        var testValue = instance[commentClazz]
        assertTrue(testValue == null)

        instance[commentClazz] = commentDto

        testValue = instance[commentClazz]
        assertTrue(testValue == commentDto)
    }
}