package ru.ashhs.cardsdisplayingtask.ui.cards

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.commentscard_cards.*
import kotlinx.android.synthetic.main.photocard_cards.*
import kotlinx.android.synthetic.main.postscard_cards.*
import kotlinx.android.synthetic.main.taskscard_cards.*
import kotlinx.android.synthetic.main.usercard_cards.*
import ru.ashhs.cardsdisplayingtask.App
import ru.ashhs.cardsdisplayingtask.R
import ru.ashhs.cardsdisplayingtask.network.dto.*
import ru.ashhs.cardsdisplayingtask.ui.advanced.MinMaxTextWatcher
import java.util.*
import javax.inject.Inject

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * View with cards.
 */
class CardsFragment : Fragment(), CardsView {

    @Inject
    lateinit var cardsPresenter: CardsPresenter

    private lateinit var usersAdapter: UsersAdapter
    private lateinit var errorToast: Toast

    private var lastLoadedPostId: Long? = null
    private var lastLoadedCommentId: Long? = null
    private var lastLoadedTaskId: Long? = null

    companion object {
        private const val KEY_LAST_LOADED_POST_ID = "last_loaded_post_id"
        private const val KEY_LAST_LOADED_COMMENT_ID = "last_loaded_comment_id"
        private const val KEY_LAST_LOADED_TASK_ID = "last_loaded_task_id"

        fun newInstance() = CardsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.cards_fragment, container, false)

        (activity.application as App).appComponent.inject(this)

        if (savedInstanceState != null) {
            lastLoadedPostId = savedInstanceState.getLong(KEY_LAST_LOADED_POST_ID)
            lastLoadedCommentId = savedInstanceState.getLong(KEY_LAST_LOADED_COMMENT_ID)
            lastLoadedTaskId = savedInstanceState.getLong(KEY_LAST_LOADED_TASK_ID)
        }

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        cardsPresenter.bindView(this)
        errorToast = Toast.makeText(context, R.string.cards_load_error, Toast.LENGTH_LONG)

        setupPostsCard()
        setupCommentsCard()
        setupUsersCard()
        setupTasksCard()

        cardsPresenter.loadUsers()
        cardsPresenter.loadPhotoDescription()
        lastLoadedPostId?.let { cardsPresenter.loadPostById(it) }
        lastLoadedCommentId?.let { cardsPresenter.loadCommentById(it) }
        lastLoadedTaskId?.let { cardsPresenter.loadTaskById(it) } ?: cardsPresenter.loadRandomTask()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        lastLoadedPostId?.let { outState.putLong(KEY_LAST_LOADED_POST_ID, it) }
        lastLoadedCommentId?.let { outState.putLong(KEY_LAST_LOADED_COMMENT_ID, it) }
        lastLoadedTaskId?.let { outState.putLong(KEY_LAST_LOADED_TASK_ID, it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        cardsPresenter.unbindView()
    }

    override fun setPost(post: PostDto) {
        lastLoadedPostId = post.id
        postTitleTextView.text = post.title
        postTextTextView.text = post.text
    }

    override fun setPostError() {
        postTitleTextView.text = ""
        postTextTextView.text = ""
    }

    override fun setComment(comment: CommentDto) {
        lastLoadedCommentId = comment.id
        commentEmailTextView.text = comment.email
        commentTitleTextView.text = comment.title
        commentTextTextView.text = comment.text
    }

    override fun setCommentError() {
        commentEmailTextView.text = ""
        commentTitleTextView.text = ""
        commentTextTextView.text = ""
    }

    override fun setUsers(users: List<UserDto>) {
        usersAdapter.users = users
        usersAdapter.notifyDataSetChanged()
    }

    override fun setUsersError() {
        usersAdapter.users = Collections.emptyList()
        usersAdapter.notifyDataSetChanged()
    }

    override fun setPhotoDescription(photoDescription: PhotoDescriptionDto) {
        photoSubtitleTextView.text = photoDescription.title ?: ""

        Picasso.with(context)
                .load(photoDescription.url)
                .into(photoImageView)
    }

    override fun setPhotoDescriptionError() {
        photoSubtitleTextView.text = ""
        photoImageView.setImageDrawable(null)
    }

    override fun setTask(task: TaskDto) {
        lastLoadedTaskId = task.id

        taskTitleTextView.text = task.title ?: ""

        if (task.completed == true) {
            taskCompletedImage.visibility = View.INVISIBLE
            taskUncompletedImage.visibility = View.VISIBLE
        } else {
            taskCompletedImage.visibility = View.VISIBLE
            taskUncompletedImage.visibility = View.INVISIBLE
        }
    }

    override fun setTaskError() {
        taskTitleTextView.text = ""

        taskCompletedImage.visibility = View.INVISIBLE
        taskUncompletedImage.visibility = View.INVISIBLE
    }

    override fun onLoadError() {
        errorToast.show()
    }

    private fun setupPostsCard() {
        postIdEditText.addTextChangedListener(MinMaxTextWatcher(1, 100,
                { postsConfirmButton.isEnabled = false },
                { postsConfirmButton.isEnabled = true }))

        postsConfirmButton.setOnClickListener({ _ ->
            cardsPresenter
                    .loadPostById(postIdEditText.text.toString().toLongOrNull() ?: 0)
        })
    }

    private fun setupCommentsCard() {
        commentIdEditText.addTextChangedListener(MinMaxTextWatcher(1, 500,
                { commentsConfirmButton.isEnabled = false },
                { commentsConfirmButton.isEnabled = true }))

        commentsConfirmButton.setOnClickListener({ _ ->
            cardsPresenter
                    .loadCommentById(commentIdEditText.text.toString().toLongOrNull() ?: 0)
        })
    }

    private fun setupUsersCard() {
        usersAdapter = UsersAdapter(context)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.isAutoMeasureEnabled = true
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        usersRecyclerView.layoutManager = layoutManager
        usersRecyclerView.adapter = usersAdapter
        usersRecyclerView.addItemDecoration(itemDecoration)
        usersRecyclerView.isNestedScrollingEnabled = false
    }

    private fun setupTasksCard() {
        tasksNextRandomButton.setOnClickListener { _ -> cardsPresenter.loadRandomTask() }
    }
}