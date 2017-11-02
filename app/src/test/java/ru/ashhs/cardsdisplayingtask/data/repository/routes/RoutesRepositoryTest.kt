package ru.ashhs.cardsdisplayingtask.data.repository.routes

import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import ru.ashhs.cardsdisplayingtask.data.network.RoutesServiceApi
import ru.ashhs.cardsdisplayingtask.data.network.dto.CommentDto

class RoutesRepositoryTest {

    @Mock
    private lateinit var serviceApi: RoutesServiceApi
    @Mock
    private lateinit var cache: RoutesCache

    private lateinit var instance: RoutesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        instance = RoutesRepository(serviceApi, cache)
    }

    /**
     * Test for cached value.
     */
    @Test
    fun getCommentByIdTest_cached() {
        val testedId = 2L
        val commentDto1 = CommentDto(1, 2, "comment", "email", "text")
        val commentDto2 = CommentDto(testedId, 3, "comment", "email", "text")
        val cachedCommentDto = CommentDto(testedId, 4, "comment", "email", "text")

        val testObserver = TestObserver.create<CommentDto>()

        `when`(cache[CommentDto::class.java])
                .then { cachedCommentDto }
        `when`(serviceApi.getCommentById(1))
                .then { Single.just(commentDto1) }
        `when`(serviceApi.getCommentById(testedId))
                .then { Single.just(commentDto2) }

        instance.getCommentById(testedId)
                .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        assertTrue(testObserver.valueCount() > 0)
        assertTrue(testObserver.values()[0] == cachedCommentDto)
    }

    /**
     * Test for non-cached value.
     */
    @Test
    fun getCommentByIdTest_nonCached() {
        val testedId = 1L
        val commentDto = CommentDto(testedId, 2, "comment", "email", "text")

        val testObserver = TestObserver.create<CommentDto>()

        `when`(cache[CommentDto::class.java])
                .then { null }
        `when`(serviceApi.getCommentById(testedId))
                .then { Single.just(commentDto) }

        instance.getCommentById(testedId)
                .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        assertTrue(testObserver.valueCount() > 0)
        assertTrue(testObserver.values()[0] == commentDto)
    }

    /**
     * Test for cached different value.
     */
    @Test
    fun getCommentByIdTest_differentCached() {
        val testedId = 1L
        val commentDto = CommentDto(testedId, 2, "comment", "email", "text")
        val cachedCommentDto = CommentDto(2, 3, "comment", "email", "text")

        val testObserver = TestObserver.create<CommentDto>()

        `when`(cache[CommentDto::class.java])
                .then { cachedCommentDto }
        `when`(serviceApi.getCommentById(testedId))
                .then { Single.just(commentDto) }

        instance.getCommentById(testedId)
                .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        assertTrue(testObserver.valueCount() > 0)
        assertTrue(testObserver.values()[0] == commentDto)
    }
}