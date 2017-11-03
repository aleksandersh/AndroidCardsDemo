package ru.ashhs.cardsdisplayingtask.presentation.cards

import android.util.Log
import ru.ashhs.cardsdisplayingtask.domain.cards.*
import ru.ashhs.cardsdisplayingtask.presentation.Presenter
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

    fun onPostRequested(textId: String) {
        val id = textId.toLongOrNull() ?: 0
        if (id < 1 || 100 < id) return

        loadPostById(id)
                .doOnError { error -> onError(error) }
                .subscribe(
                        { post -> view?.setPost(post) },
                        { _ -> view?.setPostError() })
                .collect()
    }

    fun onCommentRequested(textId: String) {
        val id = textId.toLongOrNull() ?: 0
        if (id < 1 || 500 < id) return

        loadCommentById(id)
                .doOnError { error -> onError(error) }
                .subscribe(
                        { comment -> view?.setComment(comment) },
                        { _ -> view?.setCommentError() })
                .collect()
    }

    fun onUsersRequested() {
        loadUsers(USERS_COUNT)
                .doOnError { error -> onError(error) }
                .subscribe(
                        { users -> view?.setUsers(users) },
                        { _ -> view?.setUsersError() })
                .collect()
    }

    fun onPhotoDescriptionRequested() {
        loadPhotoDescriptionById(PHOTO_ID)
                .doOnError { error -> onError(error) }
                .subscribe(
                        { photoDescription -> view?.setPhotoDescription(photoDescription) },
                        { _ -> view?.setPhotoDescriptionError() })
                .collect()
    }

    fun onRandomTaskRequested() {
        onTaskRequested(MIN_TASK_ID + random.nextInt((MAX_TASK_ID - MIN_TASK_ID).toInt()))
    }

    fun onTaskRequested(textId: String) {
        val id = textId.toLongOrNull() ?: 0
        if (id < 1 || 500 < id) return

        onTaskRequested(id)
    }

    private fun onTaskRequested(id: Long) {
        loadTaskById(id)
                .doOnError { error -> onError(error) }
                .subscribe(
                        { task -> view?.setTask(task) },
                        { _ -> view?.setTaskError() })
                .collect()
    }

    private fun onError(error: Throwable) {
        Log.d(CardsPresenter.TAG, error.message ?: error.toString())
        view?.onLoadError()
    }
}