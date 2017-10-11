package ru.ashhs.cardsdisplayingtask.ui.cards

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_userscard_cards.view.*
import ru.ashhs.cardsdisplayingtask.R
import ru.ashhs.cardsdisplayingtask.network.dto.UserDto
import java.util.*

/**
 * Created by AleksanderSh on 10.10.2017.
 *
 * Adapter for Users list.
 */
class UsersAdapter(private val context: Context) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    var users: List<UserDto> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_userscard_cards, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder?, position: Int) {
        holder?.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserDto) {
            itemView.itemUserscardName.text = user.name ?: ""
            itemView.itemUserscardCity.text = user.address?.city ?: ""
            itemView.itemUserscardPhone.text = user.phone ?: ""
            itemView.itemUserscardEmail.text = user.email ?: ""
            itemView.itemUserscardWebsite.text = user.website ?: ""
        }
    }
}