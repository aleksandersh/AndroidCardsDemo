package ru.ashhs.cardsdisplayingtask.presentation.cards

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
import ru.ashhs.cardsdisplayingtask.data.sharedpreferences.SharedPreferencesHelper
import ru.ashhs.cardsdisplayingtask.data.network.dto.*
import ru.ashhs.cardsdisplayingtask.presentation.advanced.MinMaxTextWatcher
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
    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private lateinit var usersAdapter: UsersAdapter
    private lateinit var errorToast: Toast

    companion object {
        fun newInstance() = CardsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.cards_fragment, container, false)

        (activity.application as App).appComponent.inject(this)

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
        sharedPreferencesHelper.getLastLoadedPostId()?.let {
            postIdEditText.setText(it.toString())
            cardsPresenter.loadPostById(it)
        }
        sharedPreferencesHelper.getLastLoadedCommentId()?.let {
            commentIdEditText.setText(it.toString())
            cardsPresenter.loadCommentById(it)
        }
        sharedPreferencesHelper.getLastLoadedTaskId()
                ?.let { cardsPresenter.loadTaskById(it) } ?: cardsPresenter.loadRandomTask()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        cardsPresenter.unbindView()
    }

    override fun setPost(post: PostDto) {
        post.id?.let { sharedPreferencesHelper.saveLastLoadedPostId(it) }
        postTitleTextView.text = post.title
        postTextTextView.text = post.text
    }

    override fun setPostError() {
        postTitleTextView.text = ""
        postTextTextView.text = ""
    }

    override fun setComment(comment: CommentDto) {
        comment.id?.let { sharedPreferencesHelper.saveLastLoadedCommentId(it) }
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
        task.id?.let { sharedPreferencesHelper.saveLastLoadedTaskId(it) }

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