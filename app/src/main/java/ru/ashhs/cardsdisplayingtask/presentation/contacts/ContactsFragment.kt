package ru.ashhs.cardsdisplayingtask.presentation.contacts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.ashhs.cardsdisplayingtask.R

/**
 * Created by AleksanderSh on 11.10.2017.
 *
 * View with contacts.
 */
class ContactsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    }

    companion object {
        fun newInstance(): ContactsFragment {
            return ContactsFragment()
        }
    }
}
