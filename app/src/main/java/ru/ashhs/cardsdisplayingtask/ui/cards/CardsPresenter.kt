package ru.ashhs.cardsdisplayingtask.ui.cards

import android.support.annotation.IntRange
import android.util.Log
import ru.ashhs.cardsdisplayingtask.ui.Presenter
import ru.ashhs.cardsdisplayingtask.usecase.cards.*
import java.util.*
import javax.inject.Inject

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Presenter realization for CardsView.
 */
class CardsPresenter
@Inject
constructor(private val loadPostById: LoadPostById,
            private val loadCommentById: LoadCommentById,
            private val loadUsers: LoadUsers,
            private val loadPhotoDescriptionById: LoadPhotoDescriptionById,
            private val loadTaskById: LoadTaskById) :
        Presenter<CardsView>() {

    private val random: Random = Random()

    companion object {
        const val TAG: String = "CardsPresenter"
        const val USERS_COUNT: Int = 5
        const val PHOTO_ID: Long = 3
        const val MIN_TASK_ID: Long = 1
        const val MAX_TASK_ID: Long = 200
    }

    fun loadPostById(@IntRange(from = 1, to = 100) id: Long) {
        loadFromSingleSource(loadPostById.single(id),
                { post -> view?.setPost(post) },
                { _ -> view?.setPostError() })
    }

    fun loadCommentById(@IntRange(from = 1, to = 500) id: Long) {
        loadFromSingleSource(loadCommentById.single(id),
                { comment -> view?.setComment(comment) },
                { _ -> view?.setCommentError() })
    }

    fun loadUsers() {
        loadFromSingleSource(loadUsers.single(USERS_COUNT),
                { users -> view?.setUsers(users) },
                { _ -> view?.setUsersError() })
    }

    fun loadPhotoDescription() {
        loadFromSingleSource(loadPhotoDescriptionById.single(PHOTO_ID),
                { photoDescription -> view?.setPhotoDescription(photoDescription) },
                { _ -> view?.setPhotoDescriptionError() })
    }

    fun loadRandomTask() {
        loadTaskById(MIN_TASK_ID + random.nextInt((MAX_TASK_ID - MIN_TASK_ID).toInt()))
    }

    fun loadTaskById(@IntRange(from = 1, to = 200) id: Long) {
        loadFromSingleSource(loadTaskById.single(id),
                { task -> view?.setTask(task) },
                { _ -> view?.setTaskError() })
    }

    override fun onError(error: Throwable) {
        Log.d(CardsPresenter.TAG, error.message ?: error.toString())
        view?.onLoadError()
    }
}