package ru.ashhs.cardsdisplayingtask.ui.cards

import ru.ashhs.cardsdisplayingtask.network.dto.*

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Cards view interface.
 */
interface CardsView {
    fun setPost(post: PostDto)
    fun setPostError()
    fun setComment(comment: CommentDto)
    fun setCommentError()
    fun setUsers(users: List<UserDto>)
    fun setUsersError()
    fun setPhotoDescription(photoDescription: PhotoDescriptionDto)
    fun setPhotoDescriptionError()
    fun setTask(task: TaskDto)
    fun setTaskError()
}