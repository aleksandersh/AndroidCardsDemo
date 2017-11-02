package ru.ashhs.cardsdisplayingtask.presentation.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import ru.ashhs.cardsdisplayingtask.R
import ru.ashhs.cardsdisplayingtask.presentation.cards.CardsFragment
import ru.ashhs.cardsdisplayingtask.presentation.contacts.ContactsFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            replaceFragment(CardsFragment.newInstance())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            bottomNavigationView.selectedItemId -> false
            R.id.action_main_navigation_cards -> {
                replaceFragment(CardsFragment.newInstance())
                true
            }
            R.id.action_main_navigation_contacts -> {
                replaceFragment(ContactsFragment.newInstance())
                true
            }
            else -> false
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
    }
}
